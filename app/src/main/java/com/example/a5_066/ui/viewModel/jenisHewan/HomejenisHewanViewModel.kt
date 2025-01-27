package com.example.a5_066.ui.viewModel.jenisHewan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.JenisHewan
import com.example.a5_066.repository.JenisHewanRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Status UI untuk menampilkan data jenis hewan
sealed class JenisHewanUiState {
    data class Success(val jenisHewan: List<JenisHewan>) : JenisHewanUiState()
    object Error : JenisHewanUiState()
    object Loading : JenisHewanUiState()
}

class JenisHewanViewModel(private val jenisHewan: JenisHewanRepository
) : ViewModel() {

    // Menyimpan status UI
    var jenisHewanUiState: JenisHewanUiState by mutableStateOf(JenisHewanUiState.Loading)
        private set

    init {
        getJenisHewan()  // Memanggil fungsi untuk mengambil data jenis hewan
    }

    // Fungsi untuk mengambil data jenis hewan
    fun getJenisHewan() {
        viewModelScope.launch {
            jenisHewanUiState = JenisHewanUiState.Loading
            jenisHewanUiState = try {
                JenisHewanUiState.Success(jenisHewan.getJenisHewan().data)  // Mengirim data yang diterima
            } catch (e: IOException) {
                JenisHewanUiState.Error  // Menangani kesalahan jaringan
            } catch (e: HttpException) {
                JenisHewanUiState.Error  // Menangani kesalahan HTTP
            }
        }
    }

    // Fungsi untuk menghapus jenis hewan berdasarkan ID
    fun deleteJenisHewan(id_jenis_hewan: String) {
        viewModelScope.launch {
            try {
                jenisHewan.deleteJenisHewan(id_jenis_hewan)
            } catch (e: IOException) {
                jenisHewanUiState = JenisHewanUiState.Error  // Menangani kesalahan saat penghapusan
            } catch (e: HttpException) {
                jenisHewanUiState = JenisHewanUiState.Error  // Menangani kesalahan saat penghapusan
            }
        }
    }
}
