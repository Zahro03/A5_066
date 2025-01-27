package com.example.a5_066.service

import com.example.a5_066.model.JenisHewan
import com.example.a5_066.model.JenisHewanResponse
import com.example.a5_066.model.JenisHewanResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface JenisHewanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("bacajenisHewan.php")
    suspend fun getJenisHewan(): JenisHewanResponse

    @GET("bacajenisHewan1.php")
    suspend fun getJenisHewanById(
        @Path("id_jenis_hewan") id_jenis_hewan: String): JenisHewanResponseDetail

    @POST("insertjenisHewan.php")
    suspend fun insertJenisHewan(@Body jenisHewan: JenisHewan)

    @PUT("editjenisHewan.php")
    suspend fun updateJenisHewan(
        @Path("id_jenis_hewan") id_jenis_hewan: String, @Body jenisHewan: JenisHewan)

    @DELETE("deletejenisHewan.php/{id_jenis_hewan}")
    suspend fun deleteJenisHewan(@Path("id_jenis_hewan")id_jenis_hewan: String): Response<Void>
}