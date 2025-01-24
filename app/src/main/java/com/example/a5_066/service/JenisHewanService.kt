package com.example.a5_066.service

import com.example.a5_066.model.JenisHewan
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @POST("insertjenisHewan.php")
    suspend fun insertJenisHewan(
        @Body jenisHewan: JenisHewan):JenisHewan

    @PUT("editjenisHewan.php")
    suspend fun updateJenisHewan(
        @Query("id_jenis_hewan") id: String,@Body jenisHewan: JenisHewan
    )

    @DELETE("deletejenisHewan.php/{id_jenis_hewan}")
    suspend fun deleteJenisHewan(@Query("id_jenis_hewan")id: String):retrofit2.Response<Void>
}