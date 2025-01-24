package com.example.a5_066.ui.view.pasien

import HalamanController
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.costumWidget.CostumeTopAppBar
import com.example.a5_066.ui.viewModel.PenyediaViewModel
import com.example.a5_066.ui.viewModel.pasien.UpdateViewModel
import kotlinx.coroutines.launch

object DestinasiUpdate : HalamanController {
    override val route = "Update_pasien"
    override val titleRes= "Edit Pasien"
    const val id_hewan = "id_hewan"
    val routeWithArgs = "$route/{$id_hewan}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePasienScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    updateViewModel: UpdateViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Edit Pasien (Hewan)",
                canNavigateBack = true,
                navigateUp = navigateBack,
                onBackPressed = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            insertUiState = updateViewModel.updatePasienUiState,
            onPasienValueChange = updateViewModel::updateInsertPasienState,
            onSaveClick = {
                coroutineScope.launch {
                    updateViewModel.updatePasien()
                    onNavigateUp()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
