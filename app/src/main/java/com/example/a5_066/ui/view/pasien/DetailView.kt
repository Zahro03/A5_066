package com.example.a5_066.ui.view.pasien

import DestinasiNavigasi
import Pasien
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.costumWidget.CostumeTopAppBar
import com.example.a5_066.ui.view.dokter.OnError
import com.example.a5_066.ui.view.dokter.OnLoading
import com.example.a5_066.ui.viewModel.pasien.DetailPasienUiState
import com.example.a5_066.ui.viewModel.pasien.DetailPasienViewModel

object DestinasiDetailPasien : DestinasiNavigasi {
    override val route = "pasien"
    val id_hewan = "id_hewan"  // Ubah nama parameter untuk konsistensi
    override val titleRes = "Detail Pasien"
    val routesWithArg = "$route/{$id_hewan}"  // Mendefinisikan rute dengan argumen
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPasienScreen(
    navigateBack: () -> Unit,
    onEditClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    detailViewModel: DetailPasienViewModel = viewModel(factory = PenyediaViewModel.Factory),
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPasien.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack,
                onRefresh = {
                    detailViewModel.getPasienById()
                }
            )
        },
    )
    { innerPadding ->
        DetailStatus(
            pasienUiState = detailViewModel.detailPasienUiState,
            retryAction = {detailViewModel.getPasienById() },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onEditClick = onEditClick

        )
    }
}

@Composable
fun DetailStatus(
    pasienUiState: DetailPasienUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onEditClick: (String) -> Unit = {},
) {
    when (pasienUiState) {
        is DetailPasienUiState.Success -> {
            DetailPasienLayout(
                pasien = pasienUiState.pasien.data,
                modifier = modifier,
                onEditClick = onEditClick
            )
        }
        is DetailPasienUiState.Loading -> OnLoading(modifier = modifier)
        is DetailPasienUiState.Error -> OnError(retryAction, modifier = modifier)
    }
}


@Composable
fun DetailPasienLayout(
    pasien: Pasien,
    modifier: Modifier = Modifier,
    onEditClick: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Membungkus seluruh detail pasien dengan Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE))
                .clip(RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            ItemDetailPasien(
                pasien = pasien,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Edit
        Button(
            onClick = { onEditClick(pasien.id_hewan) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Edit", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun ItemDetailPasien(
    modifier: Modifier = Modifier,
    pasien: Pasien
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        ComponentDetailPasien(title = "ID Hewan", content = pasien.id_hewan.ifEmpty { "Tidak ada data" })
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(title = "Nama Hewan", content = pasien.nama_hewan.ifEmpty { "Tidak ada data" })
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(title = "Jenis Hewan", content = pasien.jenis_hewan_id.ifEmpty { "Tidak ada data" })
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(title = "Pemilik", content = pasien.pemilik.ifEmpty { "Tidak ada data" })
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(title = "Kontak Pemilik", content = pasien.kontak_pemilik.ifEmpty { "Tidak ada data" })
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(title = "Tanggal Lahir", content = pasien.tanggal_lahir.ifEmpty { "Tidak ada data" })
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(title = "Catatan Kesehatan", content = pasien.catatan_kesehatan.ifEmpty { "Tidak ada data" })
    }
}

@Composable
fun ComponentDetailPasien(
    modifier: Modifier = Modifier,
    title: String,
    content: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$title: ",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = content,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
