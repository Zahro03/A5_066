import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a5_066.ui.view.Homepage
import com.example.a5_066.ui.view.dokter.EntryDokterScreen
import com.example.a5_066.ui.view.pasien.DestinasiEntry
import com.example.a5_066.ui.view.pasien.DetailView
import com.example.a5_066.ui.view.pasien.EntryPasienScreen
import com.example.a5_066.ui.view.pasien.HomeScreen
import com.example.a5_066.ui.view.pasien.UpdatePasienScreen

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
                        else -> {}
                    }
                }
            )
        }

        // Pasien Routes
        composable(DestinasiHomePasien.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsertPasien.route) // Menavigasi ke halaman tambah pasien
                },
                onDetailClick = { id_hewan ->
                    navController.navigate("${DestinasiDetailPasien.routesWithArg}/$id_hewan")
                }
            )
        }

        composable(DestinasiInsertPasien.route) {
            EntryPasienScreen(
                navigateBack = {
                    navController.popBackStack(DestinasiHome.route, inclusive = true)
                }
            )
        }

        composable(
            DestinasiDetailPasien.routesWithArg,
            arguments = listOf(navArgument(DestinasiDetailPasien.id_hewan) { type = NavType.StringType })
        ) { backStackEntry ->
            val id_hewan = backStackEntry.arguments?.getString(DestinasiDetailPasien.id_hewan)
            id_hewan?.let {
                DetailView(
                    NavigateBack = {
                        navController.popBackStack(DestinasiHome.route, inclusive = true)
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdatePasien.routesWithArg}/$it")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdatePasien.routesWithArg,
            arguments = listOf(navArgument(DestinasiDetailPasien.id_hewan) { type = NavType.StringType })
        ) { backStackEntry ->
            val id_hewan = backStackEntry.arguments?.getString(DestinasiDetailPasien.id_hewan)
            id_hewan?.let {
                UpdatePasienScreen(
                    navigateBack = { navController.navigateUp() },
                    onNavigateUp = {
                        navController.popBackStack(DestinasiHome.route, inclusive = true)
                    }
                )
            }
        }

        // Dokter Routes
        composable(DestinasiHomeDokter.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsertDokter.route) // Menavigasi ke halaman tambah dokter
                },
                onDetailClick = { id_Dokter ->
                    navController.navigate("${DestinasiDetailDokter.routesWithArg}/$id_Dokter")
                }
            )
        }

        composable(DestinasiInsertDokter.route) {
            EntryDokterScreen(
                navigateBack = {
                    navController.popBackStack(DestinasiHome.route, inclusive = true)
                }
            )
        }
    }
}

