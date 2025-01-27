package com.example.uaspam.ui.View.PasienHewan

import DestinasiHomePasien
import HomePasienViewModel
import HomeUiState
import Pasien
import PenyediaViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.R
import com.example.a5_066.costumWidget.CostumeTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePasienScreen(
    navigateToItemEntry: () -> Unit,
    onDetailClick: (String) -> Unit = {}, // Ubah tipe parameter menjadi String
    modifier: Modifier = Modifier,
    viewModel: HomePasienViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomePasien.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPasien()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pasien")
            }
        },
    ) { innerPadding ->
        HomeStatus(
            homeUiState = viewModel.pasienUiState,
            retryAction = { viewModel.getPasien() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deletePasien(it.id_hewan)
                viewModel.getPasien()
            }
        )
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pasien) -> Unit = {},
    onDetailClick: (String) -> Unit // Parameter sesuai dengan tipe String
) {
    when (homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeUiState.Success -> {
            if (homeUiState.pasien.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Tidak ada data Pasien Hewan",
                    )
                }
            } else {
                PsnLayout(
                    pasien = homeUiState.pasien,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it) }, // Ambil ID pasien
                    onDeleteClick = {
                        onDeleteClick(it) }
                )
            }
        }
        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
        else -> {} // Tambahkan else untuk membuat when "exhaustive"
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.loading_failed), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun PsnLayout(
    pasien: List<Pasien>,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit, // Tipe parameter disesuaikan
    onDeleteClick: (Pasien) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pasien) { pasien ->
            PsnCard(
                pasien = pasien,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(pasien.id_hewan) }, // Ambil ID pasien
                onDeleteClick = { onDeleteClick(pasien) }
            )
        }
    }
}

@Composable
fun PsnCard(
    pasien: Pasien,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pasien) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = pasien.nama_hewan,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = pasien.jenis_hewan_id,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = pasien.pemilik,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = pasien.kontak_pemilik,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = pasien.tanggal_lahir,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = pasien.catatan_kesehatan,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { onDeleteClick(pasien) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}
