package marvel.christian.tubes.pbo.models;

// Bagian nya Marvel
public class Admin extends User {
    public Admin(String idUser, String username, String password, String namaLengkap) {
        super(idUser, username, password, namaLengkap, "ADMIN");
    }

    @Override
    public String getInfoHakAkses() {
        return "Hak akses admin: kontrol penuh ke si transaksi dan perhitungan bonus harga sampahnya.";
    }
}
