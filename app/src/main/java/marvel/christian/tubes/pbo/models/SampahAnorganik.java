package marvel.christian.tubes.pbo.models;

// Bagian Marvel
public abstract class SampahAnorganik extends Sampah {
    private String jenisBahan;

    public SampahAnorganik(String kodeSampah, String namaSampah, double hargaSampahPerKg, String jenisBahan) {
        super(kodeSampah, namaSampah, hargaSampahPerKg);
        this.jenisBahan = jenisBahan;
    }

    public String getJenisBahan() {
        return jenisBahan;
    }
    public void setJenisBahan(String jenisBahan) {
        this.jenisBahan = jenisBahan;
    }
    @Override
    public String recycleSampah() {
        return "Sampah ini akan dipilah, dihancurkan, di daur ulang, atau di-reuse kembali.";
    }

    @Override
    public String infoSampah() {
        return "Sampah " + getNamaSampah() + " pastikan dalam keadaan kering, " +
                "bersih dari kotoran sisa, dan tidak bercampur dengan sampah organik.";
    }


}
