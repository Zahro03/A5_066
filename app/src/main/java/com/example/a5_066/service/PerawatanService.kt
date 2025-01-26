package com.example.a5_066.service

import com.example.a5_066.model.Perawatan
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PerawatanService {

    // Mendapatkan riwayat perawatan berdasarkan id_hewan
    @GET("perawatan/bacaperawatan/{id_hewan}")
    suspend fun getRiwayatPerawatan(@Path("id_hewan") id_hewan: String): List<Perawatan>

    // Menambahkan perawatan baru (insertperawatan)
    @POST("perawatan/insertperawatan")
    suspend fun insertPerawatan(@Body perawatan: Perawatan): Response<Unit>

    // Mengupdate perawatan berdasarkan id_perawatan
    @PUT("perawatan/editperawatan/{idPerawatan}")
    suspend fun updatePerawatan(
        @Path("idPerawatan") idPerawatan: String,
        @Body perawatan: Perawatan
    )

    // Menghapus perawatan berdasarkan id_perawatan
    @DELETE("perawatan/deleteperawatan/{idPerawatan}")
    suspend fun deletePerawatan(@Path("idPerawatan") idPerawatan: String): Response<Unit>
}