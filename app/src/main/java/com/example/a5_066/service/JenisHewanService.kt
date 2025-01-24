package com.example.a5_066.service

import com.example.a5_066.model.JenisHewan
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface JenisHewanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("bacajenisHewan.php")
    suspend fun getJenisHewan(): List<JenisHewan>

    @GET("bacajenisHewan1.php")
    suspend fun getJenisHewanById(
        @Query("id_jenis_hewan") id: String): JenisHewan

}