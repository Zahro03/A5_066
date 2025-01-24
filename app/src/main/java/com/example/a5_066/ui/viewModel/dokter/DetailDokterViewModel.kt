package com.example.a5_066.ui.viewModel.dokter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.Dokter
import com.example.a5_066.repository.DokterRepository
import com.example.a5_066.ui.view.dokter.DestinasiDetail
import kotlinx.coroutines.launch

class DetailDokterViewModel(
    savedStateHandle: SavedStateHandle,
    private val dokterRepository: DokterRepository
) : ViewModel() {
    private val idDokter: String = checkNotNull(savedStateHandle[DestinasiDetail.id_Dokter])
    var detailDokterUiState: DetailDokterUiState by mutableStateOf(DetailDokterUiState())
        private set

    init {
        getDokterById()
    }

    fun getDokterById() {
        viewModelScope.launch {
            detailDokterUiState = detailDokterUiState.copy(isLoading = true)
            try {
                val result = dokterRepository.getDokterById(idDokter)
                detailDokterUiState = detailDokterUiState.copy(
                    detailUiEvent = result.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailDokterUiState = detailDokterUiState.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }

    fun deleteDokter() {
        viewModelScope.launch {
            detailDokterUiState = detailDokterUiState.copy(isLoading = true)
            try {
                dokterRepository.deleteDokter(idDokter)
                detailDokterUiState = detailDokterUiState.copy(isLoading = false)
            } catch (e: Exception) {
                detailDokterUiState = detailDokterUiState.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}

data class DetailDokterUiState(
    val detailUiEvent: DokterUiEvent = DokterUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

fun Dokter.toDetailUiEvent(): DokterUiEvent {
    return DokterUiEvent(
        id_Dokter = id_Dokter,
        nama_Dokter = nama_dokter,
        spesialisasi = spesialisasi,
        kontak = kontak
    )
}

data class DokterUiEvent(
    val id_Dokter: String = "",
    val nama_Dokter: String = "",
    val spesialisasi: String = "",
    val kontak: String = ""
)
