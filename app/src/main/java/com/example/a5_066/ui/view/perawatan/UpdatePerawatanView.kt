package com.example.a5_066.ui.view.perawatan

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun UpdatePerawatanView(
    id_perawatan: String,  // ID Perawatan yang akan diupdate
    id_hewan: String,      // ID Hewan yang menerima perawatan
    id_dokter: String,     // ID Dokter yang memberikan perawatan
    tanggal_perawatan: String, // Tanggal Perawatan
    detail_perawatan: String,  // Detail Perawatan
    onBackArrow: () -> Unit,
    onNavigate: () -> Unit
) {
    // State untuk input field
    val id_hewan = remember { mutableStateOf(id_hewan) }
    val id_dokter = remember { mutableStateOf(id_dokter) }
    val tanggal_perawatan = remember { mutableStateOf(tanggal_perawatan) }
    val detail_perawatan = remember { mutableStateOf(detail_perawatan) }

    // Fungsi untuk menangani proses update data
    @Composable
    fun updatePerawatan() {
        if (id_hewan.value.isNotEmpty() && id_dokter.value.isNotEmpty() && tanggal_perawatan.value.isNotEmpty() && detail_perawatan.value.isNotEmpty()) {
            // Logika untuk melakukan update perawatan
            // Misalnya menggunakan ViewModel atau API untuk mengupdate data
            // Gantikan ini dengan logika yang sesuai untuk menghubungkan ke database atau API
            Toast.makeText(
                context = LocalContext.current,
                text = "Perawatan berhasil diperbarui!",
                duration = Toast.LENGTH_SHORT
            ).show()
            onNavigate() // Navigasi setelah update berhasil
        } else {
            Toast.makeText(
                context = LocalContext.current,
                text = "Semua kolom harus diisi!",
                duration = Toast.LENGTH_SHORT
            ).show()
        }
    }
}