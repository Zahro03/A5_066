package com.example.a5_066.ui.viewModel.dokter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.Dokter
import com.example.a5_066.repository.DokterRepository
import kotlinx.coroutines.launch

class InsertDokterViewModel (
    private val dokter: DokterRepository
) : ViewModel() {
    private val _uiState = mutableStateOf(InsertUiState())
    val uiState: State<InsertUiState> = _uiState

    fun updateInsertDokterState(insertUiEvent: InsertUiEvent) {
        _uiState.value = InsertUiState(insertUiEvent = insertUiEvent)
    }

    fun insertDokter() {
        viewModelScope.launch {
            try {
                dokter.insertDokter(_uiState.value.insertUiEvent.toDokter())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false
)

data class InsertUiEvent(
    val id_Dokter: String = "",
    val nama_dokter: String = "",
    val spesialisasi: String = "",
    val kontak: String = ""
)

fun InsertUiEvent.toDokter(): Dokter = Dokter(
    id_Dokter = id_Dokter,
    nama_dokter = nama_dokter,
    spesialisasi = spesialisasi,
    kontak = kontak
)

fun Dokter.toUiStateDokter(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Dokter.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_Dokter = id_Dokter,
    nama_dokter = nama_dokter,
    spesialisasi = spesialisasi,
    kontak = kontak
)