package src;
import java.util.Scanner;
import src.factory.*;
import src.strategy.*;
import src.observer.Customer;
import src.facade.RentalFacade;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vehicle (1-Car, 2-Bike, 3-Van): ");
        int choice = scanner.nextInt();
        VehicleFactory factory;
        if (choice == 2) factory = new BikeFactory();
        else if (choice == 3) factory = new VanFactory();
        else factory = new CarFactory();
        System.out.print("Pricing (1-Hourly, 2-Daily): ");
        PricingStrategy strategy;
        if (scanner.nextInt() == 2) strategy = new DailyPricing();
        else strategy = new HourlyPricing();
        System.out.print("Duration: ");
        int duration = scanner.nextInt();
        System.out.print("Insurance (1-Yes, 2-No): ");
        boolean insurance = scanner.nextInt() == 1;
        RentalFacade facade = new RentalFacade();
        Vehicle vehicle = facade.rent(factory, strategy, duration, insurance, new Customer("Linsh"));
        facade.returnVehicle(vehicle);
    }
}
