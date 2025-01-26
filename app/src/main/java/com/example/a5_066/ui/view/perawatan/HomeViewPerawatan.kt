package com.example.a5_066.ui.view.perawatan

import HalamanController
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a5_066.R
import com.example.a5_066.costumWidget.TopAppBar
import com.example.a5_066.model.Perawatan
import com.example.a5_066.ui.viewModel.perawatan.PerawatanUiState
import com.example.a5_066.ui.viewModel.perawatan.PerawatanViewModel

object DestinasiHomePerawatan : HalamanController {
    override val route = "perawatan"
    override val titleRes = "Perawatan Hewan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerawatanScreen(
    navigateToAddPerawatan: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: PerawatanViewModel = androidx.lifecycle.viewmodel.compose.viewModel() // Menggunakan viewModel() untuk mendapatkan ViewModel
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = DestinasiHomePerawatan.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getRiwayatPerawatan("id_hewan")  // Ganti dengan ID Hewan yang sesuai
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddPerawatan,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Perawatan")
            }
        },
    ) { innerPadding ->
        PerawatanStatus(
            perawatanUiState = viewModel.perawatanUIState,
            retryAction = { viewModel.getRiwayatPerawatan("id_hewan") },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deletePerawatan(it.id_perawatan) // Hapus perawatan berdasarkan ID
                viewModel.getRiwayatPerawatan("id_hewan")  // Refresh data perawatan setelah dihapus
            }
        )
    }
}

@Composable
fun PerawatanStatus(
    perawatanUiState: PerawatanUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Perawatan) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (perawatanUiState) {
        is PerawatanUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is PerawatanUiState.Success ->
            if (perawatanUiState.perawatan.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data perawatan")
                }
            } else {
                PerawatanLayout(
                    perawatan = perawatanUiState.perawatan, modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_perawatan)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is PerawatanUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
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
fun PerawatanLayout(
    perawatan: List<Perawatan>,
    modifier: Modifier = Modifier,
    onDetailClick: (Perawatan) -> Unit,
    onDeleteClick: (Perawatan) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(perawatan) { perawatan ->
            PerawatanCard(
                perawatan = perawatan,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(perawatan) },
                onDeleteClick = {
                    onDeleteClick(perawatan)
                }
            )
        }
    }
}

@Composable
fun PerawatanCard(
    perawatan: Perawatan,
    modifier: Modifier = Modifier,
    onDeleteClick: (Perawatan) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(perawatan) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                    )
                }
            }
            Text(
                text = "Tanggal: ${perawatan.tanggal_perawatan}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Deskripsi: ${perawatan.detail_perawatan}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


