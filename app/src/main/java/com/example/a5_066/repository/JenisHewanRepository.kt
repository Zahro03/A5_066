package com.example.a5_066.repository

import com.example.a5_066.model.JenisHewan
import kotlinx.coroutines.flow.Flow
import okio.IOException

interface JenisHewanRepository{
    suspend fun getJenisHewan(): List<JenisHewan>
    suspend fun insertJenisHewan(jenisHewan: JenisHewan)
    suspend fun updateJenisHewan(id: String,jenisHewan: JenisHewan)
    suspend fun deleteJenisHewan(id: String)
    suspend fun getJenisHewanById(id: String):JenisHewan
}

class NetworkJenisHewanRepository(
    private val JenisHewanApiService: JenisHewanService
): JenisHewanRepository{
    override suspend fun insertJenisHewan(jenisHewan: JenisHewan){
        JenisHewanApiService.insertJenisHewan(jenisHewan)
    }

    override suspend fun updateJenisHewan(id: String,jenisHewan: JenisHewan) {
        JenisHewanApiService.updateJenisHewan(id, jenisHewan)
    }

    override suspend fun deleteJenisHewan(id: String) {
        try {
            val response = JenisHewanApiService.deleteJenisHewan(id)
            if (!response.isSuccessful){
                throw java.io.IOException(
                    "Failed to delete jenisHewan.HTTP Status code:" +
                            "${response.code()}"
                )
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getJenisHewan(): List<JenisHewan> = JenisHewanApiService.getJenisHewan()
    override suspend fun getJenisHewanById(id: String): JenisHewan {
        return JenisHewanApiService.getMahasiwaById(id)
    }
}



