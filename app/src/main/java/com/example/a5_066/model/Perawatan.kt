package com.example.a5_066.model

import kotlinx.serialization.Serializable

@Serializable
data class Perawatan(
    val id_perawatan: String,
    val id_hewan: String,
    val id_dokter: String,
    val tanggal_perawatan: String,
    val detail_perawatan: String
)

@Serializable
data class PerawatanResponse(
    val status : Boolean,
    val message : String,
    val data : List<Perawatan>
)

@Serializable
data class PerawatanResponseDetail(
    val status : Boolean,
    val message : String,
    val data : Perawatan
)
