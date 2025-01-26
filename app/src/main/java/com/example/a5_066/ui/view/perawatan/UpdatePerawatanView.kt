package com.example.a5_066.ui.view.perawatan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

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
}