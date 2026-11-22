package marvel.christian.tubes.pbo.models;

import marvel.christian.tubes.pbo.exception.BankSampahException;

public class SampahFactory {
    public Sampah buatSampah(String jenisSampah) throws BankSampahException {
        Sampah hasil;

        if (jenisSampah == null || jenisSampah.isEmpty()) {
            throw new BankSampahException("Jenis sampah tolong jangan kosong yah!");
        } else {
            String jenisSampahBersih = jenisSampah.toLowerCase();

            if (jenisSampahBersih.equals("botol plastik")) {
                hasil = new BotolPlastik("SAM-621", "Botol Plastik", 3000.0, "Plastik", "PET");
            } else if (jenisSampahBersih.equals("kardus")) {
                hasil = new Kardus("SAM-224", "Kardus", 2000.0, true);
            } else if (jenisSampahBersih.equals("sisa makanan") || jenisSampahBersih.equals("sisamakanan")) {
                hasil = new SisaMakanan("SAM-199", "Sisa Makanan", 1000.0, 3, true);
            } else {
                throw new BankSampahException("Jenis sampahnya maaf tidak dikenali: " + jenisSampah);
            }
        }
        return hasil;
    }
}
