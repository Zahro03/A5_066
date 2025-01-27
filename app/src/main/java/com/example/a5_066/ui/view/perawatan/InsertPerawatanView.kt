package com.example.a5_066.ui.view.perawatan

import DestinasiNavigasi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.costumWidget.CostumeTopAppBar
import com.example.a5_066.ui.viewModel.perawatan.InsertPerawatanUiEvent
import com.example.a5_066.ui.viewModel.perawatan.InsertPerawatanUiState
import com.example.a5_066.ui.viewModel.perawatan.InsertPerawatanViewModel
import kotlinx.coroutines.launch


object DestinasiInsertPerawatan : DestinasiNavigasi{
    override val route = "item_entry"
    override val titleRes = "Entry Perawatan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPerawatanScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPerawatanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertPerawatan.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            insertPerawatanUiState = viewModel.uiState,
            onPerawatanValueChange = viewModel::updateInsertPerawatanState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertPerawatan()
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
    insertPerawatanUiState: InsertPerawatanUiState,
    onPerawatanValueChange: (InsertPerawatanUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertPerawatanUiEvent = insertPerawatanUiState.insertPerawatanUiEvent,
            onValueChange = onPerawatanValueChange,
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
    insertPerawatanUiEvent:InsertPerawatanUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPerawatanUiEvent) -> Unit = {},
    enabled: Boolean = true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertPerawatanUiEvent.id_perawatan,
            onValueChange = { onValueChange(insertPerawatanUiEvent.copy(id_perawatan = it))},
            label = { Text("ID Perawatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertPerawatanUiEvent.id_hewan,
            onValueChange = { onValueChange(insertPerawatanUiEvent.copy(id_hewan = it)) },
            label = { Text("ID Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertPerawatanUiEvent.id_dokter,
            onValueChange = { onValueChange(insertPerawatanUiEvent.copy(id_dokter = it)) },
            label = { Text("ID Dokter") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertPerawatanUiEvent.tanggal_perawatan,
            onValueChange = { onValueChange(insertPerawatanUiEvent.copy(tanggal_perawatan = it)) },
            label = { Text("Tanggal Perawatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertPerawatanUiEvent.detail_perawatan,
            onValueChange = { onValueChange(insertPerawatanUiEvent.copy(detail_perawatan = it)) },
            label = { Text("Detail Perawatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = "Isi Semua Data Perawatan!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}