import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a5_066.ui.view.Homepage
import com.example.a5_066.ui.view.jenisHewan.HomeJenisHewanScreen
import com.example.a5_066.ui.view.pasien.DestinasiDetailPasien
import com.example.a5_066.ui.view.pasien.DestinasiInsertPasien
import com.example.a5_066.ui.view.pasien.DetailPasienScreen
import com.example.a5_066.ui.view.pasien.EntryPasienScreen
import com.example.a5_066.ui.view.pasien.UpdatePasienScreen
import com.example.a5_066.ui.view.perawatan.DestinasiHomePerawatan
import com.example.uaspam.ui.View.PasienHewan.HomePasienScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomePage.route,
    ) {
        composable(route = DestinasiHomePage.route) {
            Homepage(
                onItemClick = { item ->
                    when (item) {
                        "Pasien" -> navController.navigate(DestinasiHomePasien.route)
                        "Jenis Hewan" -> navController.navigate(DestinasiHomejh.route)
                        "Dokter" -> navController.navigate(DestinasiHomeDokter.route)
                        "Perawatan" -> navController.navigate(DestinasiHomePerawatan.route)
                    }
                }
            )
        }

        composable(DestinasiHomePasien.route) {
            HomePasienScreen(
                navigateToItemEntry = { navController.navigate(DestinasiInsertPasien.route) },
                onDetailClick = { id_hewan ->
                    navController.navigate("${DestinasiDetailPasien.route}/$id_hewan")
                }
            )
        }

        composable(DestinasiInsertPasien.route) {
            EntryPasienScreen(navigateBack = {
                navController.navigate(DestinasiHomePasien.route) {
                    popUpTo(DestinasiHomePasien.route) { inclusive = true }
                }
            })
        }

        composable(
            DestinasiDetailPasien.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPasien.id_hewan) {
                type = NavType.StringType
            })
        ) {
            val id_hewan = it.arguments?.getString(DestinasiDetailPasien.id_hewan)
            id_hewan?.let {
                DetailPasienScreen(
                    navigateBack = { navController.navigateUp() },
                    onEditClick = { id ->
                        navController.navigate("${DestinasiUpdatePasien.route}/$id")
                    }
                )
            }
        }

        composable(
            DestinasiUpdatePasien.routeWithArgs,
            arguments = listOf(navArgument(DestinasiUpdatePasien.id_hewan) { type = NavType.StringType })
        ) { backStackEntry ->
            val id_hewan = backStackEntry.arguments?.getString(DestinasiUpdatePasien.id_hewan)
            id_hewan?.let {
                UpdatePasienScreen(
                    id_hewan = it,
                    onBack = { navController.navigateUp() },
                    onNavigate = {
                        navController.navigate(DestinasiHomePasien.route) {
                            popUpTo(DestinasiHomePasien.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        composable(DestinasiHomejh.route) {
            HomeJenisHewanScreen(
                navigateToItemEntry = { navController.navigate(DestinasiInsertJh.route) },
                onDetailClick = { id_jenis_hewan ->
                    navController.navigate("${DestinasiUpdateJh.route}/$id_jenis_hewan")
                }
            )
        }

        composable(DestinasiInsertJh.route) {
            EntryJenisHewanScreen(navigateBack = {
                navController.navigate(DestinasiHomejh.route) {
                    popUpTo(DestinasiHomejh.route) { inclusive = true }
                }
            })
        }

        composable(
            DestinasiUpdateJh.routeWithArgs,
            arguments = listOf(navArgument(DestinasiUpdateJh.id_jenis_hewan) { type = NavType.StringType })
        ) { backStackEntry ->
            val id_jenis_hewan = backStackEntry.arguments?.getString(DestinasiUpdateJh.id_jenis_hewan)
            id_jenis_hewan?.let {
                UpdateJenisHewanScreen(
                    id_jenis_hewan = it,
                    navigateBack = { navController.navigateUp() },
                    onNavigate = {
                        navController.navigate(DestinasiHomejh.route) {
                            popUpTo(DestinasiHomejh.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        composable(DestinasiHomeDokter.route) {
            HomeDokterScreen(
                navigateToItemEntry = { navController.navigate(DestinasiInsertDokter.route) },
                onDetailClick = { id_dokter ->
                    navController.navigate("${DestinasiUpdateDokter.route}/$id_dokter")
                }
            )
        }

        composable(DestinasiInsertDokter.route) {
            EntryDokterScreen(navigateBack = {
                navController.navigate(DestinasiHomeDokter.route) {
                    popUpTo(DestinasiHomeDokter.route) { inclusive = true }
                }
            })
        }

        composable(
            DestinasiUpdateDokter.routeWithArgs,
            arguments = listOf(navArgument(DestinasiUpdateDokter.id_Dokter) { type = NavType.StringType })
        ) { backStackEntry ->
            val id_dokter = backStackEntry.arguments?.getString(DestinasiUpdateDokter.id_Dokter)
            id_dokter?.let {
                UpdateDokterScreen(
                    id_dokter = it,
                    navigateBack = { navController.navigateUp() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeDokter.route) {
                            popUpTo(DestinasiHomeDokter.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        composable(DestinasiHomePerawatan.route) {
            HomePerawatanScreen(
                navigateToItemEntry = { navController.navigate(DestinasiInsertPerawatan.route) },
                onDetailClick = { id_perawatan ->
                    navController.navigate("${DestinasiUpdatePerawatan.route}/$id_perawatan")
                }
            )
        }

        composable(DestinasiInsertPerawatan.route) {
            EntryPerawatanScreen(navigateBack = {
                navController.navigate(DestinasiHomePerawatan.route) {
                    popUpTo(DestinasiHomePerawatan.route) { inclusive = true }
                }
            })
        }

        composable(
            DestinasiUpdatePerawatan.routeWithArgs,
            arguments = listOf(navArgument(DestinasiUpdatePerawatan.id_perawatan) { type = NavType.StringType })
        ) { backStackEntry ->
            val id_perawatan = backStackEntry.arguments?.getString(DestinasiUpdatePerawatan.id_perawatan)
            id_perawatan?.let {
                UpdatePerawatanScreen(
                    id_perawatan = it,
                    navigateBack = { navController.navigateUp() },
                    onNavigate = {
                        navController.navigate(DestinasiHomePerawatan.route) {
                            popUpTo(DestinasiHomePerawatan.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}



