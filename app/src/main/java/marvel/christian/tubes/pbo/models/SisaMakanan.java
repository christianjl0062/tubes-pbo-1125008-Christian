package marvel.christian.tubes.pbo.models;

// Bagian Marvel
public class SisaMakanan extends SampahOrganik {
    private boolean berbauTajam;

    public SisaMakanan(String kodeSampah, String namaSampah, double hargaSampahPerKg, int estimasiHariBusuk, boolean berbauTajam) {
        super(kodeSampah, namaSampah, hargaSampahPerKg, estimasiHariBusuk);
        this.berbauTajam = berbauTajam;
    }

    @Override
    public String infoSampah() {
        String info = "Sisa makanan " + getNamaSampah() + 
        " akan membusuk dalam " + getEstimasiHariBusuk() + " hari. ";
        String infoTambahan = "hati hati, bau nya bakal tajam ke hidung...";

        if (berbauTajam) {
            return info + infoTambahan;
        } else {
            return info;
        }
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