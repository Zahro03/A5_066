package com.example.a5_066.ui.viewModel.pasien

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.repository.PasienRepository
import kotlinx.coroutines.launch

class UpdateViewModel(
  savedStateHandle: SavedStateHandle,
    private val pasienRepository: PasienRepository
): ViewModel() {
    var updatePasienUiState by mutableStateOf(InsertUiState())
        private set
    val id_hewan: String = checkNotNull(savedStateHandle["id_hewan"])

    init {
        viewModelScope.launch {
            try {
                val pasien = pasienRepository.getPasienById(id_hewan)
                updatePasienUiState = pasien.toUiStatePasien()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInsertPasienState(insertUiEvent: InsertUiEvent) {
        updatePasienUiState = updatePasienUiState.copy(insertUiEvent = insertUiEvent)
    }

    suspend fun updatePasien() {
        try {
            pasienRepository.updatePasien(id_hewan, updatePasienUiState.insertUiEvent.toPasien())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}