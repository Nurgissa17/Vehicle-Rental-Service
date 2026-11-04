package src;
import java.util.ArrayList;
import java.util.List;
import src.observer.Observer;
import src.observer.Subject;
public abstract class Vehicle implements Subject {
    private String name;
    private double hourlyRate;
    private double dailyRate;
    private boolean available = true;
    private List<Observer> observers = new ArrayList<>();
    public Vehicle(String name, double hourlyRate, double dailyRate) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
    }
    public String getName() { return name; }
    public double getHourlyRate() { return hourlyRate; }
    public double getDailyRate() { return dailyRate; }
    public void addObserver(Observer observer) { observers.add(observer); }
    public void removeObserver(Observer observer) { observers.remove(observer); }
    public void notifyObservers() {
        for (Observer observer : observers) observer.update(this);
    }
    public void setAvailable(boolean available) {
        if (this.available == available) return;
        this.available = available;
        if (available) notifyObservers();
    }
}
