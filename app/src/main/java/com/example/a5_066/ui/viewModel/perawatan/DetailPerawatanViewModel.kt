package com.example.a5_066.ui.viewModel.perawatan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.Perawatan
import com.example.a5_066.repository.PerawatanRepository
import kotlinx.coroutines.launch

sealed class DetailPrUiState {
    data class Success(val perawatan: Perawatan) : DetailPrUiState()
    object Error : DetailPrUiState()
    object Loading : DetailPrUiState()
}

class DetailPerawatanViewModel(
    savedStateHandle: SavedStateHandle,
    private val perawatanRepository: PerawatanRepository
) : ViewModel() {
    private val id_perawatan: String = checkNotNull(savedStateHandle["id_perawatan"])
    var detailPrUiState: DetailPrUiState by mutableStateOf(DetailPrUiState.Loading)
        private set

    init {
        getPerawatanById()
    }

    // Fungsi untuk mengambil data perawatan berdasarkan id_perawatan
    fun getPerawatanById() {
        viewModelScope.launch {
            detailPrUiState = DetailPrUiState.Loading
            detailPrUiState = try {
                val responseDetail = perawatanRepository.getPerawatanById(id_perawatan)
                DetailPrUiState.Success(responseDetail.data)
            } catch (e: Exception) {
                DetailPrUiState.Error
            }
        }
    }
}

