package com.example.a5_066.ui.view.dokter

import DestinasiNavigasi
import UpdateDokterViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.R
import com.example.a5_066.costumWidget.CostumeTopAppBar
import kotlinx.coroutines.launch

object DestinasiUpdateDokter : DestinasiNavigasi {
    override val route = "Update"
    override val titleRes = "Edit Dokter"
    const val id_Dokter = "id_Dokter"
    val routeWithArgs = "$route/{$id_Dokter}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDokterScreen(
    navigateBack:() -> Unit,
    onNavigateUp:() -> Unit,
    modifier: Modifier = Modifier,
    updateDokterViewModel: UpdateDokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = {
            CostumeTopAppBar(
                title = "Update Dokter",
                canNavigateBack = false,
                navigateUp = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            insertUiState = updateDokterViewModel.updateDokterUiState,
            onDokterValueChange = updateDokterViewModel::updateInsertDokterState,
            onSaveClick = {
                coroutineScope.launch {
                    updateDokterViewModel.updateDokter()
                    onNavigateUp()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

