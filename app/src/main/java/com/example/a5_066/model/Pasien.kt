import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pasien(
    val id_hewan: String,
    val nama_hewan: String,
    val jenis_hewan_id: String,
    val pemilik: String,
    val kontak_pemilik: String,
    val tanggal_lahir: String,
    val catatan_kesehatan: String
)

@Serializable
data class PasienResponse(
    val status : Boolean,
    val message : String,
    val data : List<Pasien>
)

@Serializable
data class PasienResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Pasien
) {
    // Fungsi untuk mengonversi Pasien menjadi InsertPasienUiState
    fun toUiStatePsn(): InsertPasienUiState {
        return InsertPasienUiState(
            insertPasienUiEvent = InsertPasienUiEvent(
                id_hewan = data.id_hewan,
                nama_hewan = data.nama_hewan,
                jenis_hewan_id = data.jenis_hewan_id,
                pemilik = data.pemilik,
                kontak_pemilik = data.kontak_pemilik,
                tanggal_lahir = data.tanggal_lahir,
                catatan_kesehatan = data.catatan_kesehatan
            )
        )
    }
}
