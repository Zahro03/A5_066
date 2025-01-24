import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pasien(
    val id_hewan: String,
    val nama_hewan: String,
    val jenis_hewan_id: String,
    val pemilik: String,

    @SerialName("kontak_pemilik")
    val kontak_pemilik: String,
    @SerialName("tanggal_lahir")
    val tanggal_lahir: String,
    @SerialName("catatan_kesehatan")
    val catatan_kesehatan: String
)

