interface HalamanController {
    val route: String
    val titleRes: String
}

object DestinasiHome : HalamanController {
    override val route = "home"
    override val titleRes = "Home"
}

object DestinasiHomePasien : HalamanController {
    override val route = "pasien"
    override val titleRes = "Pasien Home"
}

object DestinasiInsertPasien : HalamanController {
    override val route = "pasien/add"
    override val titleRes = "Tambah Pasien"
}

object DestinasiDetailPasien : HalamanController {
    override val route = "pasien"
    val id_hewan = "id_hewan"  // Ubah nama parameter untuk konsistensi
    override val titleRes = "Detail Pasien"
    val routesWithArg = "$route/{$id_hewan}"  // Mendefinisikan rute dengan argumen
}

object DestinasiUpdatePasien : HalamanController {
    override val route = "updatePasien"
    val id_hewan = "id_hewan"  // Ubah nama parameter untuk konsistensi
    override val titleRes = "Ubah Pasien"
    val routesWithArg = "$route/{$id_hewan}"  // Mendefinisikan rute dengan argumen
}

object DestinasiHomeDokter : HalamanController {
    override val route = "dokter"
    override val titleRes = "Dokter Home"
}

object DestinasiInsertDokter : HalamanController {
    override val route = "dokter/add"
    override val titleRes = "Tambah Dokter"
}

object DestinasiDetailDokter : HalamanController {
    override val route = "dokter"
    val id_Dokter = "id_Dokter"
    override val titleRes = "Detail Dokter"
    val routesWithArg = "$route/{$id_Dokter}"  // Mendefinisikan rute dengan argumen
}

object DestinasiUpdateDokter : HalamanController {
    override val route = "updateDokter"
    val id_Dokter = "id_Dokter"
    override val titleRes = "Ubah Dokter"
    val routesWithArg = "$route/{$id_Dokter}"  // Mendefinisikan rute dengan argumen
}
