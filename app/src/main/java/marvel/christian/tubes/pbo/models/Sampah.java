package marvel.christian.tubes.pbo.models;

// Christian
public abstract class Sampah implements Recycleable, PriceCountable {
    private String kodeSampah;
    private String namaSampah;
    private double hargaSampahPerKg;

    public Sampah(String kodeSampah, String namaSampah, double hargaSampahPerKg) {
        this.kodeSampah = kodeSampah;
        this.namaSampah = namaSampah;
        this.hargaSampahPerKg = hargaSampahPerKg;
    }

    public String getKodeSampah() {
        return kodeSampah;
    }
    public void setKodeSampah(String kodeSampah) {
        this.kodeSampah = kodeSampah;
    }
    public String getNamaSampah() {
        return namaSampah;
    }
    public void setNamaSampah(String namaSampah) {
        this.namaSampah = namaSampah;
    }
    public double getHargaSampahPerKg() {
        return hargaSampahPerKg;
    }
    public void setHargaSampahPerKg(double hargaSampahPerKg) {
        this.hargaSampahPerKg = hargaSampahPerKg;
    }

    public abstract String infoSampah();

    @Override
    public double hitungHarga(double beratSampahKg) {
        return beratSampahKg * this.hargaSampahPerKg;
    }

    @Override
    public String recycleSampah() {
        return "Sampah " + this.namaSampah + " akan diproses sesuai jenis sampahnya.";
    }
}
    

