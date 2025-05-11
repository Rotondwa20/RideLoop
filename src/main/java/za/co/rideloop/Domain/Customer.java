package za.co.rideloop.Domain;


/**
 * Customer.java
 * Customer model class
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
    private Address address; // Aggregation
    private ContactDetails contactDetails; // Composition

    protected Customer() {
    }

    protected Customer(Builder builder) {
        this.customerID = builder.customerID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.licenseNumber = builder.licenseNumber;
        this.username = builder.username;
        this.password = builder.password;
        this.status = builder.status;
        this.address = builder.address;
        this.contactDetails = builder.contactDetails;
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

    // Builder class
    public static class Builder {
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

        public Builder setCustomerID(int customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setContactDetails(ContactDetails contactDetails) {
            this.contactDetails = contactDetails;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}