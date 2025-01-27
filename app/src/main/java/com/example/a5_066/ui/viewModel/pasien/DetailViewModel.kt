package com.example.a5_066.ui.viewModel.pasien

import Pasien
import PasienRepository
import PasienResponseDetail
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Kelas sealed untuk merepresentasikan berbagai status UI pada DetailPasien
sealed class DetailPasienUiState {
    data class Success(val pasien: PasienResponseDetail) : DetailPasienUiState()
    object Error : DetailPasienUiState()
    object Loading : DetailPasienUiState()
}

class DetailPasienViewModel(
    savedStateHandle: SavedStateHandle,
    private val pasien :  PasienRepository
) : ViewModel() {
    private val id_hewan: String = checkNotNull(savedStateHandle["id_hewan"])
    var detailPasienUiState: DetailPasienUiState by mutableStateOf(DetailPasienUiState.Loading)
        private set

    init {
        getPasienById()
    }

    fun getPasienById() {
        viewModelScope.launch {
            detailPasienUiState = DetailPasienUiState.Loading
            detailPasienUiState = try {
                val pasien = pasien.getPasienById(id_hewan)
                DetailPasienUiState.Success(pasien)
            } catch (e: Exception) {
                DetailPasienUiState.Error
            }
        }
    }
}
