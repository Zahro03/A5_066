package com.example.a5_066.service

import com.example.a5_066.model.Perawatan
import com.example.a5_066.model.PerawatanResponse
import com.example.a5_066.model.PerawatanResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PerawatanService {

    // Mendapatkan riwayat perawatan berdasarkan id_hewan
    @GET("bacaperawatan.php")
    suspend fun getPerawatan() : PerawatanResponse

    @GET("bacaperawatan1.php")
    suspend fun getPerawatanById(@Path("id_perawatan")id_perawatan: String): PerawatanResponseDetail

    // Menambahkan perawatan baru (insertperawatan)
    @POST("insertperawatan.php")
    suspend fun insertPerawatan(@Body perawatan: Perawatan)

    // Mengupdate perawatan berdasarkan id_perawatan
    @PUT("editperawatan.php")
    suspend fun updatePerawatan(
        @Path("id_perawatan") id_perawatan: String,
        @Body perawatan: Perawatan
    )

    // Menghapus perawatan berdasarkan id_perawatan
    @DELETE("deleteperawatan.php{id_perawatan}")
    suspend fun deletePerawatan(@Path("id_perawatan") id_perawatan: String): Response<Unit>
}