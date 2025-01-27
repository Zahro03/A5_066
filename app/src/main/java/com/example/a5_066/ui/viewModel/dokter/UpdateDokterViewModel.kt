import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.Dokter
import com.example.a5_066.model.DokterResponseDetail
import com.example.a5_066.repository.DokterRepository
import com.example.a5_066.ui.viewModel.dokter.InsertDokterUiEvent
import com.example.a5_066.ui.viewModel.dokter.InsertDokterUiState
import com.example.a5_066.ui.viewModel.dokter.toDokter
import com.example.a5_066.ui.viewModel.dokter.toUiStateDokter
import kotlinx.coroutines.launch

class UpdateDokterViewModel(
    savedStateHandle: SavedStateHandle,
    private val dokterRepository: DokterRepository
) : ViewModel() {
    var updateDokterUiState by mutableStateOf(InsertDokterUiState())
        private set
    val id_Dokter: String = checkNotNull(savedStateHandle["id_Dokter"])

    init {
        viewModelScope.launch {
            try {
                // Mendapatkan DokterResponseDetail
                val dokterResponseDetail: DokterResponseDetail = dokterRepository.getDokterById(id_Dokter)

                // Akses data Dokter dari DokterResponseDetail
                val dokter: Dokter = dokterResponseDetail.data

                // Mengonversi Dokter menjadi InsertDokterUiState
                updateDokterUiState = dokter.toUiStateDokter() // Menggunakan konversi yang sudah ada
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInsertDokterState(insertUiEvent: InsertDokterUiEvent) {
        updateDokterUiState = updateDokterUiState.copy(insertDokterUiEvent = insertUiEvent)
    }

    suspend fun updateDokter() {
        try {
            dokterRepository.updateDokter(id_Dokter, updateDokterUiState.insertDokterUiEvent.toDokter())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
