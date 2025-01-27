package com.example.a5_066.ui.viewModel.perawatan

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.Perawatan
import com.example.a5_066.repository.PerawatanRepository
import kotlinx.coroutines.launch

class InsertPerawatanViewModel(
    private val perawatan: PerawatanRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertPerawatanUiState())
        private set

    fun updateInsertPerawatanState(insertPerawatanUiEvent: InsertPerawatanUiEvent) {
        uiState = InsertPerawatanUiState(insertPerawatanUiEvent = insertPerawatanUiEvent)
    }

    suspend fun insertPerawatan() {
        viewModelScope.launch {
            try {
                perawatan.insertPerawatan(uiState.insertPerawatanUiEvent.toPerawatan())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertPerawatanUiState(
    val insertPerawatanUiEvent: InsertPerawatanUiEvent = InsertPerawatanUiEvent()
)

data class InsertPerawatanUiEvent(
    val id_perawatan: String = "",
    val id_hewan: String = "",
    val id_dokter: String = "",
    val tanggal_perawatan: String = "",
    val detail_perawatan: String = ""
)

fun InsertPerawatanUiEvent.toPerawatan(): Perawatan = Perawatan(
    id_perawatan = id_perawatan,
    id_hewan = id_hewan,
    id_dokter = id_dokter,
    tanggal_perawatan = tanggal_perawatan,
    detail_perawatan = detail_perawatan
)

fun Perawatan.toUiStatePr(): InsertPerawatanUiState = InsertPerawatanUiState(
    insertPerawatanUiEvent = toInsertPerawatanUiEvent()
)

fun Perawatan.toInsertPerawatanUiEvent(): InsertPerawatanUiEvent = InsertPerawatanUiEvent(
    id_perawatan = id_perawatan,
    id_hewan = id_hewan,
    id_dokter = id_dokter,
    tanggal_perawatan = tanggal_perawatan,
    detail_perawatan = detail_perawatan
)
