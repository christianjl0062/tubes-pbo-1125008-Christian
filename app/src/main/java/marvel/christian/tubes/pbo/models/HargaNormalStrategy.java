package marvel.christian.tubes.pbo.models;

// bagian Christian
public class HargaNormalStrategy implements PricingStrategy {
    @Override
    public double calculateFinalPrice(double basePrice) {
        return basePrice;
    }
}
