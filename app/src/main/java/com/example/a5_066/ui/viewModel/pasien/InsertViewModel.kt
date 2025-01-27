import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5_066.model.JenisHewan
import kotlinx.coroutines.launch

class InsertViewModel(
    private val pasien: PasienRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertPasienUiState())
        private set
    var jenisList by mutableStateOf<List<JenisHewan>>(emptyList())

    fun updateInsertPasienState(insertUiEvent: InsertPasienUiEvent) {
        uiState = uiState.copy(insertPasienUiEvent = insertUiEvent)
    }

    fun insertPasien() {
        viewModelScope.launch {
            try {
                if (validatePasienData(uiState.insertPasienUiEvent)) {
                    pasien.insertPasien(uiState.insertPasienUiEvent.toPasien())
                } else {
                    println("Data pasien tidak valid")
                }
            } catch (e: Exception) {
                e.printStackTrace() // Menangani exception
            }
        }
    }

    private fun validatePasienData(uiEvent: InsertPasienUiEvent): Boolean {
        return uiEvent.id_hewan.isNotEmpty() &&
                uiEvent.nama_hewan.isNotEmpty() &&
                uiEvent.jenis_hewan_id.isNotEmpty() // Sesuaikan validasi lain
    }
}

data class InsertPasienUiState(
    val insertPasienUiEvent: InsertPasienUiEvent = InsertPasienUiEvent()
)

data class InsertPasienUiEvent(
    val id_hewan: String = "",
    val nama_hewan: String = "",
    val jenis_hewan_id: String = "",
    val pemilik: String = "",
    val kontak_pemilik: String = "",
    val tanggal_lahir: String = "",
    val catatan_kesehatan: String = ""
)

fun Pasien.toUiStatePsn(): InsertPasienUiState = InsertPasienUiState(
    insertPasienUiEvent = toInsertUiEvent()
)

fun Pasien.toInsertUiEvent(): InsertPasienUiEvent = InsertPasienUiEvent(
    id_hewan = id_hewan,
    nama_hewan = nama_hewan,
    jenis_hewan_id = jenis_hewan_id,
    pemilik = pemilik,
    kontak_pemilik = kontak_pemilik,
    tanggal_lahir = tanggal_lahir,
    catatan_kesehatan = catatan_kesehatan
)

fun InsertPasienUiEvent.toPasien(): Pasien = Pasien(
    id_hewan = id_hewan,
    nama_hewan = nama_hewan,
    jenis_hewan_id = jenis_hewan_id,
    pemilik = pemilik,
    kontak_pemilik = kontak_pemilik,
    tanggal_lahir = tanggal_lahir,
    catatan_kesehatan = catatan_kesehatan
)
