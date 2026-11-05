
# Vehicle Rental Service

Java console project for renting cars, bikes and vans. We used six design patterns for vehicle creation, pricing, insurance, payment and notifications.

## Team

- Nurgissa Pernebay - Factory Method, Facade
- Abish Adil - Observer, Strategy
- Adilgereyev Nuraly - Adapter, Decorator

## Design Patterns

- **Factory Method** - `CarFactory`, `BikeFactory` and `VanFactory` create vehicles.
- **Observer** - `Vehicle` notifies `Customer` when it becomes available again.
- **Strategy** - `HourlyPricing` and `DailyPricing` calculate different rental prices.
- **Decorator** - `InsuranceDecorator` adds 500 to the base price.
- **Adapter** - `PaymentAdapter` connects `Payment` to `OldPaymentService`.
- **Facade** - `RentalFacade` connects all the rental steps.

## How It Works

The user selects a vehicle, hourly or daily pricing, duration and insurance. `RentalFacade` creates the vehicle, calculates the total and processes a simulated payment. For the demo, the vehicle is returned immediately and `Customer` receives a notification.

## UML Class Diagram

```mermaid
classDiagram
    class Vehicle {
        <<abstract>>
        +getName() String
        +getHourlyRate() double
        +getDailyRate() double
        +setAvailable(available) void
    }
    class VehicleFactory {
        <<abstract>>
        +createVehicle() Vehicle
    }
    class PricingStrategy {
        <<interface>>
        +calculate(vehicle, duration) double
    }
    class Subject {
        <<interface>>
        +addObserver(observer) void
        +notifyObservers() void
    }
    class Observer {
        <<interface>>
        +update(vehicle) void
    }
    class RentalCost {
        <<interface>>
        +getCost() double
    }
    class Payment {
        <<interface>>
        +processPayment(amount) void
    }
    class RentalFacade {
        +rent(factory, strategy, duration, insurance, observer) Vehicle
        +returnVehicle(vehicle) void
    }

    Vehicle <|-- Car
    Vehicle <|-- Bike
    Vehicle <|-- Van
    VehicleFactory <|-- CarFactory
    VehicleFactory <|-- BikeFactory
    VehicleFactory <|-- VanFactory
    CarFactory ..> Car : creates
    BikeFactory ..> Bike : creates
    VanFactory ..> Van : creates

    Subject <|.. Vehicle
    Observer <|.. Customer
    Vehicle o-- Observer

    PricingStrategy <|.. HourlyPricing
    PricingStrategy <|.. DailyPricing
    RentalPricing --> PricingStrategy

    RentalCost <|.. BasicRental
    RentalCost <|.. InsuranceDecorator
    InsuranceDecorator --> RentalCost

    Payment <|.. PaymentAdapter
    PaymentAdapter --> OldPaymentService

    Main ..> RentalFacade
    RentalFacade ..> VehicleFactory
    RentalFacade ..> RentalPricing
    RentalFacade ..> RentalCost
    RentalFacade ..> Payment
    RentalFacade ..> Vehicle
```

## UML Sequence Diagram

```mermaid
sequenceDiagram
    participant Main
    participant Facade as RentalFacade
    participant Factory as VehicleFactory
    participant Pricing as RentalPricing
    participant Vehicle
    participant Payment as PaymentAdapter
    participant OldPaymentService
    participant Customer

    Main->>Facade: rent(factory, strategy, duration, insurance, customer)
    Facade->>Factory: createVehicle()
    Factory-->>Facade: Vehicle
    Facade->>Vehicle: addObserver(customer)
    Facade->>Pricing: calculate(vehicle, duration)
    Note over Pricing: Uses HourlyPricing or DailyPricing
    Pricing-->>Facade: base price
    Note over Facade: cost = BasicRental(base price)
    opt Insurance selected
        Note over Facade: cost = InsuranceDecorator(cost)
    end
    Facade->>Payment: processPayment(cost.getCost())
    Payment->>OldPaymentService: pay(amount)
    Facade->>Vehicle: setAvailable(false)
    Facade-->>Main: Vehicle
    Main->>Facade: returnVehicle(vehicle)
    Facade->>Vehicle: setAvailable(true)
    Vehicle->>Customer: update(vehicle)
```

## How to Run

You need Java JDK. Open a terminal in the project folder and run:

```bash
mkdir -p out
javac -d out Main.java Vehicle.java factory/*.java observer/*.java strategy/*.java decorator/*.java adapter/*.java facade/*.java
java -cp out src.Main
```

## Example

Input: `1` (Car), `2` (Daily), `2` days, `1` (Insurance).

Output: `Paid: 12500.0`, then `Linsh: Car is available again`.
