package com.example.a5_066.model

import kotlinx.serialization.Serializable

@Serializable
data class Dokter(
    val id_Dokter: String,
    val nama_dokter: String,
    val spesialisasi: String,
    val kontak: String
)

@Serializable
data class DokterResponse(
    val status : Boolean,
    val message : String,
    val data : List<Dokter>
)

@Serializable
data class DokterResponseDetail(
    val status: Boolean,
    val message: String,
    val data : Dokter
)
