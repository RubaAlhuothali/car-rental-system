
package carrentalsystem;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;

public class CarInventory{
    private ArrayList<Car> cars;
    private Queue<Customer> waitingList;

    public CarInventory() {
        this.cars = new ArrayList<>();
        this.waitingList = new ArrayDeque<>();
    }

     /* add new Car*/

    public void addCar(Car car) {
        cars.add(car);
    }
    /* remove existing Car*/

    public void removeCar(Car car) {
        cars.remove(car);
    }
     /* search inventory by make and return list of matching results
 *@return List containing all matching results in inventory by make parameter
 */
    public ArrayList<Car> searchCarsByMake(String make) {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getMake().equals(make)) {
                result.add(car);
            }
        }
        return result;
    }
    /* search inventory by model and return list of matching results
 */
    public ArrayList<Car> searchCarsByModel(String model) {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getModel().equals(model)) {
                result.add(car);
            }
        }
        return result;
    }
     /*search inventory by year and return list of matching results
 */
    public ArrayList<Car> searchCarsByYear(int year) {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYear() == year) {
                result.add(car);
            }
        }
        return result;
    }
        /*Method that sorts all cars in inventory by their year and returns sorted list. 
    Uses TreeMap data structure internally.  
      }*/
       /* add new customer to waiting list for rent car*/  
    public void addToWaitingList(Customer customer) {
        waitingList.add(customer);
    }

    public Customer getNextCustomerFromWaitingList() {
        return waitingList.poll();
    }
    
}



