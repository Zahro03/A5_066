package com.example.a5_066.model

import kotlinx.serialization.Serializable

@Serializable
data class JenisHewan(
    val id_jenis_hewan: String,
    val nama_jenis_hewan: String,
    val deskripsi: String
)

@Serializable
data class JenisHewanResponse(
    val status : Boolean,
    val message : String,
    val data : List<JenisHewan>
)

@Serializable
data class JenisHewanResponseDetail(
    val status : Boolean,
    val message : String,
    val data : JenisHewan
)
