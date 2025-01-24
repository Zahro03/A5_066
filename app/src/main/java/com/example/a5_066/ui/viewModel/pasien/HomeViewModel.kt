package com.example.a5_066.ui.viewModel.pasien

import Pasien
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.repository.PasienRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState {
    data class Success(val pasien: List<Pasien>) : HomeUiState()
    data class Error(val retryAction: () -> Unit) : HomeUiState()  // Add retryAction to Error
    object Loading : HomeUiState()
}


class PasienViewModel(private val pasien: PasienRepository) : ViewModel() {
    var pasienUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPasien()
    }

    fun getPasien() {
        viewModelScope.launch {
            pasienUiState = HomeUiState.Loading
            pasienUiState = try {
                HomeUiState.Success(pasien.getPasien())
            } catch (e: IOException) {
                Log.e("HomeViewModel", "Kesalahan Jaringan", e)
                HomeUiState.Error { getPasien() }  // Pass retry action here
            } catch (e: HttpException) {
                Log.e("HomeViewModel", "Kesalahan HTTP", e)
                HomeUiState.Error { getPasien() }  // Pass retry action here
            }
        }
    }

    fun deletePasien(id_Hewan: String) {
        viewModelScope.launch {
            try {
                pasien.deletePasien(id_Hewan)
                getPasien()
            } catch (e: IOException) {
                Log.e("HomeViewModel", "Terjadi kesalahan jaringan saat menghapus pasien", e)
                pasienUiState = HomeUiState.Error { getPasien() }  // Pass retry action here
            } catch (e: HttpException) {
                Log.e("HomeViewModel", "Terjadi kesalahan HTTP saat menghapus pasien", e)
                pasienUiState = HomeUiState.Error { getPasien() }  // Pass retry action here
            }
        }
    }
}

