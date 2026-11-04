package src.factory;
import src.Vehicle;
public class CarFactory extends VehicleFactory {
    public Vehicle createVehicle() { return new Car(); }
}
