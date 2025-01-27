package com.example.a5_066.ui.viewModel.perawatan

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.Perawatan
import com.example.a5_066.model.PerawatanResponse
import com.example.a5_066.repository.PerawatanRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomePrUiState {
    data class Success(val perawatan: List<Perawatan>) : HomePrUiState()
    object Error : HomePrUiState()
    object Loading : HomePrUiState()
}

class PerawatanViewModel(private val perawatan: PerawatanRepository) : ViewModel() {
    var perawatanUIState: HomePrUiState by mutableStateOf(HomePrUiState.Loading)
        private set

    init {
        getPerawatan()  // Ganti dengan ID Hewan yang sesuai
    }

    // Mendapatkan riwayat perawatan
    fun getPerawatan() {
        viewModelScope.launch {
            perawatanUIState = HomePrUiState.Loading
            perawatanUIState = try {
                HomePrUiState.Success(perawatan.getPerawatan().data)  // Mendapatkan daftar perawatan hewan
            } catch (e: IOException) {
                Log.e("PerawatanViewModel", "Kesalahan jaringan", e)
                HomePrUiState.Error  // Menangani error jaringan
            } catch (e: HttpException) {
                Log.e("PerawatanViewModel", "Kesalahan HTTP", e)
                HomePrUiState.Error  // Menangani error HTTP
            }
        }
    }

    // Menghapus perawatan berdasarkan ID
    fun deletePerawatan(id_perawatan: String) {
        viewModelScope.launch {
            try {
                perawatan.deletePerawatan(id_perawatan)  // Memanggil metode delete di repository
                getPerawatan()  // Refresh data setelah penghapusan
            } catch (e: IOException) {
                Log.e("PerawatanViewModel", "Kesalahan jaringan saat menghapus perawatan", e)
                perawatanUIState =
                    HomePrUiState  .Error  // Setel status error ketika terjadi exception
            } catch (e: HttpException) {
                Log.e("PerawatanViewModel", "Kesalahan HTTP saat menghapus perawatan", e)
                perawatanUIState =
                    HomePrUiState.Error  // Setel status error ketika terjadi HttpException
            }
        }
    }
}