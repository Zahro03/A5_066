package com.example.a5_066.ui.viewModel.dokter

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.Dokter
import com.example.a5_066.repository.DokterRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState {
    data class Success(val dokter: List<Dokter>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class DokterViewModel(private val dokter : DokterRepository) : ViewModel() {
    var dokterUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getDokter()
    }

    fun getDokter() {
        viewModelScope.launch {
            dokterUiState = HomeUiState.Loading
            dokterUiState = try {
                HomeUiState.Success(dokter.getDokter())
            } catch (e: IOException) {
                Log.e("HomeViewModel", "Kesalahan Jaringan", e)
                HomeUiState.Error
            } catch (e: HttpException) {
                Log.e("HomeViewModel", "Kesalahan HTTP", e)
                HomeUiState.Error
            }
        }
    }

    fun deleteDokter(id_Dokter: String) {
        viewModelScope.launch {
            try {
                dokter.deleteDokter(id_Dokter)
                getDokter()
            } catch (e: IOException) {
                Log.e("HomeViewModel", "Terjadi kesalahan jaringan saat menghapus pasien", e)
                dokterUiState = HomeUiState.Error
            } catch (e: HttpException) {
                Log.e("HomeViewModel", "Terjadi kesalahan HTTP saat menghapus pasien", e)
                dokterUiState = HomeUiState.Error
            }
        }
    }
}