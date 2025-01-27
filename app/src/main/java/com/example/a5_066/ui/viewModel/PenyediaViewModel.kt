import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a5_066.klinikHewanApplications
import com.example.a5_066.ui.viewModel.dokter.DetailDokterViewModel
import com.example.a5_066.ui.viewModel.dokter.DokterViewModel
import com.example.a5_066.ui.viewModel.dokter.InsertDokterViewModel
import com.example.a5_066.ui.viewModel.pasien.DetailPasienViewModel
import com.example.a5_066.ui.viewModel.perawatan.InsertPerawatanViewModel
import com.example.a5_066.ui.viewModel.perawatan.PerawatanViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Initialize HomePasienViewModel
        initializer {
            HomePasienViewModel(
                aplikasiPasien().container.pasienRepository,
            )
        }
        // Initialize InsertViewModel
        initializer {
            InsertViewModel(
                aplikasiPasien().container.pasienRepository
            )
        }
        // Initialize DetailPasienViewModel
        initializer {
            DetailPasienViewModel(
                this.createSavedStateHandle(),
                aplikasiPasien().container.pasienRepository
            )
        }
        // Initialize UpdatePasienViewModel
        initializer {
            UpdatePasienViewModel(
                this.createSavedStateHandle(),
                aplikasiPasien().container.pasienRepository
            )
        }
        // Initialize DokterViewModel
        initializer {
            DokterViewModel(
                aplikasiPasien().container.dokterRepository
            )
        }
        // Initialize InsertDokterViewModel
        initializer {
            InsertDokterViewModel(
                aplikasiPasien().container.dokterRepository
            )
        }
        // Initialize DetailDokterViewModel
        initializer {
            DetailDokterViewModel(
                this.createSavedStateHandle(),
                aplikasiPasien().container.dokterRepository
            )
        }
        // Initialize UpdateDokterViewModel
        initializer {
            UpdateDokterViewModel(
                this.createSavedStateHandle(),
                aplikasiPasien().container.dokterRepository
            )
        }
        // Initialize PerawatanViewModel
        initializer {
            PerawatanViewModel(
                aplikasiPasien().container.perawatanRepository
            )
        }
        // Initialize InsertPerawatanViewModel
        initializer {
            InsertPerawatanViewModel(
                aplikasiPasien().container.perawatanRepository
            )
        }
        // Initialize UpdatePerawatanViewModel
        initializer {
            UpdatePerawatanViewModel(
                this.createSavedStateHandle(),
                aplikasiPasien().container.perawatanRepository
            )
        }
    }
}

// Function to access PasienApplication from CreationExtras
fun CreationExtras.aplikasiPasien(): klinikHewanApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as klinikHewanApplications)
