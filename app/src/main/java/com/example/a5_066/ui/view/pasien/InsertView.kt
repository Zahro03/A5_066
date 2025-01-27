package com.example.a5_066.ui.view.pasien

import DestinasiNavigasi
import InsertPasienUiEvent
import InsertPasienUiState
import InsertViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.R
import com.example.a5_066.costumWidget.CostumeTopAppBar
import com.example.a5_066.model.JenisHewan
import kotlinx.coroutines.launch

object DestinasiInsertPasien : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Entry Hewan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPasienScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior() // Membuat header lebih dekat ke atas

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertPasien.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            insertPasienUiState = viewModel.uiState,
            jenisList = viewModel.jenisList,
            onPasienValueChange = viewModel::updateInsertPasienState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertPasien()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()) // Aktifkan scrolling
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    insertPasienUiState: InsertPasienUiState,
    jenisList:List<JenisHewan>,
    onPasienValueChange: (InsertPasienUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(16.dp)
    ) {
        FormInput(
            insertPasienUiEvent = insertPasienUiState.insertPasienUiEvent,
            insertPasienUiState = insertPasienUiState,
            jenisList = jenisList,
            onValueChange = onPasienValueChange,
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
    insertPasienUiState: InsertPasienUiState,
    insertPasienUiEvent: InsertPasienUiEvent,
    modifier: Modifier = Modifier,
    jenisList: List<JenisHewan>,
    onValueChange: (InsertPasienUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // ID Hewan
        OutlinedTextField(
            value = insertPasienUiEvent.id_hewan,
            onValueChange = { onValueChange(insertPasienUiEvent.copy(id_hewan = it)) },
            label = { Text("Id Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        // Validasi untuk ID Hewan
        if (insertPasienUiEvent.id_hewan.isEmpty()) {
            Text(
                text = "ID Hewan wajib diisi.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Nama Hewan
        OutlinedTextField(
            value = insertPasienUiEvent.nama_hewan,
            onValueChange = { onValueChange(insertPasienUiEvent.copy(nama_hewan = it)) },
            label = { Text("Nama Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Jenis Hewan ID
        OutlinedTextField(
            value = insertPasienUiEvent.jenis_hewan_id,
            onValueChange = { onValueChange(insertPasienUiEvent.copy(jenis_hewan_id = it)) },
            label = { Text("Jenis Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Pemilik Hewan
        OutlinedTextField(
            value = insertPasienUiEvent.pemilik,
            onValueChange = { onValueChange(insertPasienUiEvent.copy(pemilik = it)) },
            label = { Text("Pemilik Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Kontak Pemilik Hewan
        OutlinedTextField(
            value = insertPasienUiEvent.kontak_pemilik,
            onValueChange = { onValueChange(insertPasienUiEvent.copy(kontak_pemilik = it)) },
            label = { Text("Kontak Pemilik") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Tanggal Lahir Hewan
        OutlinedTextField(
            value = insertPasienUiEvent.tanggal_lahir,
            onValueChange = { onValueChange(insertPasienUiEvent.copy(tanggal_lahir = it)) },
            label = { Text("Tanggal Lahir Hewan") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Catatan Kesehatan
        OutlinedTextField(
            value = insertPasienUiEvent.catatan_kesehatan,
            onValueChange = { onValueChange(insertPasienUiEvent.copy(catatan_kesehatan = it)) },
            label = { Text("Catatan Kesehatan") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        Divider(
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}
