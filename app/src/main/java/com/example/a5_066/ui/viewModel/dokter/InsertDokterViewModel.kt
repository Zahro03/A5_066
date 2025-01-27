package com.example.a5_066.ui.viewModel.dokter

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.Dokter
import com.example.a5_066.repository.DokterRepository
import kotlinx.coroutines.launch

class InsertDokterViewModel(
    private val dokterRepository: DokterRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertDokterUiState())
        private set

    fun updateInsertDokterState(insertDokterUiEvent: InsertDokterUiEvent) {
        uiState = InsertDokterUiState(insertDokterUiEvent = insertDokterUiEvent)
    }

    fun insertDokter() {
        viewModelScope.launch {
            try {
                dokterRepository.insertDokter(uiState.insertDokterUiEvent.toDokter())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertDokterUiState(
    val insertDokterUiEvent: InsertDokterUiEvent = InsertDokterUiEvent()
)

data class InsertDokterUiEvent(
    val id_Dokter: String = "",
    val nama_dokter: String = "",
    val spesialisasi: String = "",
    val kontak: String = ""
)

fun InsertDokterUiEvent.toDokter(): Dokter = Dokter(
    id_Dokter = id_Dokter,
    nama_dokter = nama_dokter,
    spesialisasi = spesialisasi,
    kontak = kontak
)

fun Dokter.toUiStateDokter(): InsertDokterUiState = InsertDokterUiState(
    insertDokterUiEvent = toInsertDokterUiEvent()
)

fun Dokter.toInsertDokterUiEvent(): InsertDokterUiEvent = InsertDokterUiEvent(
    id_Dokter = id_Dokter,
    nama_dokter = nama_dokter,
    spesialisasi = spesialisasi,
    kontak = kontak
)
