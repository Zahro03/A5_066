package com.example.a5_066.ui.viewModel.dokter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.repository.DokterRepository
import com.example.a5_066.ui.viewModel.dokter.InsertUiEvent
import com.example.a5_066.ui.viewModel.dokter.InsertUiState
import com.example.a5_066.ui.viewModel.dokter.toDokter
import com.example.a5_066.ui.viewModel.dokter.toUiStateDokter
import kotlinx.coroutines.launch

class UpdateDokterViewModel(
    savedStateHandle: SavedStateHandle,
    private val dokterRepository: DokterRepository
) : ViewModel() {

    // State untuk menyimpan data dokter
    var updateDokterUiState by mutableStateOf(InsertUiState())
        private set

    // Mendapatkan id_Dokter dari savedStateHandle
    val id_Dokter: String = checkNotNull(savedStateHandle["id_Dokter"])

    init {
        // Mengambil data dokter berdasarkan id_Dokter
        viewModelScope.launch {
            try {
                val dokter = dokterRepository.getDokterById(id_Dokter)
                updateDokterUiState = dokter.toUiStateDokter()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Fungsi untuk mengubah state InsertUiState dengan InsertUiEvent
    fun updateInsertDokterState(insertUiEvent: InsertUiEvent) {
        updateDokterUiState = updateDokterUiState.copy(insertUiEvent = insertUiEvent)
    }

    // Fungsi untuk memperbarui data dokter
    suspend fun updateDokter() {
        try {
            dokterRepository.updateDokter(id_Dokter, updateDokterUiState.insertUiEvent.toDokter())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
