package za.co.rideloop.Domain;


/**
 * Customer.java
 * Customer domain class
 *
 * Author : Rotondwa Rambau
 * Student Number : 222342145
 * Group : 3I
 */

public class Customer {

    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String licenseNumber;
    private String username;
    private String password;
    private String status;
    private Address address;
    private ContactDetails contactDetails;
    private int customerRewards;

    // Constructor
    public Customer(int customerID, String firstName, String lastName, String email, String phone,
                    String licenseNumber, String username, String password, String status,
                    Address address, ContactDetails contactDetails, int customerRewards) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.username = username;
        this.password = password;
        this.status = status;
        this.address = address;
        this.contactDetails = contactDetails;
        this.customerRewards = customerRewards;
    }

    // Getters
    public int getCustomerID() { return customerID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getStatus() { return status; }
    public Address getAddress() { return address; }
    public ContactDetails getContactDetails() { return contactDetails; }
    public int getCustomerRewards() { return customerRewards; }
}
