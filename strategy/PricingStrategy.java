package src.strategy;
import src.Vehicle;
public interface PricingStrategy {
    double calculate(Vehicle vehicle, int duration);
}
