package za.co.rideloop.Domain;

import jakarta.persistence.*;

@Entity
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private int profileID; // now its own ID

    private String firstName;
    private String lastName;
    private String idNumber;
    private String licenseNumber;
    private String phoneNumber;



    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user; // still linked to a user, but no longer dependent on user ID

    @Embedded
    private Address address;

    @Lob
    private byte[] idDocument;

    @Lob
    private byte[] licenseDocument;

    @Lob
    private byte[] proofOfAddress;

    @Lob
    private byte[] profileImage;

    public CustomerProfile() {}

    private CustomerProfile(Builder builder) {
        this.user = builder.user;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.idNumber = builder.idNumber;
        this.licenseNumber = builder.licenseNumber;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.idDocument = builder.idDocument;
        this.licenseDocument = builder.licenseDocument;
        this.proofOfAddress = builder.proofOfAddress;
        this.profileImage = builder.profileImage;
    }

    // ---------- Getters ----------
    public int getProfileID() { return profileID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getIdNumber() { return idNumber; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getPhoneNumber() { return phoneNumber; }
    public User getUser() { return user; }
    public Address getAddress() { return address; }
    public byte[] getIdDocument() { return idDocument; }
    public byte[] getLicenseDocument() { return licenseDocument; }
    public byte[] getProofOfAddress() { return proofOfAddress; }
    public byte[] getProfileImage() { return profileImage; }
    public String getEmail() { return user != null ? user.getEmail() : null; }
    public String getUsername() { return user != null ? user.getUsername() : null; }

    // ---------- Setters ----------
    public void setProfileID(int profileID) { this.profileID = profileID; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setUser(User user) { this.user = user; }
    public void setAddress(Address address) { this.address = address; }
    public void setIdDocument(byte[] idDocument) { this.idDocument = idDocument; }
    public void setLicenseDocument(byte[] licenseDocument) { this.licenseDocument = licenseDocument; }
    public void setProofOfAddress(byte[] proofOfAddress) { this.proofOfAddress = proofOfAddress; }
    public void setProfileImage(byte[] profileImage) { this.profileImage = profileImage; }

    // ---------- Builder ----------
    public static class Builder {
        private String firstName;
        private String lastName;
        private String idNumber;
        private String licenseNumber;
        private String phoneNumber;
        private User user;
        private Address address;
        private byte[] idDocument;
        private byte[] licenseDocument;
        private byte[] proofOfAddress;
        private byte[] profileImage;

        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setIdNumber(String idNumber) { this.idNumber = idNumber; return this; }
        public Builder setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; return this; }
        public Builder setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder setUser(User user) { this.user = user; return this; }
        public Builder setAddress(Address address) { this.address = address; return this; }
        public Builder setIdDocument(byte[] idDocument) { this.idDocument = idDocument; return this; }
        public Builder setLicenseDocument(byte[] licenseDocument) { this.licenseDocument = licenseDocument; return this; }
        public Builder setProofOfAddress(byte[] proofOfAddress) { this.proofOfAddress = proofOfAddress; return this; }
        public Builder setProfileImage(byte[] profileImage) { this.profileImage = profileImage; return this; }

        public CustomerProfile build() { return new CustomerProfile(this); }
    }
}
