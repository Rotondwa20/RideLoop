package za.co.rideloop.Domain;

import jakarta.persistence.*;

@Entity
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private int profileID;

    private String firstName;
    private String lastName;
    private String idNumber;
    private String licenseNumber;
    private String phoneNumber;

    private String status;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Embedded
    private Address address;

    public CustomerProfile() {
        this.status = "pending"; // default status
    }

    private CustomerProfile(Builder builder) {
        this.user = builder.user;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.idNumber = builder.idNumber;
        this.licenseNumber = builder.licenseNumber;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.status = builder.status != null ? builder.status : "pending"; // default
    }

    public int getProfileID() { return profileID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getIdNumber() { return idNumber; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getStatus() { return status; }
    public User getUser() { return user; }
    public Address getAddress() { return address; }
    public String getEmail() { return user != null ? user.getEmail() : null; }
    public String getUsername() { return user != null ? user.getUsername() : null; }


    public void setProfileID(int profileID) { this.profileID = profileID; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setStatus(String status) { this.status = status; }
    public void setUser(User user) { this.user = user; }
    public void setAddress(Address address) { this.address = address; }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String idNumber;
        private String licenseNumber;
        private String phoneNumber;
        private String status;
        private User user;
        private Address address;

        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setIdNumber(String idNumber) { this.idNumber = idNumber; return this; }
        public Builder setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; return this; }
        public Builder setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder setStatus(String status) { this.status = status; return this; }
        public Builder setUser(User user) { this.user = user; return this; }
        public Builder setAddress(Address address) { this.address = address; return this; }

        public CustomerProfile build() {
            if (this.status == null) this.status = "pending"; // default
            return new CustomerProfile(this);
        }
    }
}
