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
