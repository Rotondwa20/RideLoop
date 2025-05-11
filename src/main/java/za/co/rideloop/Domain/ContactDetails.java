package za.co.rideloop.Domain;


/**
 * ContactDetails.java
 * ContactDetails model class
 *
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */

public class ContactDetails {
    private String contactEmail;
    private String contactNumber;
    private String alternativeContactNum;

    private ContactDetails(Builder builder) {
        this.contactEmail = builder.contactEmail;
        this.contactNumber = builder.contactNumber;
        this.alternativeContactNum = builder.alternativeContactNum;
    }

    public ContactDetails() {

    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAlternativeContactNum() {
        return alternativeContactNum;
    }

    public static class Builder {
        private String contactEmail;
        private String contactNumber;
        private String alternativeContactNum;

        public Builder() {
            // No-argument constructor
        }

        public Builder setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
            return this;
        }

        public Builder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder setAlternativeContactNum(String alternativeContactNum) {
            this.alternativeContactNum = alternativeContactNum;
            return this;
        }

        public ContactDetails build() {
            return new ContactDetails(this);
        }
    }
}