package marvel.christian.tubes.pbo.models;

import marvel.christian.tubes.pbo.exception.SaldoTidakCukupException;

// Bagian Marvel
public class Nasabah extends User {
    private double saldo;

    public Nasabah(String idUser, String username, String password, String namaLengkap) {
        super(idUser, username, password, namaLengkap, "NASABAH");
        this.saldo = 0.0;
    }

    public void tukarSaldoKeUang(double nominal) throws SaldoTidakCukupException {
        if (nominal > this.saldo) {
            throw new SaldoTidakCukupException("Saldo tidak cukup untuk penukaran!");
        }
        this.saldo -= nominal;
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
