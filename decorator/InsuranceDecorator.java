package src.decorator;
public class InsuranceDecorator implements RentalCost {
    private RentalCost rental;
    public InsuranceDecorator(RentalCost rental) { this.rental = rental; }
    public double getCost() { return rental.getCost() + 500; }
}
