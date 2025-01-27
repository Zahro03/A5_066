package com.example.a5_066.ui.view.jenisHewan

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
import com.example.a5_066.costumWidget.CostumeTopAppBar
import com.example.a5_066.model.JenisHewan
import com.example.a5_066.ui.viewModel.jenisHewan.JenisHewanUiState
import com.example.a5_066.ui.viewModel.jenisHewan.JenisHewanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeJenisHewanScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: JenisHewanViewModel = viewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomejh.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getJenisHewan()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Jenis Hewan")
            }
        },
    ) { innerPadding ->
        HomeStatus(
            jenisHewanUiState = viewModel.jenisHewanUiState,
            retryAction = { viewModel.getJenisHewan() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = { jenisHewan ->
                viewModel.deleteJenisHewan(jenisHewan.id_jenis_hewan)
                viewModel.getJenisHewan()
            }
        )
    }
}

@Composable
fun HomeStatus(
    jenisHewanUiState: JenisHewanUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (JenisHewan) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (jenisHewanUiState) {
        is JenisHewanUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is JenisHewanUiState.Success -> {
            if (jenisHewanUiState.jenisHewan.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Jenis Hewan")
                }
            } else {
                JhLayout(
                    jenisHewan = jenisHewanUiState.jenisHewan,  // Gunakan list JenisHewan
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { id ->
                        onDetailClick(id) // Passing ID for detail
                    },
                    onDeleteClick = { jenisHewan ->
                        onDeleteClick(jenisHewan)
                    }
                )
            }
        }
        is JenisHewanUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
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
fun JhLayout(
    jenisHewan: List<JenisHewan>,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (JenisHewan) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(jenisHewan) { jenisHewan ->
            JhCard(
                jenisHewan = jenisHewan,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(jenisHewan.id_jenis_hewan) },  // Passing ID for detail
                onDeleteClick = {
                    onDeleteClick(jenisHewan)
                }
            )
        }
    }
}

@Composable
fun JhCard(
    jenisHewan: JenisHewan,
    modifier: Modifier = Modifier,
    onDeleteClick: (JenisHewan) -> Unit = {}
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
                Text(
                    text = jenisHewan.nama_jenis_hewan,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(jenisHewan) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                    )
                }
                Text(
                    text = jenisHewan.id_jenis_hewan,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = jenisHewan.deskripsi,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
