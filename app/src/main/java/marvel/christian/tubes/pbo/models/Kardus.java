package marvel.christian.tubes.pbo.models;

// Marvel
public class Kardus extends SampahAnorganik {
    private boolean kering;

    public Kardus(String kodeSampah, String namaSampah, double hargaSampahPerKg, boolean kering) {
        super(kodeSampah, namaSampah, hargaSampahPerKg, "Karton");
        this.kering = kering;
    }

    @Override
    public String infoSampah() {
        if (kering == true) {
            return "Kardus " + getNamaSampah() + " sudah kering dan layak untuk ditumpuk rapi.";
        } else {
            return "Kardus " + getNamaSampah() + " basah dan tidak akan diterima" + 
            "sebelum dikeringkan.";
        }
    }

    @Override
    public String recycleSampah() {
        return "Kardus dan kertas bekas akan direndam dan dilebur kembali menjadi bubur kertas (pulp) untuk lembaran kardus baru.";
    }

    public boolean isKering() {
        return kering;
    }

    public void setKering(boolean kering) {
        this.kering = kering;
    }
}