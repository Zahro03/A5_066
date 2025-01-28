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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.R
import com.example.a5_066.costumWidget.CostumeTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToJenisHewanManagement: () -> Unit,
    navigateToDokterManagement: () -> Unit,
    navigateToPerawatanManagement: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    onEditClick: (PasienHewan) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHome.titleRes,
                canNavigateBack = false,
                onRefresh = { viewModel.getPsn() }
            )
        },
        bottomBar = {
            Box(modifier = Modifier.padding(bottom = 32.dp)) {
                NavigationBar(
                    containerColor = Color(0xFFFDE2F3) // Soft pink tone
                ) {
                    NavigationBarItem(
                        selected = selectedIndex == 0,
                        onClick = {
                            selectedIndex = 0
                            navigateToItemEntry()
                        },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(64.dp) // Increased size
                                    .background(
                                        color = Color(0xFFF9D0D9), // Pastel pink for Pasien
                                        shape = CircleShape
                                    )
                                    .padding(14.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.pasieen),
                                    contentDescription = "Tambah Pasien",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        },
                    )
                    NavigationBarItem(
                        selected = selectedIndex == 1,
                        onClick = {
                            selectedIndex = 1
                            navigateToJenisHewanManagement()
                        },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(64.dp) // Increased size
                                    .background(
                                        color = Color(0xFFADD8E6), // Pastel blue for Jenis Hewan
                                        shape = CircleShape
                                    )
                                    .padding(14.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.jenis),
                                    contentDescription = "Jenis Hewan",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        },
                    )
                    NavigationBarItem(
                        selected = selectedIndex == 2,
                        onClick = {
                            selectedIndex = 2
                            navigateToDokterManagement()
                        },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(64.dp) // Increased size
                                    .background(
                                        color = Color(0xFFFFE135), // Pastel yellow for Dokter
                                        shape = CircleShape
                                    )
                                    .padding(14.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.dokter),
                                    contentDescription = "Dokter",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        },
                    )
                    NavigationBarItem(
                        selected = selectedIndex == 3,
                        onClick = {
                            selectedIndex = 3
                            navigateToPerawatanManagement()
                        },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(64.dp) // Increased size
                                    .background(
                                        color = Color(0xFFE1BEE7), // Pastel purple for Perawatan
                                        shape = CircleShape
                                    )
                                    .padding(14.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.perawatan),
                                    contentDescription = "Perawatan",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        },
                    )
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(180.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFF9D0D9), Color(0xFFF9C5D1)) // Soft gradient
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Welcome",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        ),
                        color = Color(0xFF9C4D7E), // Soft purple
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    )
                    Text(
                        text = "to Our Animal Care Center",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        color = Color(0xFF9C4D7E), // Soft purple
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.animal),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)  // Menambahkan untuk membuat gambar berbentuk bulat
                        .align(Alignment.CenterEnd)
                )
            }

            HomeStatus(
                homeUiState = viewModel.psnUIState,
                retryAction = { viewModel.getPsn() },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                onDetailClick = onDetailClick,
                onEditClick = onEditClick,
                onDeleteClick = {
                    viewModel.deletePsn(it.id_hewan)
                    viewModel.getPsn()
                }
            )
        }
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (PasienHewan) -> Unit = {},
    onDetailClick: (Int) -> Unit,
    onEditClick: (PasienHewan) -> Unit = {}
) {
    when (homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeUiState.Success -> {
            if (homeUiState.pasienHewan.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Tidak ada data Pasien Hewan",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                PsnLayout(
                    pasienHewan = homeUiState.pasienHewan,
                    modifier = modifier,
                    onDetailClick = { onDetailClick(it.id_hewan) },
                    onEditClick = { onEditClick(it) },
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        }
        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.loading)
            )
            Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun OnError(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.loading_failed), contentDescription = ""
            )
            Text(
                text = stringResource(R.string.loading_failed),
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
            Button(onClick = retryAction) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Composable
fun PsnLayout(
    pasienHewan: List<PasienHewan>,
    modifier: Modifier = Modifier,
    onDetailClick: (PasienHewan) -> Unit,
    onEditClick: (PasienHewan) -> Unit = {},
    onDeleteClick: (PasienHewan) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pasienHewan) { hewan ->
            PsnCard(
                pasienHewan = hewan,
                modifier = Modifier
                    .fillMaxWidth(),
                onDetailClick = { onDetailClick(it) },
                onEditClick = { onEditClick(it) },
                jnsList = viewModel.jnsList,
                onDeleteClick = { onDeleteClick(it) }
            )
        }
    }
}

@Composable
fun PsnCard(
    jnsList: List<Jenishewan>,
    pasienHewan: PasienHewan,
    modifier: Modifier = Modifier,
    onDetailClick: (PasienHewan) -> Unit = {},
    onEditClick: (PasienHewan) -> Unit = {},
    onDeleteClick: (PasienHewan) -> Unit = {}
) {
    var jenishewan =
        jnsList.find { it.id_jenis_hewan == pasienHewan.jenis_hewan_id }?.nama_jenis_hewan
            ?: "Tidak ditemukan Jenis Hewan"

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                showDialog = false
                onDeleteClick(pasienHewan)
            },
            onDeleteCancel = {
                showDialog = false
            }
        )
    }

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .heightIn(min = 80.dp, max = 120.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent) // Set container color to transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFCE4EC),
                            Color(0xFFFFFF)
                        ) // Soft pink to white gradient
                    ),
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp) // Adjust padding to center text more
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Nama Hewan: ${pasienHewan.nama_hewan}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.Start) // Align to start with some padding
                )
                Text(
                    text = "Nama Pemilik : ${pasienHewan.pemilik}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start) // Align to start with some padding
                )
                Text(
                    text = "Jenis Hewan : ${jenishewan}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start) // Align to start with some padding
                )
                Text(
                    text = "Catatan Kesehatan : ${pasienHewan.catatan_kesehatan}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start) // Align to start with some padding
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDetailClick(pasienHewan) }) {
                        Text("Detail", style = MaterialTheme.typography.labelSmall)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = { onEditClick(pasienHewan) }) {
                        Text("Edit", style = MaterialTheme.typography.labelSmall)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = { showDialog = true }) {
                        Text("Hapus", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}
@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /*Do nothing*/ },
        title = { Text("Hapus Data") },
        text = { Text("Apakah Anda yakin ingin menghapus data ini?") },
        dismissButton = {
            TextButton(onClick = { onDeleteCancel() }) {
                Text(text = "Batal")
            }
        },
        confirmButton = {
            TextButton(onClick = { onDeleteConfirm() }) {
                Text(text = "Ya")
            }
        }
    )
}