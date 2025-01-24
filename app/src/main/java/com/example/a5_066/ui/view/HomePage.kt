package com.example.a5_066.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a5_066.R

@Preview(showBackground = true)
@Composable
fun Homepage(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .background(color = Color.White)
    ) {
        HeaderSection()

        Spacer(modifier = Modifier.size(48.dp))

        BodySection(onItemClick = onItemClick)
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 48.dp))
            .background(color = Color(0xFFFFD1DC)) // Warna pastel pink
            .padding(bottom = 32.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 24.dp)
        ) {
            Column {
                Spacer(Modifier.padding(3.dp))
                Text(
                    text = "Welcome",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "to Our Animal Care Center",
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Box(
            Modifier.align(Alignment.CenterEnd)
                .padding(24.dp)
                .padding(top = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.animal),
                contentDescription = "Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
fun BodySection(
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 100.dp), // Tambahkan padding bawah untuk menaikkan layout ke atas
        verticalArrangement = Arrangement.Center, // Konten sejajar vertikal di tengah layar
        horizontalAlignment = Alignment.CenterHorizontally // Konten sejajar horizontal di tengah layar
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp), // Jarak antar kartu lebih besar
            modifier = Modifier.align(Alignment.CenterHorizontally) // Row di tengah horizontal
        ) {
            ManageCard(
                title = "Pasien",
                description = "",
                backgroundColor = Color(0xFFF5B7B1), // Peach pastel
                iconResource = R.drawable.pasieen,
                onClick = { onItemClick("Pasien") }
            )

            ManageCard(
                title = "Jenis Hewan",
                description = "",
                backgroundColor = Color(0xFFAED6F1), // Biru muda pastel
                iconResource = R.drawable.jenis,
                onClick = { onItemClick("Jenis Hewan") }
            )
        }

        Spacer(modifier = Modifier.size(24.dp)) // Jarak antar baris lebih besar

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp), // Jarak antar kartu lebih besar
            modifier = Modifier.align(Alignment.CenterHorizontally) // Row di tengah horizontal
        ) {
            ManageCard(
                title = "Dokter",
                description = "",
                backgroundColor = Color(0xFFD7BDE2), // Ungu pastel
                iconResource = R.drawable.dokter,
                onClick = { onItemClick("Dokter") }
            )

            ManageCard(
                title = "Perawatan",
                description = "",
                backgroundColor = Color(0xFFF9E79F), // Kuning pastel
                iconResource = R.drawable.perawatpng,
                onClick = { onItemClick("Perawatan") }
            )
        }
    }
}

@Composable
fun ManageCard(
    title: String,
    description: String,
    backgroundColor: Color,
    iconResource: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(170.dp) // Ukuran kartu diperbesar menjadi 160x160 dp
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp), // Sudut melengkung
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // Gambar icon
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = "$title Icon",
                modifier = Modifier
                    .size(70.dp) // Ukuran ikon diperbesar menjadi 70 dp
                    .clip(CircleShape)
            )

            // Teks judul
            Text(
                text = title,
                fontSize = 16.sp, // Ukuran teks diperbesar
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Teks deskripsi
            Text(
                text = description,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


