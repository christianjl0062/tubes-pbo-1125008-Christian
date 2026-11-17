package marvel.christian.tubes.pbo.models;

// Marvel
public class BotolPlastik extends SampahAnorganik {
    private String tipePlastik;

    public BotolPlastik(String kodeSampah, String namaSampah, double hargaSampahPerKg,
                        String jenisBahan, String tipePlastik) {
        super(kodeSampah, namaSampah, hargaSampahPerKg, jenisBahan);
        this.tipePlastik = tipePlastik;
    }

    @Override
    public String infoSampah() {
        return "Pastikan botol plastik " + getNamaSampah() + " yang bertipe " + this.tipePlastik +
                "sudah dicuci sampai bersih, labelnya sudah dilepasin, " +
                "dan bentuk akhirnya sudah dipipihkan.";
    }

    @Override
    public String recycleSampah() {
        return "Botol plastik " + this.tipePlastik + " akan " +
                "dihancurin pakai mesin untuk diubah menjadi " +
                "bijih plastik agar bisa didaur ulang.";
    }

    public String getTipePlastik() {
        return tipePlastik;
    }
    public void setTipePlastik(String tipePlastik) {
        this.tipePlastik = tipePlastik;
    }
}
