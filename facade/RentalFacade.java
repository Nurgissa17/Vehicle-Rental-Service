package src.facade;
import src.Vehicle;
import src.factory.VehicleFactory;
import src.strategy.*;
import src.decorator.*;
import src.adapter.*;
import src.observer.Observer;
public class RentalFacade {
    public Vehicle rent(VehicleFactory factory, PricingStrategy strategy, int duration, boolean insurance, Observer observer) {
        Vehicle vehicle = factory.createVehicle();
        vehicle.addObserver(observer);
        double price = new RentalPricing(strategy).calculate(vehicle, duration);
        RentalCost cost = new BasicRental(price);
        if (insurance) cost = new InsuranceDecorator(cost);
        Payment payment = new PaymentAdapter(new OldPaymentService());
        payment.processPayment(cost.getCost());
        vehicle.setAvailable(false);
        return vehicle;
    }
    public void returnVehicle(Vehicle vehicle) {
        vehicle.setAvailable(true);
    }
}
