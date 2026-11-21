package marvel.christian.tubes.pbo.models;

public class HargaNormalStrategy implements PricingStrategy {
    @Override
    public double calculateFinalPrice(double basePrice) {
        return basePrice;
    }
}
