package marvel.christian.tubes.pbo.models;

// Bagian Marvel
public class Nasabah extends User {
    public Nasabah(String idUser, String username, String password, String namaLengkap) {
        super(idUser, username, password, namaLengkap, "Andi Budiman");
    }

    @Override
    public String getInfoHakAkses() {
        return "Hak Akses Nasabah ==> bisa nyetor sampah + lihat estimasi harganya.";
    }
}
