package com.example.a5_066.repository

import com.example.a5_066.model.JenisHewan
import com.example.a5_066.model.JenisHewanResponse
import com.example.a5_066.model.JenisHewanResponseDetail
import com.example.a5_066.service.JenisHewanService
import kotlinx.coroutines.flow.Flow
import okio.IOException

interface JenisHewanRepository{
    suspend fun getJenisHewan(): JenisHewanResponse
    suspend fun insertJenisHewan(jenisHewan: JenisHewan)
    suspend fun updateJenisHewan(id_jenis_hewan: String,jenisHewan: JenisHewan)
    suspend fun deleteJenisHewan(id_jenis_hewan: String)
    suspend fun getJenisHewanById(id_jenis_hewan: String): JenisHewanResponseDetail
}

class NetworkJenisHewanRepository(
    private val jenisHewanApiService: JenisHewanService
): JenisHewanRepository{
    override suspend fun insertJenisHewan(jenisHewan: JenisHewan){
        jenisHewanApiService.insertJenisHewan(jenisHewan)
    }

    override suspend fun updateJenisHewan(id_jenis_hewan: String,jenisHewan: JenisHewan) {
        jenisHewanApiService.updateJenisHewan(id_jenis_hewan, jenisHewan)
    }

    override suspend fun deleteJenisHewan(id_jenis_hewan: String) {
        val response = jenisHewanApiService.deleteJenisHewan(id_jenis_hewan)
        if (!response.isSuccessful){
            throw java.io.IOException(
                "Failed to delete jenisHewan.HTTP Status code:" +
                        "${response.code()}"
            )
        } else {
            println(response.message())
        }
    }

    override suspend fun getJenisHewan(): JenisHewanResponse {
        return jenisHewanApiService.getJenisHewan()
    }
    override suspend fun getJenisHewanById(id_jenis_hewan: String): JenisHewanResponseDetail {
        return jenisHewanApiService.getJenisHewanById(id_jenis_hewan)
    }
}



