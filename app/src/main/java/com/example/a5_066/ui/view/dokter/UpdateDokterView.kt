package com.example.a5_066.ui.view.dokter

import HalamanController
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.costumWidget.CostumeTopAppBar
import com.example.a5_066.ui.viewModel.dokter.UpdateDokterViewModel
import kotlinx.coroutines.launch

object DestinasiUpdate : HalamanController {
    override val route = "Update"
    override val titleRes = "Edit Dokter"
    const val id_Dokter = "id_Dokter"
    val routeWithArgs = "$route/{$id_Dokter}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDokterScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    updateDokterViewModel: UpdateDokterViewModel = viewModel() // Make sure it's the correct ViewModel
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Edit Dokter",
                canNavigateBack = true,
                navigateUp = navigateBack,
                onBackPressed = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            insertUiState = updateDokterViewModel.updateDokterUiState, // Ensure correct state
            onDokterValueChange = updateDokterViewModel::updateInsertDokterState, // Make sure this function is correct
            onSaveClick = {
                coroutineScope.launch {
                    updateDokterViewModel.updateDokter() // Update logic
                    onNavigateUp() // Navigation after update
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

