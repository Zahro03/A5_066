package com.example.a5_066.model

import kotlinx.serialization.Serializable

@Serializable
data class JenisHewan(
    val id_jenis_hewan: String,
    val nama_jenis_hewan: String,
    val deskripsi: String
)
