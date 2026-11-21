package marvel.christian.tubes.pbo.models;

// Christian
public class SampahOrganik extends Sampah {
    private int estimasiHariBusuk; 
    
    public SampahOrganik(String kodeSampah, String namaSampah, double hargaSampahPerKg, int estimasiHariBusuk) {
        super(kodeSampah, namaSampah, hargaSampahPerKg);
        this.estimasiHariBusuk = estimasiHariBusuk;
    }

    public int getEstimasiHariBusuk() {
        return estimasiHariBusuk;
    }
    public void setEstimasiHariBusuk(int estimasiHariBusuk) {
        this.estimasiHariBusuk = estimasiHariBusuk;
    }

    @Override
    public String infoSampah() {
        return "Sampah organik ini akan membusuk setelah waktu " + estimasiHariBusuk + " hari.";
    }

    @Override
    public String recycleSampah() {
        return "Sampah " + getNamaSampah() + " diolah dengan cara ditimbun di lubang biopori atau dijadikan pupuk kompos.";
    }
}
