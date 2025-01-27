package com.example.a5_066.service

import com.example.a5_066.model.Dokter
import com.example.a5_066.model.DokterResponse
import com.example.a5_066.model.DokterResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DokterService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    // Menampilkan daftar pasien
    @GET("bacadokter.php")
    suspend fun getDokter(): DokterResponse

    // Menampilkan detail pasien berdasarkan ID
    @GET("baca1dokter.php")
    suspend fun getDokterById(@Path("id_dokter") id: String): DokterResponseDetail

    // Menambahkan data pasien baru
    @POST("insertdokter.php")
    suspend fun insertDokter(@Body dokter: Dokter)

    // Memperbarui data pasien
    @PUT("editdokter.php")
    suspend fun updateDokter(@Path("id_dokter") id: String, @Body dokter: Dokter)

    // Menghapus data pasien berdasarkan ID
    @DELETE("deletedokter.php/{id_dokter}")
    suspend fun deleteDokter(@Query("id_dokter") id: String): Response<Void>
}