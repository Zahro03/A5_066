import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.repository.PerawatanRepository
import com.example.a5_066.ui.viewModel.perawatan.InsertPerawatanUiEvent
import com.example.a5_066.ui.viewModel.perawatan.InsertPerawatanUiState
import com.example.a5_066.ui.viewModel.perawatan.toPerawatan
import com.example.a5_066.ui.viewModel.perawatan.toUiStatePr
import kotlinx.coroutines.launch

class UpdatePerawatanViewModel(
    savedStateHandle: SavedStateHandle,
    private val perawatanRepository: PerawatanRepository
) : ViewModel() {
    var updatePerawatanUiState = mutableStateOf(InsertPerawatanUiState())
        private set
    val id_perawatan: String = checkNotNull(savedStateHandle["id_perawatan"])

    init {
        viewModelScope.launch {
            try {
                val perawatanResponseDetail = perawatanRepository.getPerawatanById(id_perawatan)
                val perawatan = perawatanResponseDetail.data
                updatePerawatanUiState.value = perawatan.toUiStatePr()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInsertPrState(insertPerawatanUiEvent: InsertPerawatanUiEvent) {
        updatePerawatanUiState.value = updatePerawatanUiState.value.copy(insertPerawatanUiEvent = insertPerawatanUiEvent)
    }

    suspend fun updatePerawatan() {
        try {
            // Mengonversi InsertPerawatanUiEvent menjadi Perawatan dan mengirimkan ke repository untuk update
            perawatanRepository.updatePerawatan(id_perawatan, updatePerawatanUiState.value.insertPerawatanUiEvent.toPerawatan())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
