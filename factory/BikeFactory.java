package src.factory;
import src.Vehicle;
public class BikeFactory extends VehicleFactory {
    public Vehicle createVehicle() { return new Bike(); }
}
