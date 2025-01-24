package com.example.a5_066.ui.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a5_066.klinikHewanApplications
import com.example.a5_066.ui.viewModel.dokter.DetailDokterViewModel
import com.example.a5_066.ui.viewModel.dokter.DokterViewModel
import com.example.a5_066.ui.viewModel.dokter.InsertDokterViewModel
import com.example.a5_066.ui.viewModel.dokter.UpdateDokterViewModel
import com.example.a5_066.ui.viewModel.pasien.DetailViewModel
import com.example.a5_066.ui.viewModel.pasien.InsertViewModel
import com.example.a5_066.ui.viewModel.pasien.PasienViewModel
import com.example.a5_066.ui.viewModel.pasien.UpdateViewModel
import okhttp3.Call

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            PasienViewModel(
                aplikasiPasien().container.pasienRepository
            )
        }
        initializer {
            InsertViewModel(
                aplikasiPasien().container.pasienRepository
            )
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(), aplikasiPasien().container.pasienRepository
            )
        }
        initializer {
            UpdateViewModel(
                createSavedStateHandle(), aplikasiPasien().container.pasienRepository
            )
        }
        initializer {
            DokterViewModel(
                aplikasiPasien().container.dokterRepository
            )
        }
        initializer {
            InsertDokterViewModel(
                aplikasiPasien().container.dokterRepository
            )
        }
        initializer {
            DetailDokterViewModel(
                createSavedStateHandle(),aplikasiPasien().container.dokterRepository
            )
        }
        initializer {
            UpdateDokterViewModel(
                createSavedStateHandle(),aplikasiPasien().container.dokterRepository
            )
        }
    }
}


// Fungsi untuk mengakses PasienApplication dari CreationExtras
fun CreationExtras.aplikasiPasien(): klinikHewanApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as klinikHewanApplications)