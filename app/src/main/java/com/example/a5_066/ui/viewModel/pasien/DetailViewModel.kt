package com.example.a5_066.ui.viewModel.pasien

import Pasien
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.repository.PasienRepository
import com.example.a5_066.ui.view.dokter.DestinasiDetail
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val pasienRepository: PasienRepository
) : ViewModel() {

    // Mengambil id_hewan dari SavedStateHandle sesuai dengan parameter yang ada di rute
    private val id_hewan: String = checkNotNull(savedStateHandle[DestinasiDetailPasien.id_hewan]) // Menggunakan DestinasiDetailPasien.id_hewan

    var detailPasienUiState: DetailPasienUiState by mutableStateOf(DetailPasienUiState())
        private set

    init {
        getPasienById()
    }

    // Fungsi untuk mengambil data pasien berdasarkan id_hewan
    fun getPasienById() {
        viewModelScope.launch {
            detailPasienUiState = DetailPasienUiState(isLoading = true)
            try {
                val result = pasienRepository.getPasienById(id_hewan)
                detailPasienUiState = DetailPasienUiState(
                    detailUiEvent = result.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailPasienUiState = DetailPasienUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown"
                )
            }
        }
    }

    // Fungsi untuk menghapus pasien
    fun deletePasien() {
        viewModelScope.launch {
            detailPasienUiState = DetailPasienUiState(isLoading = true)
            try {
                pasienRepository.deletePasien(id_hewan)
                detailPasienUiState = DetailPasienUiState(isLoading = false)
            } catch (e: Exception) {
                detailPasienUiState = DetailPasienUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}


data class DetailPasienUiState(
    val detailUiEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean get() = detailUiEvent == InsertUiEvent()
    val isUiEventNotEmpty: Boolean get() = detailUiEvent != InsertUiEvent()
}

fun Pasien.toDetailUiEvent(): InsertUiEvent {
    return InsertUiEvent(
        id_hewan = id_hewan,
        nama_hewan = nama_hewan,
        jenis_hewan_id = jenis_hewan_id,
        pemilik = pemilik,
        kontak_pemilik = kontak_pemilik,
        tanggal_lahir = tanggal_lahir,
        catatan_kesehatan = catatan_kesehatan
    )
}
