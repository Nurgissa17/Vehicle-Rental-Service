package src.strategy;
import src.Vehicle;
public class HourlyPricing implements PricingStrategy {
    public double calculate(Vehicle vehicle, int duration) {
        return vehicle.getHourlyRate() * duration;
    }
}