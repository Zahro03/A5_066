@file:Suppress("NAME_SHADOWING")

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.a5_066.ui.view.Homepage
import com.example.a5_066.ui.view.dokter.DetailDokterScreen
import com.example.a5_066.ui.view.dokter.HomeDokterView
import com.example.a5_066.ui.view.dokter.InsertDokterView
import com.example.a5_066.ui.view.dokter.UpdateDokterScreen
import com.example.a5_066.ui.view.jenisHewan.HomejenisHewanView
import com.example.a5_066.ui.view.jenisHewan.InsertjenisHewanView
import com.example.a5_066.ui.view.pasien.DestinasiEntry
import com.example.a5_066.ui.view.pasien.DestinasiUpdate
import com.example.a5_066.ui.view.pasien.DetailPasienScreen
import com.example.a5_066.ui.view.pasien.HomePasienView
import com.example.a5_066.ui.view.pasien.InsertPasienView
import com.example.a5_066.ui.view.pasien.UpdatePasienScreen
import com.example.a5_066.ui.view.perawatan.DestinasiHomePerawatan

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        // Homepage Route
        composable(DestinasiHome.route) {
            Homepage(
                onItemClick = { item ->
                    when (item) {
                        "Pasien" -> navController.navigate(DestinasiHomePasien.route)
                        "Dokter" -> navController.navigate(DestinasiHomeDokter.route)
                        "Jenis Hewan" ->navController.navigate(DestinasiHomejh.route)
                        "Perawatan" -> navController.navigate(DestinasiHomePerawatan.route)
                        else -> {}
                    }
                }
            )
        }

        // Pasien Routes
        composable(DestinasiHomePasien.route) {
            HomePasienView(
                onAddPsnClick = { navController.navigate(DestinasiInsertPasien.route) },
                onBackArrow = { navController.popBackStack() }
            )
        }

        composable(DestinasiInsertPasien.route) {
            InsertPasienView(
                onBackArrow = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() }
            )
        }

        composable(
            DestinasiDetailPasien.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailPasien.id_hewan) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_hewan = it.arguments?.getString(DestinasiDetailPasien.id_hewan)
            id_hewan?.let { id_hewan ->
                DetailPasienScreen(
                    navigateBack = { navController.navigateUp() },
                    onEditClick = { id_hewan ->
                        navController.navigate("${DestinasiUpdatePasien.route}/$id_hewan")
                    }
                )
            }
        }

        composable(
            DestinasiUpdatePasien.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailPasien.id_hewan) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdatePasienScreen(
                onBackArrow = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() }
            )
        }

        // Dokter Routes
        composable(DestinasiHomeDokter.route) {
            HomeDokterView(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { id_Dokter ->
                    navController.navigate("${DestinasiDetailDokter.route}/$id_Dokter")
                }
            )
        }

        composable(DestinasiInsertDokter.route) {
            InsertDokterView(
                onBackArrow = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() }
            )
        }

        composable(
            DestinasiDetailDokter.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailDokter.id_Dokter) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_Dokter = it.arguments?.getString(DestinasiDetailDokter.id_Dokter)
            id_Dokter?.let { id_Dokter ->
                DetailDokterScreen(
                    navigateBack = { navController.navigateUp() },
                    onEditClick = { id_Dokter ->
                        navController.navigate("${DestinasiUpdateDokter.route}/$id_Dokter")
                    }
                )
            }
        }

        composable(
            DestinasiUpdateDokter.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailDokter.id_Dokter) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateDokterScreen(
                onBackArrow = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() }
            )
        }

        // Jenis Hewan Routes
        composable(DestinasiHomejh.route) {
            HomejenisHewanView(
                onAddjenisHewanClick = { navController.navigate(DestinasiInsertjh.route) },
                onDetailjenisHewanClick = { id_jenis_hewan ->
                    navController.navigate("${DestinasiDetailjh.route}/$id_jenis_hewan")
                },
                onBackArrow = { navController.popBackStack() }
            )
        }

        composable(DestinasiInsertjh.route) {
            InsertjenisHewanView(
                onBackArrow = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() }
            )
        }

        // Tambah Pasien Deep Link
        composable(
            "tambah_pasien",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.a5_066/tambah_pasien" })
        ) {
            // Tampilan untuk tambah pasien
            InsertPasienView(
                onBackArrow = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() }
            )
        }
    }
}
