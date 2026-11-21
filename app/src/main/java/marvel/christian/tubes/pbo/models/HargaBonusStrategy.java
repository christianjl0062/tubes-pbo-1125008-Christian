package marvel.christian.tubes.pbo.models;

public class HargaBonusStrategy implements PricingStrategy {
    private double bonusPersen;

    public HargaBonusStrategy(double bonusPersen) {
        this.bonusPersen = bonusPersen;
    }

    @Override
    public double calculateFinalPrice(double basePrice) {
        double bonus = basePrice * (bonusPersen / 100.0);
        return basePrice + bonus;
    }

    public double getBonusPersen() {
        return bonusPersen;
    }
    public void setBonusPersen(double bonusPersen) {
        this.bonusPersen = bonusPersen;
    }
}
