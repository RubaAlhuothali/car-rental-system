
package carrentalsystem;
/*

 */
import java.util.Stack;
public class Car {
    private String make;
    private String model;
    private int year;
    private double dailyRentalPrice;
    private boolean isRented;
    private Stack<Rental> rentalHistory;


     public Car(String make, String model, int year, double dailyRentalPrice) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.dailyRentalPrice = dailyRentalPrice;
        this.isRented = false;
        this.rentalHistory = new Stack<>();
    }
    
    public String getMake() {
        return make;
    }
   
    public String getModel() {
        return model;
    }
    
    
    public int getYear() {
        return year;
    }
    

    public double getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    public boolean isRented() {
        return isRented;
    }
    /*
     * @param rented True if the car is rented, false otherwise.
     */
    public void setRented(boolean rented) {
        isRented = rented;
    }

    /*
     * Adds a renter to the rental history of the car.
     */
    public void addRentalToHistory(Rental rental) {
        rentalHistory.push(rental);
    }
 
}
