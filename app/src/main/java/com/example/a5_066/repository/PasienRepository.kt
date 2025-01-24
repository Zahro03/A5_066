package com.example.a5_066.repository

import Pasien
import com.example.a5_066.service.PasienService
import java.io.IOException

interface PasienRepository {
    suspend fun getPasien(): List<Pasien>
    suspend fun getPasienById(id: String): Pasien
    suspend fun insertPasien(pasien: Pasien)
    suspend fun updatePasien(id: String, pasien: Pasien)
    suspend fun deletePasien(id: String)
}

class NetworkPasienRepository(
    private val pasienApiService: PasienService
) : PasienRepository {

    override suspend fun insertPasien(pasien: Pasien) {
        pasienApiService.insertPasien(pasien)
    }

    override suspend fun updatePasien(id: String, pasien: Pasien) {
        pasienApiService.updatePasien(id,pasien)
    }

    override suspend fun deletePasien(id: String) {
        try {
            val response = pasienApiService.deletePasien(id)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete pasien. HTTP Status Code: " +
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
    override suspend fun getPasien(): List<Pasien> = pasienApiService.getPasien()
    override suspend fun getPasienById(id: String): Pasien {
        return pasienApiService.getPasienById(id)
    }
}
