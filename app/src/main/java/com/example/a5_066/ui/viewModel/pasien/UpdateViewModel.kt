import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.JenisHewan
import kotlinx.coroutines.launch

class UpdatePasienViewModel(
    savedStateHandle: SavedStateHandle,
    private val pasienRepository: PasienRepository
): ViewModel(){
    var updateUiState by mutableStateOf(InsertPasienUiState())
        private set

    var jenisList by mutableStateOf<List<JenisHewan>>(emptyList())

    private val _id_hewan: String = checkNotNull(savedStateHandle[DestinasiUpdatePasien.id_hewan])

    init {
        viewModelScope.launch {
            updateUiState = pasienRepository.getPasienById(_id_hewan).data
                .toUiStatePsn()
        }
    }

    fun updateState(insertUiEvent: InsertPasienUiEvent){
        updateUiState = InsertPasienUiState(insertPasienUiEvent = insertUiEvent)
    }

    suspend fun updatePasien(){
        viewModelScope.launch {
            try {
                pasienRepository.updatePasien(_id_hewan, updateUiState.insertPasienUiEvent.toPasien())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
