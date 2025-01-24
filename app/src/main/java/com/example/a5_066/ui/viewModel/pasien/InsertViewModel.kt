package com.example.a5_066.ui.viewModel.pasien

import Pasien
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.repository.PasienRepository
import kotlinx.coroutines.launch
class InsertViewModel (
    private val pasien: PasienRepository
) : ViewModel() {
    private val _uiState = mutableStateOf(InsertUiState())
    val uiState: State<InsertUiState> = _uiState

    fun updateInsertPasienState(insertUiEvent: InsertUiEvent) {
        _uiState.value = InsertUiState(insertUiEvent = insertUiEvent)
    }

    fun insertPasien() {
        viewModelScope.launch {
            try {
                pasien.insertPasien(_uiState.value.insertUiEvent.toPasien())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val id_hewan: String = "",
    val nama_hewan: String = "",
    val jenis_hewan_id: String = "",
    val pemilik: String = "",
    val kontak_pemilik: String = "",
    val tanggal_lahir: String = "",
    val catatan_kesehatan: String = ""
)

fun InsertUiEvent.toPasien(): Pasien = Pasien(
    id_hewan = id_hewan,
    nama_hewan = nama_hewan,
    jenis_hewan_id = jenis_hewan_id,
    pemilik = pemilik,
    kontak_pemilik = kontak_pemilik,
    tanggal_lahir = tanggal_lahir,
    catatan_kesehatan = catatan_kesehatan
)

fun Pasien.toUiStatePasien(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Pasien.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_hewan = id_hewan,
    nama_hewan = nama_hewan,
    jenis_hewan_id = jenis_hewan_id,
    pemilik = pemilik,
    kontak_pemilik = kontak_pemilik,
    tanggal_lahir = tanggal_lahir,
    catatan_kesehatan = catatan_kesehatan
)
