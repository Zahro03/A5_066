package com.example.a5_066.ui.view.jenisHewan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.a5_066.costumWidget.CostumeTopAppBar
import kotlinx.coroutines.launch

@Composable
fun EntryJenisHewanScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertJenisHewanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertjh.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onJenisHewanValueChange = viewModel::updateInsertJhState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertJenisHewan()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    insertUiState: InsertUiState,
    onJenisHewanValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onJenisHewanValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@Composable
fun FormInput(
    modifier: Modifier = Modifier,
    insertUiEvent: InsertUiEvent ,
    onValueChange: (InsertUiEvent) -> Unit = { },
    enabled:Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertUiEvent.id_jenis_hewan,
            onValueChange = { onValueChange(insertUiEvent.copy(id_jenis_hewan = it))},
            label = { Text("Id Jenis Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.nama_jenis_hewan,
            onValueChange = { onValueChange(insertUiEvent.copy(nama_jenis_hewan = it)) },
            label = { Text("Nama Jenis Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsi,
            onValueChange = { onValueChange(insertUiEvent.copy(deskripsi = it)) },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}