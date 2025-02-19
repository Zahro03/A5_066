package com.example.a5_066.service

import Pasien
import PasienResponse
import PasienResponseDetail
import retrofit2.Response
import retrofit2.http.*

interface PasienService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    // Menampilkan daftar pasien
    @GET("bacapasien.php")
    suspend fun getPasien(): PasienResponse

    // Menampilkan detail pasien berdasarkan ID
    @GET("baca1pasien.php")
    suspend fun getPasienById(@Query("id_hewan") id_hewan: String): PasienResponseDetail

    // Menambahkan data pasien baru
    @POST("insertpasien.php")
    suspend fun insertPasien(@Body pasien: Pasien): Pasien

    // Memperbarui data pasien
    @PUT("editpasien.php")
    suspend fun updatePasien(@Query("id_hewan") id_hewan: String, @Body pasien: Pasien)

    // Menghapus data pasien berdasarkan ID
    @DELETE("deletepasien.php/{id_hewan}")
    suspend fun deletePasien(@Query("id_hewan") id_hewan: String): Response<Void>
}

