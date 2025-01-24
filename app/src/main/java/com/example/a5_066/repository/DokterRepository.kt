package com.example.a5_066.repository

import com.example.a5_066.model.Dokter
import com.example.a5_066.service.DokterService
import com.example.a5_066.service.PasienService
import java.io.IOException

interface DokterRepository {
    suspend fun getDokter(): List<Dokter>
    suspend fun getDokterById(id: String): Dokter
    suspend fun insertDokter(dokter: Dokter)
    suspend fun updateDokter(id: String, dokter: Dokter)
    suspend fun deleteDokter(id: String)
}

class NetworkDokterRepository(
    private val dokterApiService: DokterService
) : DokterRepository {

    override suspend fun insertDokter(dokter: Dokter) {
        dokterApiService.insertDokter(dokter)
    }

    override suspend fun updateDokter(id: String, dokter: Dokter) {
        dokterApiService.updateDokter(id,dokter)
    }

    override suspend fun deleteDokter(id: String) {
        try {
            val response = dokterApiService.deleteDokter(id)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete dokter. HTTP Status Code: " +
                            "${response.code()}"
                )
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }
    override suspend fun getDokter(): List<Dokter> = dokterApiService.getDokter()
    override suspend fun getDokterById(id: String): Dokter {
        return dokterApiService.getDokterById(id)
    }
}