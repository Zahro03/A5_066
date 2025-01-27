import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.a5_066.model.JenisHewan
import com.example.a5_066.repository.JenisHewanRepository
import kotlinx.coroutines.launch
import java.io.IOException

class HomePasienViewModel(
    private val pasien: PasienRepository,
) : ViewModel() {
    var pasienUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPasien()
    }

    fun getPasien() {
        viewModelScope.launch {
            pasienUiState = HomeUiState.Loading
            pasienUiState = try {
                HomeUiState.Success(pasien.getPasien().data) // Ensure this returns the correct type
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching patients", e)
                HomeUiState.Error // Handle network error
            } catch (e: HttpException) {
                Log.e("HomeViewModel", "HTTP error", e)
                HomeUiState.Error // Handle HTTP error
            }
        }
    }
    // Delete patient by animal ID
    fun deletePasien(id_hewan: String) {
        viewModelScope.launch {
            try {
                pasien.deletePasien(id_hewan)
                getPasien()
            } catch (e: IOException) {
                Log.e("HomeViewModel", "Network error", e)
                pasienUiState = HomeUiState.Error
            } catch (e: HttpException) {
                Log.e("HomeViewModel", "HTTP error", e)
                pasienUiState = HomeUiState.Error
            }
        }
    }
}

sealed class HomeUiState {
    data class Success(val pasien: List<Pasien>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}