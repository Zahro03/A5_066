package com.example.a5_066.repository

import com.example.a5_066.model.Perawatan
import com.example.a5_066.service.PerawatanService
import java.io.IOException

interface PerawatanRepository {
    suspend fun getRiwayatPerawatan(idHewan: String): List<Perawatan>
    suspend fun insertPerawatan(perawatan: Perawatan)
    suspend fun updatePerawatan(idPerawatan: String, perawatan: Perawatan)
    suspend fun deletePerawatan(idPerawatan: String)
}

class NetworkPerawatanRepository(
    private val perawatanApiService: PerawatanService
) : PerawatanRepository {

    override suspend fun insertPerawatan(perawatan: Perawatan) {
        try {
            perawatanApiService.insertPerawatan(perawatan)
        } catch (e: Exception) {
            throw IOException("Gagal mencatat perawatan: ${e.message}")
        }
    }

    override suspend fun updatePerawatan(idPerawatan: String, perawatan: Perawatan) {
        try {
            perawatanApiService.updatePerawatan(idPerawatan, perawatan)
        } catch (e: Exception) {
            throw IOException("Gagal mengedit perawatan: ${e.message}")
        }
    }

    override suspend fun deletePerawatan(idPerawatan: String) {
        try {
            val response = perawatanApiService.deletePerawatan(idPerawatan)
            if (!response.isSuccessful) {
                throw IOException("Gagal menghapus perawatan. HTTP Status Code: ${response.code()}")
            }
        } catch (e: Exception) {
            throw IOException("Gagal menghapus perawatan: ${e.message}")
        }
    }

    override suspend fun getRiwayatPerawatan(idHewan: String): List<Perawatan> {
        return try {
            perawatanApiService.getRiwayatPerawatan(idHewan)
        } catch (e: Exception) {
            throw IOException("Gagal mendapatkan riwayat perawatan: ${e.message}")
        }
    }
}