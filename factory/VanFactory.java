package src.factory;
import src.Vehicle;
public class VanFactory extends VehicleFactory {
    public Vehicle createVehicle() { return new Van(); }
}