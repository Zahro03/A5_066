package com.example.a5_066

import android.app.Application
import com.example.a5_066.repository.AppContainer
import com.example.a5_066.repository.klinikHewanContainer

class klinikHewanApplications : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        // Inisialisasi AppContainer untuk mengelola dependensi
        container = klinikHewanContainer()
    }
}