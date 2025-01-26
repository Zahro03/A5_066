package com.example.a5_066.ui.view.perawatan

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a5_066.ui.theme.A5_066Theme
import java.text.SimpleDateFormat
import java.util.Date

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

    // Layout untuk tampilan Update Perawatan
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tombol untuk kembali ke halaman sebelumnya
        Button(onClick = { onBackArrow() }) {
            Text("Kembali")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }

    // Input untuk ID Hewan
    BasicTextField(
        value = id_hewan.value,
        onValueChange = { id_hewan.value = it },
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        textStyle = TextStyle(color = Color.Black)
    )
    Text("ID Hewan", color = Color.Black)

    Spacer(modifier = Modifier.height(8.dp))

    // Input untuk ID Dokter
    BasicTextField(
        value = id_dokter.value,
        onValueChange = { id_dokter.value = it },
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        textStyle = TextStyle(color = Color.Black)
    )
    Text("ID Dokter", color = Color.Black)

    Spacer(modifier = Modifier.height(8.dp))

    // Input untuk Tanggal Perawatan
    BasicTextField(
        value = tanggal_perawatan.value,
        onValueChange = { tanggal_perawatan.value = it },
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        textStyle = TextStyle(color = Color.Black)
    )
    Text("Tanggal Perawatan", color = Color.Black)

    Spacer(modifier = Modifier.height(8.dp))

    // Input untuk Detail Perawatan
    BasicTextField(
        value = detail_perawatan.value,
        onValueChange = { detail_perawatan.value = it },
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        textStyle = TextStyle(color = Color.Black)
    )
    Text("Detail Perawatan", color = Color.Black)

    Spacer(modifier = Modifier.height(16.dp))

    // Tombol untuk update perawatan
    Button(onClick = { updatePerawatan() }) {
        Text("Update Perawatan")
    }
}

@Preview(showBackground = true)
@Composable
fun UpdatePerawatanViewPreview() {
    A5_066Theme {
        UpdatePerawatanView(
            id_perawatan = "1234",
            id_hewan = "5678",
            id_dokter = "91011",
            tanggal_perawatan = SimpleDateFormat("dd/MM/yyyy").format(Date()),
            detail_perawatan = "Vaksinasi rabies",
            onBackArrow = {},
            onNavigate = {}
        )
    }
}
