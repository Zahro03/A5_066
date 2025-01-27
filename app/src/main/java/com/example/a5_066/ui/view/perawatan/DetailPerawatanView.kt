package com.example.a5_066.ui.view.perawatan

import DestinasiNavigasi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a5_066.costumWidget.CostumeTopAppBar
import com.example.a5_066.model.Perawatan
import com.example.a5_066.ui.viewModel.perawatan.DetailPerawatanViewModel
import com.example.a5_066.ui.viewModel.perawatan.DetailPrUiState

object DestinasiDetailPerawatan : DestinasiNavigasi {
    override val route = "Detail_perawatan"
    override val titleRes = "Detail Perawatan"
    const val id_perawatan = "id_perawatan"
    val routeWithArgs = "$route/{$id_perawatan}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPerawatanScreen(
    navigateBack: () -> Unit,
    onEditClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    detailViewModel: DetailPerawatanViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPerawatan.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack,
                onRefresh = {
                    detailViewModel.getPerawatanById()
                }
            )
        },
    )
    { innerPadding ->
        DetailStatus(
            prUiState = detailViewModel.detailPrUiState,
            retryAction = {detailViewModel.getPerawatanById() },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onEditClick = onEditClick

        )
    }
}

@Composable
fun DetailStatus(
    prUiState: DetailPrUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onEditClick: (String) -> Unit = {},
) {
    when (prUiState) {
        is DetailPrUiState.Success -> {
            DetailPrLayout(
                perawatan = prUiState.perawatan,
                modifier = modifier,
                onEditClick = { onEditClick(it) }
            )
        }

        is DetailPrUiState.Loading -> OnLoading(modifier = modifier)
        is DetailPrUiState.Error -> OnError(retryAction, modifier = modifier)
    }
}

@Composable
fun DetailPrLayout(
    perawatan: Perawatan,
    modifier: Modifier = Modifier,
    onEditClick: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Membungkus seluruh detail mahasiswa dengan Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE)) // Warna abu-abu terang
                .clip(RoundedCornerShape(8.dp))
                .padding(16.dp) // Padding di dalam Box
        ) {
            // Komponen Detail Mahasiswa
            ItemDetailPr(
                perawatan = perawatan,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp)) // Spacer setelah detail

        // Tombol Edit
        Button(
            onClick = { onEditClick(perawatan.id_perawatan) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF424242) // Warna abu-abu gelap
            )
        ) {
            Text(text = "Edit", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun ItemDetailPr(
    modifier: Modifier = Modifier,
    perawatan: Perawatan
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ComponentDetailPr(judul = "Id Perawatan", isinya = perawatan.id_perawatan)
        Spacer(modifier = Modifier.height(12.dp)) // Spacer between each detail
        ComponentDetailPr(judul = "Id Hewan", isinya = perawatan.id_hewan)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPr(judul = "Id Dokter", isinya = perawatan.id_dokter)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPr(judul = "Tanggal Perawatan", isinya = perawatan.tanggal_perawatan)
        Spacer(modifier = Modifier.height(12.dp))
        ComponentDetailPr(judul = "Detail Perawatan", isinya = perawatan.detail_perawatan)
    }
}

@Composable
fun ComponentDetailPr(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$judul: ",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = isinya,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
