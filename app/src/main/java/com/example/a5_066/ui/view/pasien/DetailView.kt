package com.example.a5_066.ui.view.pasien

import Pasien
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.costumWidget.CostumeTopAppBar
import com.example.a5_066.ui.view.dokter.DestinasiDetail
import com.example.a5_066.ui.viewModel.PenyediaViewModel
import com.example.a5_066.ui.viewModel.pasien.DetailPasienUiState
import com.example.a5_066.ui.viewModel.pasien.DetailViewModel
import com.example.a5_066.ui.viewModel.pasien.toPasien

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(
    NavigateBack: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit = { },
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = NavigateBack,
                onBackPressed = NavigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onEditClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Pasien"
                )
            }
        }
    ) { innerPadding ->
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

        BodyDetailPasien(
            detailUiState = viewModel.detailPasienUiState,
            modifier = Modifier.padding(innerPadding),
            onDeleteClick = {
                deleteConfirmationRequired = true
            }
        )

        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    viewModel.deletePasien()
                    onDeleteClick()
                    deleteConfirmationRequired = false
                },
                onDeleteCancel = {
                    deleteConfirmationRequired = false
                },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun BodyDetailPasien(
    modifier: Modifier = Modifier,
    detailUiState: DetailPasienUiState,
    onDeleteClick: () -> Unit
) {
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailUiState.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = detailUiState.errorMessage, color = Color.Red)
            }
        }
        detailUiState.isUiEventNotEmpty -> {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                ItemDetailPasien(
                    pasien = detailUiState.detailUiEvent.toPasien(),
                    modifier = modifier
                )

                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = onDeleteClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Delete")
                }
            }
        }
    }
}

@Composable
fun ItemDetailPasien(
    modifier: Modifier = Modifier,
    pasien: Pasien
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ComponentDetailPasien(judul = "ID Hewan", isinya = pasien.id_hewan)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(judul = "Nama Hewan", isinya = pasien.nama_hewan)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(judul = "Jenis Hewan", isinya = pasien.jenis_hewan_id)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(judul = "Pemilik", isinya = pasien.pemilik)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(judul = "Kontak Pemilik", isinya = pasien.kontak_pemilik)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(judul = "Tanggal Lahir", isinya = pasien.tanggal_lahir)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPasien(judul = "Catatan Kesehatan", isinya = pasien.catatan_kesehatan)
    }
}

@Composable
fun ComponentDetailPasien(
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

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDeleteCancel,
        title = { Text("Konfirmasi Penghapusan") },
        text = { Text("Apakah Anda yakin ingin menghapus pasien ini?") },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text("Ya")
            }
        },
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text("Tidak")
            }
        },
        modifier = modifier
    )
}
