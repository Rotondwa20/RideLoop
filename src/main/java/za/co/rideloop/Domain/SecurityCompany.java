package za.co.rideloop.Domain;

import java.util.Date;
import java.util.Objects;

/**
 * RideLoop
 * SecurityCompany.java
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @date : 5/12/2025
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
public class SecurityCompany {
    private final int securityCompanyID;
    private final String name;
    private final String contactPerson;
    private final String phone;
    private final String email;
    private final String serviceType;
    private final Date contractStartDate;
    private final Date contractEndDate;
    private final double monthlyFee;
    private final String emergencyHotline;
    private final String coverageArea;

    /**
     * Private constructor used by the Builder.
     * Can only be instantiated through the Builder.
     */
    private SecurityCompany(Builder builder) {
        this.securityCompanyID = builder.securityCompanyID;
        this.name = builder.name;
        this.contactPerson = builder.contactPerson;
        this.phone = builder.phone;
        this.email = builder.email;
        this.serviceType = builder.serviceType;
        this.contractStartDate = builder.contractStartDate;
        this.contractEndDate = builder.contractEndDate;
        this.monthlyFee = builder.monthlyFee;
        this.emergencyHotline = builder.emergencyHotline;
        this.coverageArea = builder.coverageArea;
    }

    // Getters
    public int getSecurityCompanyID() {
        return securityCompanyID;
    }

    public String getName() {
        return name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Date getContractStartDate() {
        return contractStartDate != null ? new Date(contractStartDate.getTime()) : null; // Return a copy to maintain immutability
    }

    public Date getContractEndDate() {
        return contractEndDate != null ? new Date(contractEndDate.getTime()) : null; // Return a copy to maintain immutability
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public String getEmergencyHotline() {
        return emergencyHotline;
    }

    public String getCoverageArea() {
        return coverageArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityCompany that = (SecurityCompany) o;
        return securityCompanyID == that.securityCompanyID &&
                Double.compare(that.monthlyFee, monthlyFee) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(contactPerson, that.contactPerson) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(serviceType, that.serviceType) &&
                Objects.equals(contractStartDate, that.contractStartDate) &&
                Objects.equals(contractEndDate, that.contractEndDate) &&
                Objects.equals(emergencyHotline, that.emergencyHotline) &&
                Objects.equals(coverageArea, that.coverageArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(securityCompanyID, name, contactPerson, phone, email, serviceType,
                contractStartDate, contractEndDate, monthlyFee, emergencyHotline, coverageArea);
    }

    @Override
    public String toString() {
        return "SecurityCompany{" +
                "securityCompanyID=" + securityCompanyID +
                ", name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", contractStartDate=" + contractStartDate +
                ", contractEndDate=" + contractEndDate +
                ", monthlyFee=" + monthlyFee +
                ", emergencyHotline='" + emergencyHotline + '\'' +
                ", coverageArea='" + coverageArea + '\'' +
                '}';
    }

    /**
     * Builder class for SecurityCompany.
     * Implements the Builder Pattern to create SecurityCompany instances in a fluent API style.
     */
    public static class Builder {
        private int securityCompanyID;
        private String name;
        private String contactPerson;
        private String phone;
        private String email;
        private String serviceType;
        private Date contractStartDate;
        private Date contractEndDate;
        private double monthlyFee;
        private String emergencyHotline;
        private String coverageArea;

        public Builder securityCompanyID(int securityCompanyID) {
            this.securityCompanyID = securityCompanyID;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder contactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder serviceType(String serviceType) {
            this.serviceType = serviceType;
            return this;
        }

        public Builder contractStartDate(Date contractStartDate) {
            this.contractStartDate = contractStartDate != null ? new Date(contractStartDate.getTime()) : null;
            return this;
        }

        public Builder contractEndDate(Date contractEndDate) {
            this.contractEndDate = contractEndDate != null ? new Date(contractEndDate.getTime()) : null;
            return this;
        }

        public Builder monthlyFee(double monthlyFee) {
            this.monthlyFee = monthlyFee;
            return this;
        }

        public Builder emergencyHotline(String emergencyHotline) {
            this.emergencyHotline = emergencyHotline;
            return this;
        }

        public Builder coverageArea(String coverageArea) {
            this.coverageArea = coverageArea;
            return this;
        }

        /**
         * Builds and returns a new SecurityCompany instance.
         * @return A new SecurityCompany instance.
         */
        public SecurityCompany build() {
            return new SecurityCompany(this);
        }
    }
}
