package za.co.rideloop.Domain;

import jakarta.persistence.Embeddable;

/**
 * ContactDetails.java
 * ContactDetails model class (Embeddable for JPA)
 *
 * Author: Rotondwa Rambau
 * Student Number: 222342145
 * Group: 3I
 */
@Embeddable
public class ContactDetails {

    private String contactEmail;
    private String contactNumber;
    private String alternativeContactNum;

    public ContactDetails() {}

    private ContactDetails(Builder builder) {
        this.contactEmail = builder.contactEmail;
        this.contactNumber = builder.contactNumber;
        this.alternativeContactNum = builder.alternativeContactNum;
    }

    // Getters
    public String getContactEmail() { return contactEmail; }
    public String getContactNumber() { return contactNumber; }
    public String getAlternativeContactNum() { return alternativeContactNum; }

    // Setters
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setAlternativeContactNum(String alternativeContactNum) { this.alternativeContactNum = alternativeContactNum; }

    // Builder
    public static class Builder {
        private String contactEmail;
        private String contactNumber;
        private String alternativeContactNum;

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
