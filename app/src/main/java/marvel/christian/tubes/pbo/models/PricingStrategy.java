package marvel.christian.tubes.pbo.models;


public interface PricingStrategy {
    double calculateFinalPrice(double basePrice);
}
