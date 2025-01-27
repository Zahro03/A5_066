interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiHomePage : DestinasiNavigasi{
    override val route = "Home Page"
    override val titleRes = "Home Page"
}

object DestinasiHomePasien : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Home Mhs"
}

object DestinasiHomejh : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Home Jenis Hewan"
}

object DestinasiHomeDokter : DestinasiNavigasi{
    override val route = "home"
    override val titleRes = "Home Dokter"
}

object DestinasiHomePerawatan : DestinasiNavigasi{
    override val route = "home"
    override val titleRes = "Home Perawatan"
}

object DestinasiUpdatePasien : DestinasiNavigasi {
    override val route = "updatePasien"
    override val titleRes = "Edit Pasien"

    const val id_hewan = "id_hewan"  // Ubah nama parameter untuk konsistensi
    val routesWithArgs = "$route/{$id_hewan}"  // Mendefinisikan rute dengan argumen
}

object DestinasiUpdateDokter : DestinasiNavigasi {
    override val route = "Update_dokter"
    override val titleRes= "Edit Dokter"
    const val id_Dokter = "id_Dokter"
    val routeWithArgs = "$route/{$id_Dokter}"
}

object DestinasiUpdateJh : DestinasiNavigasi {
    override val route = "Update_jenisHewan"
    override val titleRes= "Edit Pasien"
    const val id_jenis_hewan = "id_jenis_hewan"
    val routeWithArgs = "$route/{$id_jenis_hewan}"
}

object DestinasiUpdatePerawatan : DestinasiNavigasi {
    override val route = "Update_pasien"
    override val titleRes= "Edit Pasien"
    const val id_perawatan = "id_perawatan"
    val routeWithArgs = "$route/{$id_perawatan}"
}

object DestinasiInsertJh : DestinasiNavigasi{
    override val route = "Tambah_JenisHewan"
    override val titleRes = "Tambah Jenis Hewan"
}

object DestinasiInsertPasien : DestinasiNavigasi{
    override val route = "Tambah_Pasien"
    override val titleRes = "Tambah Pasien"
}

object DestinasiInsertDokter : DestinasiNavigasi{
    override val route = "Tambah_Dokter"
    override val titleRes = "Tambah Dokter"
}

object DestinasiInsertPerawatan : DestinasiNavigasi{
    override val route = "Tambah_Perawatan"
    override val titleRes = "Tambah Perawatan"
}

object DestinasiDetailPasien : DestinasiNavigasi {
    override val route = "Detail_pasien"
    override val titleRes = "Detail Pasien"
    const val id_hewan = "id_hewan"
    val routeWithArgs = "$route/{$id_hewan}"
}

