package com.example.a5_066.repository

import com.example.a5_066.model.Dokter
import com.example.a5_066.model.DokterResponse
import com.example.a5_066.model.DokterResponseDetail
import com.example.a5_066.service.DokterService
import com.example.a5_066.ui.view.dokter.DestinasiUpdateDokter.id_Dokter
import java.io.IOException

interface DokterRepository {
    suspend fun getDokter(): DokterResponse
    suspend fun getDokterById(id_Dokter: String): DokterResponseDetail
    suspend fun insertDokter(dokter: Dokter)
    suspend fun updateDokter(id_Dokter: String, dokter: Dokter)
    suspend fun deleteDokter(id_Dokter: String)
}

class NetworkDokterRepository(
    private val dokterApiService: DokterService
) : DokterRepository {

    override suspend fun insertDokter(dokter: Dokter) {
        dokterApiService.insertDokter(dokter)
    }

    override suspend fun updateDokter(id: String, dokter: Dokter) {
        dokterApiService.updateDokter(id_Dokter,dokter)
    }

    override suspend fun deleteDokter(id_Dokter: String) {
            val response = dokterApiService.deleteDokter(id_Dokter)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete dokter. HTTP Status Code: " +
                            "${response.code()}"
                )
            } else {
                println(response.message())
            }
        }
    override suspend fun getDokter(): DokterResponse{
        return dokterApiService.getDokter()
    }
    override suspend fun getDokterById(id_Dokter: String): DokterResponseDetail {
        return dokterApiService.getDokterById(id_Dokter)
    }
}