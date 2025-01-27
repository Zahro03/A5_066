package com.example.a5_066.repository

import com.example.a5_066.model.Perawatan
import com.example.a5_066.model.PerawatanResponse
import com.example.a5_066.model.PerawatanResponseDetail
import com.example.a5_066.service.PerawatanService
import java.io.IOException

interface PerawatanRepository {
    suspend fun getPerawatan(): PerawatanResponse
    suspend fun getPerawatanById(idPerawatan: String): PerawatanResponseDetail
    suspend fun insertPerawatan(perawatan: Perawatan)
    suspend fun updatePerawatan(id_perawatan: String, perawatan: Perawatan)
    suspend fun deletePerawatan(id_perawatan: String)
}

class NetworkPerawatanRepository(
    private val perawatanApiService: PerawatanService
) : PerawatanRepository {

    override suspend fun insertPerawatan(perawatan: Perawatan) {
        return perawatanApiService.insertPerawatan(perawatan)
    }

    override suspend fun updatePerawatan(id_perawatan: String, perawatan: Perawatan) {
        return perawatanApiService.updatePerawatan(id_perawatan, perawatan)
    }

    override suspend fun deletePerawatan(id_perawatan: String) {
            val response = perawatanApiService.deletePerawatan(id_perawatan)
            if (!response.isSuccessful) {
                throw IOException("Gagal menghapus perawatan. HTTP Status Code: ${response.code()}")
            } else {
                println(response.message())
        }
    }

    override suspend fun getPerawatan(): PerawatanResponse {
        return perawatanApiService.getPerawatan()
    }

    override suspend fun getPerawatanById(id_perawatan: String): PerawatanResponseDetail {
        return perawatanApiService.getPerawatanById(id_perawatan)
    }
}