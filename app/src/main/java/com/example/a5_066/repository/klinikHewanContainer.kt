package com.example.a5_066.repository

import NetworkPasienRepository
import PasienRepository
import com.example.a5_066.service.DokterService
import com.example.a5_066.service.JenisHewanService
import com.example.a5_066.service.PasienService
import com.example.a5_066.service.PerawatanService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val pasienRepository: PasienRepository
    val dokterRepository: DokterRepository
    val perawatanRepository: PerawatanRepository
    val jenisHewanRepository: JenisHewanRepository
}

class klinikHewanContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2/hewan/" // Pastikan server dapat dijangkau
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val pasienService: PasienService by lazy {
        retrofit.create(PasienService::class.java)
    }
    private val dokterService: DokterService by lazy {
        retrofit.create(DokterService::class.java)
    }
    private val perawatanService: PerawatanService by lazy {
        retrofit.create(PerawatanService::class.java)
    }
    private val jenisHewanService:JenisHewanService by lazy {
        retrofit.create(JenisHewanService::class.java)
    }

    override val pasienRepository: PasienRepository by lazy {
        NetworkPasienRepository(pasienService)
    }
    override val dokterRepository: DokterRepository by lazy {
        NetworkDokterRepository(dokterService)
    }
    override val perawatanRepository: PerawatanRepository by lazy {
        NetworkPerawatanRepository(perawatanService)
    }
    override val jenisHewanRepository: JenisHewanRepository by lazy {
        NetworkJenisHewanRepository(jenisHewanService)
    }
}
