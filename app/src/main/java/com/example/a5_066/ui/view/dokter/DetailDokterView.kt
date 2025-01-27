package com.example.a5_066.ui.view.dokter

import DestinasiNavigasi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.R
import com.example.a5_066.costumWidget.CostumeTopAppBar
import com.example.a5_066.model.Dokter
import com.example.a5_066.ui.viewModel.dokter.DetailDokterUiState
import com.example.a5_066.ui.viewModel.dokter.DetailDokterViewModel

object DestinasiDetailDokter : DestinasiNavigasi {
    override val route = "dokter"
    val id_Dokter = "id_Dokter"
    override val titleRes = "Detail Dokter"
    val routesWithArg = "$route/{$id_Dokter}"  // Mendefinisikan rute dengan argumen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailDokterScreen(
    navigateBack: () -> Unit,
    onEditClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    detailDokterViewModel: DetailDokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailDokter.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack,
                onRefresh = {
                    detailDokterViewModel.getDokterById()
                }
            )
        },
    )
    { innerPadding ->
        DetailStatus(
            dokterUiState = detailDokterViewModel.detailDokterUiState,
            retryAction = {detailDokterViewModel.getDokterById() },
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize(),
            onEditClick = onEditClick

        )
    }
}

@Composable
fun DetailStatus(
    dokterUiState: DetailDokterUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onEditClick: (String) -> Unit = {},
){
    when(dokterUiState){
        is DetailDokterUiState.Success -> {
            DetailDokterLayout(
                dokter = dokterUiState.dokter,
                modifier = modifier,
                onEditClick = {onEditClick(it)}
            )
        }
        is DetailDokterUiState.Loading -> OnLoading(modifier = modifier)
        is DetailDokterUiState.Error -> OnError(retryAction, modifier = modifier)
    }
}

@Composable
fun DetailDokterLayout(
    dokter: Dokter,
    modifier: Modifier = Modifier,
    onEditClick: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Membungkus seluruh detail mahasiswa dengan Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE)) // Warna abu-abu terang
                .clip(RoundedCornerShape(8.dp))
                .padding(16.dp) // Padding di dalam Box
        ) {
            // Komponen Detail Mahasiswa
            ItemDetailDokter(
                dokter = dokter,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp)) // Spacer setelah detail

        // Tombol Edit
        Button(
            onClick = { onEditClick(dokter.id_Dokter) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF424242) // Warna abu-abu gelap
            )
        ) {
            Text(text = "Edit", fontSize = 16.sp, color = Color.White)
        }
    }
}


@Composable
fun ItemDetailDokter(
    modifier: Modifier = Modifier,
    dokter: Dokter
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ComponentDetailDokter(judul = "Id Dokter", isinya = dokter.id_Dokter)
        Spacer(modifier = Modifier.height(12.dp)) // Spacer between each detail
        ComponentDetailDokter(judul = "Nama Dokter", isinya = dokter.nama_dokter)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailDokter(judul = "Spesialisasi", isinya = dokter.spesialisasi)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailDokter(judul = "Kontak", isinya = dokter.kontak)
    }
}

@Composable
fun ComponentDetailDokter(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$judul: ",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = isinya,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
