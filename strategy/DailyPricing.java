package src.strategy;
import src.Vehicle;
public class DailyPricing implements PricingStrategy {
    public double calculate(Vehicle vehicle, int duration) {
        return vehicle.getDailyRate() * duration;
    }
}
