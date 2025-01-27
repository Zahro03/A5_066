package com.example.a5_066.ui.viewModel.jenisHewan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.JenisHewan
import com.example.a5_066.repository.JenisHewanRepository
import kotlinx.coroutines.launch

class UpdatejenisHewanViewModel(
    savedStateHandle: SavedStateHandle,
    private val jenisHewanRepository: JenisHewanRepository
): ViewModel(){
    var updateUiState by mutableStateOf(InsertJhUiState())
        private set

    private val id_jenis_hewan: String = checkNotNull(savedStateHandle[DestinasiUpdatePasien.id_jenis_hewan])

    init {
        viewModelScope.launch {
            updateUiState = jenisHewanRepository.getPasienById(id_jenis_hewan).data
                .toUiStatePsn()
        }
    }

    fun updateState(insertJhUiEvent: InsertJhUiEvent){
        updateUiState = InsertJhUiState(insertJhUiEvent = insertJhUiEvent)
    }

    suspend fun updateJenisHewan(){
        viewModelScope.launch {
            try {
                jenisHewanRepository.updateJenisHewan(id_jenis_hewan, updateUiState.insertPasienUiEvent.toJenisHewan())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
