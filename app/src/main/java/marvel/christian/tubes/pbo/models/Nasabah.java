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

    // override: bagian christian
    @Override
    public String getInfoHakAkses() {
        return "Hak Akses Nasabah ==> bisa nyetor sampah + lihat estimasi harganya.";
    }

    @Override
    public String toString() {
        return getNamaLengkap() + " (" + getUsername() + ") - Saldo: Rp " + saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
