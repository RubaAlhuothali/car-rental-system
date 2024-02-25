
package carrentalsystem;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CarRentalSystem {
     private CarInventory inventory;
    private ArrayList<Rental> activeRentals;

    public CarRentalSystem() {
        this.inventory = new CarInventory();
        this.activeRentals = new ArrayList<>();
    }
   
    public void addCarToSystem(Car car) {
        inventory.addCar(car);
    }

  public Rental rentCar(Customer customer, Car car, Date startDate, Date endDate) {
    if (car.isRented()) {
        System.out.println("Sorry, this car is already rented.");
        return null;
    } else {
        Rental rental = new Rental(customer, car, startDate, endDate);
        activeRentals.add(rental);
        return rental;
    }
}
public void processReturn(Rental rental) {
    rental.getCar().setRented(false);
    activeRentals.remove(rental);
}
public void showAllRentals() {
    if (activeRentals.size() == 0) {
        System.out.println("There are no active rentals.");
    } else {
        for (Rental rental : activeRentals) {
            System.out.println("Car: " + rental.getCar().getMake() + " " + rental.getCar().getModel() + " " + rental.getCar().getYear());
            System.out.println("\tCustomer: " + rental.getCustomer().getName() + ",Phone:" 
                    + rental.getCustomer().getPhoneNumber() + " ,EmailAddress:" + rental.getCustomer().getEmailAddress());
            System.out.println("\tStart Date: " + rental.getStartDate());
 
            System.out.println("\tEnd Date: " + rental.getEndDate());
            System.out.println("\tTotal Rent: $" +  rental.getCar().getDailyRentalPrice()*7);
            System.out.println();
        }
    }
}
    // rent cars to customers on the waiting list:
    public void rentCarToNextCustomerOnWaitingList(Car car, Date startDate, Date endDate) {
            Customer customer = inventory.getNextCustomerFromWaitingList();
        if (customer == null) {
           
        } else {
            rentCar(customer, car, startDate, endDate);
             System.out.println("Rent the car to the first customer on the waiting list done..");
        }
    }


   public static void main(String[] args) {
    // Create a new instance of the CarRentalSystem class
    CarRentalSystem system = new CarRentalSystem();

    // Add some cars to the system
    system.addCarToSystem(new Car("Toyota", "Camry", 2018, 50.0));
    system.addCarToSystem(new Car("Honda", "Civic", 2019, 45.0));
    system.addCarToSystem(new Car("Ford", "Mustang", 2020, 60.0));

    // read input from the user
    Scanner scanner = new Scanner(System.in);

    // Loop to display the menu and process user input
      System.out.println("\t\tWelcome to the Car Rental System!");
    while (true) {
        // Display the menu options
      
       System.out.println("\t\t**************************");
        System.out.println("1. Rent a car");
        System.out.println("2. Return a car");
        System.out.println("3. Search for cars by make");
        System.out.println("4. Search for cars by model");
        System.out.println("5. Search for cars by year");
        System.out.println("6. Add customer to waiting list");
        System.out.println("7. Show all rentals");
        System.out.println("8. Exit");
         System.out.print("Please choose an option:");

       
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Process the user's choice
        switch (choice) {
            case 1:
                // Rent a car
                // Read the customer's information
                System.out.println("Please enter your name:");
                String name = scanner.nextLine();
                System.out.println("Please enter your phone number:");
                String phoneNumber = scanner.nextLine();
                System.out.println("Please enter your email address:");
                String emailAddress = scanner.nextLine();
                Customer customer = new Customer(name, phoneNumber, emailAddress);

                // Read the make of the car 
                System.out.println("Please enter the make of the car you want to rent:");
                String make = scanner.nextLine();

                // Search for available cars of that make
                ArrayList<Car> availableCars = system.inventory.searchCarsByMake(make);
                if (availableCars.size() == 0) {
                    // If no cars are available, display a message and go back to the menu
                    System.out.println("Sorry, we don't have any cars of that make available.");
                    break;
                }

                // Choose the first available car to rent
                Car carToRent = availableCars.get(0);

                // Set the rental start date to today and the end date to one week from today
                Date startDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                calendar.add(Calendar.DATE, 7);
                Date endDate = calendar.getTime();

                // Rent the car to the customer
                system.rentCar(customer, carToRent, startDate, endDate);
                 System.out.println("Rent done..");
                break;
            case 2:
                // Return a car
                      
                // Check if there are any active rentals
                if (system.activeRentals.size() == 0) {
                    // If there are no active rentals, display a message and go back to the menu
                    System.out.println("There are no active rentals.");
                    break;
                }

                // Display a list of active rentals for the user to choose from
                for (int i = 0; i < system.activeRentals.size(); i++) {
                    Rental rental = system.activeRentals.get(i);
                    System.out.println((i + 1) + ". " + rental.getCar().getMake() + " " + rental.getCar().getModel() 
                            + " rented by " + rental.getCustomer().getName());
                }
                System.out.println("Please choose a rental to return:");
              
                int rentalIndex = scanner.nextInt();
                scanner.nextLine();

              
                if (rentalIndex < 1 || rentalIndex > system.activeRentals.size()) {
                    // If the user's choice is invalid, display a message and go back to the menu
                    System.out.println("Invalid option. Please try again.");
                    break;
                }

                // Get the selected rental from the list of active rentals
                Rental rentalToReturn = system.activeRentals.get(rentalIndex - 1);

                // Process the return of the selected rental
                system.processReturn(rentalToReturn);

                // Rent the car to the next customer on the waiting list
                Date startDate1 = new Date();
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(startDate1);
                calendar1.add(Calendar.DATE, 7);
                Date endDate1 = calendar1.getTime();
                system.rentCarToNextCustomerOnWaitingList(rentalToReturn.getCar(), startDate1, endDate1);

                break;

            case 3:
                // Search for cars by make
                System.out.println("Please enter the make of the car you want to search for:");
                make = scanner.nextLine();

                // Search for available cars of that make
                availableCars = system.inventory.searchCarsByMake(make);
                if (availableCars.size() == 0) {
                    // If no cars are available, display a message and go back to the menu
                    System.out.println("Sorry, we don't have any cars of that make available.");
                    break;
                }

                // Display the available cars
                for (Car car : availableCars) {
                    System.out.println(car.getMake() + " " + car.getModel() + " " + car.getYear());
                }
                break;
            case 4:
                // Search for cars by model
                System.out.println("Please enter the model of the car you want to search for:");
                String model = scanner.nextLine();

                // Search for available cars of that model
                availableCars = system.inventory.searchCarsByModel(model);
                if (availableCars.size() == 0) {
                    // If no cars are available, display a message and go back to the menu
                    System.out.println("Sorry, we don't have any cars of that model available.");
                    break;
                }

                // Display the available cars
                for (Car car : availableCars) {
                    System.out.println(car.getMake() + " " + car.getModel() + " " + car.getYear());
                }
                  break;
            case 5:
                // Search for cars by year
                System.out.println("Please enter the year of the car you want to search for:");
                int year = scanner.nextInt();
                scanner.nextLine();

                // Search for available cars of that year
                availableCars = system.inventory.searchCarsByYear(year);
                if (availableCars.size() == 0) {
                    // If no cars are available, display a message and go back to the menu
                    System.out.println("Sorry, we don't have any cars of that year available.");
                    break;
                }

                // Display the available cars
                for (Car car : availableCars) {
                    System.out.println(car.getMake() + " " + car.getModel() + " " + car.getYear());
                }
                break;
            case 6:
                // Add customer to waiting list
                // Read the customer's information
                System.out.println("Please enter your name:");
                name = scanner.nextLine();
                System.out.println("Please enter your phone number:");
                phoneNumber = scanner.nextLine();
                System.out.println("Please enter your email address:");
                emailAddress = scanner.nextLine();
                customer = new Customer(name, phoneNumber, emailAddress);

                // Add the customer to the waiting list
                system.inventory.addToWaitingList(customer);
                break;
            case 7:
                // Show all rentals
                system.showAllRentals();
                break;
            case 8:
                // Exit
                // Display a message and exit the program
                System.out.println("Thank you for using the Car Rental System!");
                scanner.close();
                return;
            default:
                // Invalid option
                // Display a message and go back to the menu
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
}
}
        