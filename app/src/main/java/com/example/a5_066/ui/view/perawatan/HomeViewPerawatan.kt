package com.example.a5_066.ui.view.perawatan

import DestinasiNavigasi
import HomeUiState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.R
import com.example.a5_066.costumWidget.CostumeTopAppBar
import com.example.a5_066.model.Perawatan
import com.example.a5_066.ui.viewModel.perawatan.HomePrUiState
import com.example.a5_066.ui.viewModel.perawatan.PerawatanViewModel

object DestinasiHomePerawatan : DestinasiNavigasi {
    override val route = "perawatan"
    override val titleRes = "Home Perawatan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePerawatanScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: PerawatanViewModel = viewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomePerawatan.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = { viewModel.getPerawatan() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Perawatan")
            }
        }
    ) { innerPadding ->
        HomeStatus(
            homePrUiState = viewModel.perawatanUIState,
            retryAction = { viewModel.getPerawatan() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = { perawatan ->
                viewModel.deletePerawatan(perawatan.id_perawatan)
                viewModel.getPerawatan()
            }
        )
    }
}

@Composable
fun HomeStatus(
    homePrUiState: HomePrUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Perawatan) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homePrUiState) {
        is HomePrUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomePrUiState.Success -> {
            if (homePrUiState.perawatan.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data perawatan")
                }
            } else {
                PerawatanLayout(
                    perawatan = homePrUiState.perawatan,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.id_perawatan) },
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        }
        is HomePrUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
        else -> {}
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
        Image(painter = painterResource(id = R.drawable.loading_failed), contentDescription = "")
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
                onDeleteClick = { onDeleteClick(perawatan) }
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
                text = "Tanggal Perawatan: ${perawatan.tanggal_perawatan}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Detail Perawatan: ${perawatan.detail_perawatan}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
