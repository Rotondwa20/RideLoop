package za.co.rideloop.Domain;

import java.util.Date;
import java.util.Objects;

/**
 * RideLoop
 * CarSupplier.java
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @date : 5/12/2025
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
public class CarSupplier {
    private final int supplierID;
    private final String name;
    private final String contactPerson;
    private final Date supplyDate;
    private final String contractStatus;

    /**
     * Private constructor used by the Builder.
     * Can only be instantiated through the Builder.
     */
    private CarSupplier(Builder builder) {
        this.supplierID = builder.supplierID;
        this.name = builder.name;
        this.contactPerson = builder.contactPerson;
        this.supplyDate = builder.supplyDate;
        this.contractStatus = builder.contractStatus;
    }

    // Getters
    public int getSupplierID() {
        return supplierID;
    }

    public String getName() {
        return name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Date getSupplyDate() {
        return supplyDate != null ? new Date(supplyDate.getTime()) : null; // Return a copy to maintain immutability
    }

    public String getContractStatus() {
        return contractStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarSupplier that = (CarSupplier) o;
        return supplierID == that.supplierID &&
                Objects.equals(name, that.name) &&
                Objects.equals(contactPerson, that.contactPerson) &&
                Objects.equals(supplyDate, that.supplyDate) &&
                Objects.equals(contractStatus, that.contractStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierID, name, contactPerson, supplyDate, contractStatus);
    }

    @Override
    public String toString() {
        return "CarSupplier{" +
                "supplierID=" + supplierID +
                ", name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", supplyDate=" + supplyDate +
                ", contractStatus='" + contractStatus + '\'' +
                '}';
    }

    /**
     * Builder class for CarSupplier.
     * Implements the Builder Pattern to create CarSupplier instances in a fluent API style.
     */
    public static class Builder {
        private int supplierID;
        private String name;
        private String contactPerson;
        private Date supplyDate;
        private String contractStatus;

        public Builder supplierID(int supplierID) {
            this.supplierID = supplierID;
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

        public Builder supplyDate(Date supplyDate) {
            this.supplyDate = supplyDate != null ? new Date(supplyDate.getTime()) : null; // Create a copy to maintain immutability
            return this;
        }

        public Builder contractStatus(String contractStatus) {
            this.contractStatus = contractStatus;
            return this;
        }

        public CarSupplier build() {
            return new CarSupplier(this);
        }
    }
}
