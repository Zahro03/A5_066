package com.example.a5_066.ui.view.jenisHewan

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.ui.viewModel.PenyediaViewModel

@Composable
fun HomejenisHewanView(
    modifier: Modifier = Modifier,
    viewModel: HomejenisHewanViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddjenisHewanClick: () -> Unit = {},
    onDetailjenisHewanClick: (String) -> Unit = {},
    onBackArrow: () -> Unit
){

}