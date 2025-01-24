package com.example.a5_066.repository

import com.example.a5_066.service.DokterService
import com.example.a5_066.service.PasienService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val pasienRepository: PasienRepository
    val dokterRepository: DokterRepository
}

class klinikHewanContainer : AppContainer{
    private val baseUrl ="http://10.0.2.2:8080/hewan/"//
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val pasienService: PasienService by lazy {
        retrofit.create(PasienService::class.java)
    }
    private val dokterService: DokterService by lazy {
        retrofit.create(DokterService::class.java)
    }
    override val pasienRepository: PasienRepository by lazy {
        NetworkPasienRepository(pasienService)
    }
    override val dokterRepository: DokterRepository by lazy {
        NetworkDokterRepository(dokterService)
    }
}