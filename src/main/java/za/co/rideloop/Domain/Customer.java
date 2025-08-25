package za.co.rideloop.Domain;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String licenseNumber;
    private String username;
    private String password;
    private String status;

    @Embedded
    private Address address;

    @Embedded
    private ContactDetails contactDetails;

    public Customer() {}

    protected Customer(Builder builder) {
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

    // COMPLETE GETTERS AND SETTERS
    public Long getCustomerID() { return customerID; }
    public void setCustomerID(Long customerID) { this.customerID = customerID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public ContactDetails getContactDetails() { return contactDetails; }
    public void setContactDetails(ContactDetails contactDetails) { this.contactDetails = contactDetails; }

    // Builder class
    public static class Builder {
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

        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setPhone(String phone) { this.phone = phone; return this; }
        public Builder setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; return this; }
        public Builder setUsername(String username) { this.username = username; return this; }
        public Builder setPassword(String password) { this.password = password; return this; }
        public Builder setStatus(String status) { this.status = status; return this; }
        public Builder setAddress(Address address) { this.address = address; return this; }
        public Builder setContactDetails(ContactDetails contactDetails) { this.contactDetails = contactDetails; return this; }

        public Customer build() { return new Customer(this); }
    }
}