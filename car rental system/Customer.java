
package carrentalsystem;


 
class Customer {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private Car rentedCar;

 
    public Customer(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
   
    public String getName() {
        return name;
    }
    

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    /*
  * Car object representing current rented car by customer
  */
  public Car getRentedCar() {
      return rentedCar; 
  }
  
  /*@param rentedCar Car object to set as currently rented by customer
  */
  public void setRentedCar(Car rentedCar) {
      this.rentedCar = rentedCar; 
  }
}
