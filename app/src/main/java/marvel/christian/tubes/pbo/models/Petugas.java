package marvel.christian.tubes.pbo.models;

// bagiannya Marvel
public class Petugas extends User {
    public Petugas(String idUser, String username, String password, String namaLengkap) {
        super(idUser, username, password, namaLengkap, "PETUGAS");
    }

    @Override
    public String getInfoHakAkses() {
        return "Hak akses petugas == Dapat mencatat setoran sampah & cek instruksi daur ulang.";
    }
}