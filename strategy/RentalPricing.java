package src.strategy;
import src.Vehicle;
public class RentalPricing {
    private PricingStrategy strategy;
    public RentalPricing(PricingStrategy strategy) { this.strategy = strategy; }
    public void setStrategy(PricingStrategy strategy) { this.strategy = strategy; }
    public double calculate(Vehicle vehicle, int duration) { return strategy.calculate(vehicle, duration); }
}
