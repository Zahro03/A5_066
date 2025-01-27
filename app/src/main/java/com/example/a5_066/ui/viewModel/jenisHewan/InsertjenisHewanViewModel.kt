package com.example.a5_066.ui.viewModel.jenisHewan

import androidx.compose.runtime.getValue
import com.example.a5_066.model.JenisHewan
import com.example.a5_066.repository.JenisHewanRepository
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// ViewModel class
class InsertJenisHewanViewModel(
    private val Jh: JenisHewanRepository
) : ViewModel() {

    // Properti untuk state UI
    var uiState by mutableStateOf(InsertUiState())
        private set

    // Update state InsertUiEvent
    fun updateInsertJhState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    // Fungsi untuk menyimpan data jenis hewan
    fun insertJenisHewan() {
        viewModelScope.launch {
            try {
                Jh.insertJenisHewan( uiState.insertUiEvent.toJh())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

// Data kelas untuk InsertUiState
data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

// Data kelas untuk InsertUiEvent
data class InsertUiEvent(
    val id_jenis_hewan: String = "",
    val nama_jenis_hewan: String = "",
    val deskripsi: String = ""
)

// Fungsi untuk konversi dari InsertUiEvent ke JenisHewan
fun InsertUiEvent.toJh(): JenisHewan = JenisHewan(
    id_jenis_hewan = id_jenis_hewan,
    nama_jenis_hewan = nama_jenis_hewan,
    deskripsi = deskripsi
)

// Fungsi untuk konversi dari JenisHewan ke InsertUiState
fun JenisHewan.toUiStateJh(): InsertUiState = InsertUiState(
    insertUiEvent = this.toInsertUiEvent() // Menggunakan fungsi toInsertUiEvent untuk konversi
)

// Fungsi konversi JenisHewan ke InsertUiEvent
fun JenisHewan.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_jenis_hewan = id_jenis_hewan,
    nama_jenis_hewan = nama_jenis_hewan,
    deskripsi = deskripsi
)
