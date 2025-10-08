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

    private String status; // new status field

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Embedded
    private Address address;

    // ---------- Documents ----------
    @Lob
    @Column(name = "id_document", columnDefinition = "MEDIUMBLOB")
    private byte[] idDocument;

    @Lob
    @Column(name = "license_doc", columnDefinition = "MEDIUMBLOB")
    private byte[] licenseDoc;

    @Lob
    @Column(name = "id_copy", columnDefinition = "MEDIUMBLOB")
    private byte[] idCopy;

    @Lob
    @Column(name = "proof_of_residence", columnDefinition = "MEDIUMBLOB")
    private byte[] proofOfResidence;

    // ---------- Profile Picture ----------
    @Lob
    @Column(name = "profile_picture", columnDefinition = "MEDIUMBLOB")
    private byte[] profilePicture;

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
        this.status = builder.status != null ? builder.status : "pending";

        this.idDocument = builder.idDocument;
        this.licenseDoc = builder.licenseDoc;
        this.idCopy = builder.idCopy;
        this.proofOfResidence = builder.proofOfResidence;
        this.profilePicture = builder.profilePicture;
    }

    // ---------- Getters ----------
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

    public byte[] getIdDocument() { return idDocument; }
    public byte[] getLicenseDoc() { return licenseDoc; }
    public byte[] getIdCopy() { return idCopy; }
    public byte[] getProofOfResidence() { return proofOfResidence; }
    public byte[] getProfilePicture() { return profilePicture; }

    // ---------- Setters ----------
    public void setProfileID(int profileID) { this.profileID = profileID; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setStatus(String status) { this.status = status; }
    public void setUser(User user) { this.user = user; }
    public void setAddress(Address address) { this.address = address; }

    public void setIdDocument(byte[] idDocument) { this.idDocument = idDocument; }
    public void setLicenseDoc(byte[] licenseDoc) { this.licenseDoc = licenseDoc; }
    public void setIdCopy(byte[] idCopy) { this.idCopy = idCopy; }
    public void setProofOfResidence(byte[] proofOfResidence) { this.proofOfResidence = proofOfResidence; }
    public void setProfilePicture(byte[] profilePicture) { this.profilePicture = profilePicture; }

    // ---------- Builder ----------
    public static class Builder {
        private int profileID;
        private String firstName;
        private String lastName;
        private String idNumber;
        private String licenseNumber;
        private String phoneNumber;
        private String status;
        private User user;
        private Address address;

        private byte[] idDocument;
        private byte[] licenseDoc;
        private byte[] idCopy;
        private byte[] proofOfResidence;
        private byte[] profilePicture;

        public Builder setProfileID(int profileID) {
            this.profileID = profileID;
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

        public Builder setIdNumber(String idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public Builder setLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setIdDocument(byte[] idDocument) {
            this.idDocument = idDocument;
            return this;
        }

        public Builder setLicenseDoc(byte[] licenseDoc) {
            this.licenseDoc = licenseDoc;
            return this;
        }

        public Builder setIdCopy(byte[] idCopy) {
            this.idCopy = idCopy;
            return this;
        }

        public Builder setProofOfResidence(byte[] proofOfResidence) {
            this.proofOfResidence = proofOfResidence;
            return this;
        }

        public Builder setProfilePicture(byte[] profilePicture) {
            this.profilePicture = profilePicture;
            return this;
        }

        public CustomerProfile build() {
            if (this.status == null) this.status = "pending";
            CustomerProfile profile = new CustomerProfile(this);
            profile.setProfileID(this.profileID);
            return profile;
        }
    }
}
