

# Vehicle Rental Service

This is our Java console project for renting cars, bikes and vans. We used six design patterns in one program.

## Team

- Nurgissa Pernebay - Factory Method, Facade
- Abish Adil - Observer, Strategy
- Adilgereyev Nuraly - Adapter, Decorator

## Design Patterns

- **Factory Method** - `CarFactory`, `BikeFactory` and `VanFactory` create vehicles.
- **Observer** - `Customer` gets a message when a vehicle is available again.
- **Strategy** - `HourlyPricing` and `DailyPricing` calculate the rental price.
- **Decorator** - `InsuranceDecorator` adds 500 if the user chooses insurance.
- **Adapter** - `PaymentAdapter` connects `Payment` with `OldPaymentService`.
- **Facade** - `RentalFacade` brings all the rental steps together.

## UML Class Diagram

```mermaid
classDiagram
    class Vehicle {
        <<abstract>>
    }
    class VehicleFactory {
        <<abstract>>
    }
    class PricingStrategy {
        <<interface>>
    }
    class Subject {
        <<interface>>
    }
    class Observer {
        <<interface>>
    }
    class RentalCost {
        <<interface>>
    }
    class Payment {
        <<interface>>
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

    Main --> RentalFacade
    RentalFacade --> VehicleFactory
    RentalFacade --> RentalPricing
    RentalFacade --> RentalCost
    RentalFacade --> Payment
    RentalFacade --> Vehicle
```

## UML Sequence Diagram

```mermaid
sequenceDiagram
    actor User
    participant Main
    participant RentalFacade
    participant VehicleFactory
    participant Vehicle
    participant Customer

    User->>Main: Select vehicle, price type, duration, insurance
    Main->>RentalFacade: rent(...)
    RentalFacade->>VehicleFactory: createVehicle()
    VehicleFactory-->>RentalFacade: Vehicle
    Note over RentalFacade: Calculate price, add insurance, process payment
    RentalFacade->>Vehicle: setAvailable(false)
    RentalFacade-->>Main: Vehicle
    Main->>RentalFacade: returnVehicle(vehicle)
    RentalFacade->>Vehicle: setAvailable(true)
    Vehicle->>Customer: update(vehicle)
```

## How to Run

You need Java JDK. Open a terminal in the project folder and run:

```bash
mkdir -p out
javac -d out Main.java Vehicle.java factory/*.java observer/*.java strategy/*.java decorator/*.java adapter/*.java facade/*.java
java -cp out src.Main
```

The program asks for a vehicle, hourly or daily pricing, duration and insurance. Payment is simulated in the console, and the vehicle is returned at the end of the demo.
