package src.decorator;
public class BasicRental implements RentalCost {
    private double cost;
    public BasicRental(double cost) { this.cost = cost; }
    public double getCost() { return cost; }
}
