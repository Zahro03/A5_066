package com.example.a5_066.ui.view.perawatan

import androidx.compose.runtime.Composable

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