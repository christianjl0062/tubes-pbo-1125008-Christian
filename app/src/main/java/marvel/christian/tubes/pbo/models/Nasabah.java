package marvel.christian.tubes.pbo.models;

// Bagian Marvel
public class Nasabah extends User {
    private double saldo;

    public Nasabah(String idUser, String username, String password, String namaLengkap) {
        super(idUser, username, password, namaLengkap, "NASABAH");
        this.saldo = 0.0;
    }

    @Override
    public String getInfoHakAkses() {
        return "Hak Akses Nasabah ==> bisa nyetor sampah + lihat estimasi harganya.";
    }

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

