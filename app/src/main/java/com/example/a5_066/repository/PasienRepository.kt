import com.example.a5_066.service.PasienService
import java.io.IOException

interface PasienRepository {
    suspend fun getPasien(): PasienResponse
    suspend fun getPasienById(id_hewan: String): PasienResponseDetail
    suspend fun insertPasien(pasien: Pasien)
    suspend fun updatePasien(id_hewan: String, pasien: Pasien)
    suspend fun deletePasien(id_hewan: String)
}

class NetworkPasienRepository(
    private val pasienApiService: PasienService
) : PasienRepository {

    override suspend fun insertPasien(pasien: Pasien) {
        pasienApiService.insertPasien(pasien) // Pastikan API call ini benar
    }

    override suspend fun updatePasien(id_hewan: String, pasien: Pasien) {
        pasienApiService.updatePasien(id_hewan, pasien)
    }

    override suspend fun deletePasien(id_hewan: String) {
            val response = pasienApiService.deletePasien(id_hewan)
            if (!response.isSuccessful) {
                throw IOException("Gagal menghapus pasien. Kode Status HTTP: ${response.code()}")
            } else {
                println(response.message())
            }
        }

    override suspend fun getPasien(): PasienResponse {
        return pasienApiService.getPasien()
    }

    override suspend fun getPasienById(id_hewan: String): PasienResponseDetail {
        return pasienApiService.getPasienById(id_hewan)
    }
}
