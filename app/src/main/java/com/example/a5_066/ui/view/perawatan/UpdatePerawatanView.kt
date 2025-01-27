package com.example.a5_066.ui.view.perawatan

import DestinasiNavigasi
import UpdatePerawatanViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.costumWidget.CostumeTopAppBar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePerawatanScreen(
    navigateBack:() -> Unit,
    onNavigateUp:() -> Unit,
    modifier: Modifier = Modifier,
    updatePerawatanViewModel: UpdatePerawatanViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Update Mahasiswa",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            insertPerawatanUiState =  updatePerawatanViewModel.updatePerawatanUiState.value,
            onPerawatanValueChange =  updatePerawatanViewModel::updateInsertPrState,
            onSaveClick = {
                coroutineScope.launch {
                    updatePerawatanViewModel.updatePerawatan()
                    onNavigateUp()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
