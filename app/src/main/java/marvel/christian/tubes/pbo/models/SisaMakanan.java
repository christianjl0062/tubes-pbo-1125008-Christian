package marvel.christian.tubes.pbo.models;

// Marvel
public class SisaMakanan extends SampahOrganik {
    private boolean berbauTajam;

    public SisaMakanan(String kodeSampah, String namaSampah, double hargaSampahPerKg, int estimasiHariBusuk, boolean berbauTajam) {
        super(kodeSampah, namaSampah, hargaSampahPerKg, estimasiHariBusuk);
        this.berbauTajam = berbauTajam;
    }

    @Override
    public String infoSampah() {
        return "Sisa makanan " + getNamaSampah() + " akan membusuk dalam "
                + getEstimasiHariBusuk() + " hari. " +
                (berbauTajam ? "Harap ditutup rapat karena berbau tajam." : "");
    }

    @Override
    public String recycleSampah() {
        return "Sisa makanan " + getNamaSampah() + " diolah menjadi pupuk kompos cair dengan cairan EM4.";
    }

    public boolean isBerbauTajam() {
        return berbauTajam;
    }

    public void setBerbauTajam(boolean berbauTajam) {
        this.berbauTajam = berbauTajam;
    }
}