package src.observer;
import src.Vehicle;
public class Customer implements Observer {
    private String name;
    public Customer(String name) { this.name = name; }
    public void update(Vehicle vehicle) {
        System.out.println(name + ": " + vehicle.getName() + " is available again");
    }
}
