
package carrentalsystem;
import java.util.Date;

class Rental {
    private Customer customer;
    private Car car;
    private Date startDate;
    private Date endDate;
    

    public Rental(Customer customer, Car car, Date startDate, Date endDate) {
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;

        car.setRented(true);
        car.addRentalToHistory(this);
    }
   
    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
