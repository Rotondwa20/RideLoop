package za.co.rideloop.Domain;

import jakarta.persistence.*;

import java.time.LocalDate;
/**
 * CarSupplier.java
 * Incident model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */


@Entity
@Table(name = "car_supplier")

public class CarSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierid")
    private int supplierID;

    private String name;
    private String contactPerson;

    @Column(name = "supply_date", columnDefinition = "DATE") // explicit DATE mapping
    private LocalDate supplyDate;

    @Column(name = "contract_status")
    private String contractStatus;

    protected CarSupplier() { }

    private CarSupplier(Builder builder) {
        this.supplierID = builder.supplierID;
        this.name = builder.name;
        this.contactPerson = builder.contactPerson;
        this.supplyDate = builder.supplyDate;
        this.contractStatus = builder.contractStatus;
    }

    // Getters
    public int getSupplierID() { return supplierID; }
    public String getName() { return name; }
    public String getContactPerson() { return contactPerson; }
    public LocalDate getSupplyDate() { return supplyDate; }
    public String getContractStatus() { return contractStatus; }

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

    public static class Builder {
        private int supplierID;
        private String name;
        private String contactPerson;
        private LocalDate supplyDate;
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

        public Builder supplyDate(LocalDate supplyDate) {
            this.supplyDate = supplyDate;
            return this;
        }

        public Builder contractStatus(String contractStatus) {
            this.contractStatus = contractStatus;
            return this;
        }

        // Method to copy an existing supplier for updates
        public Builder copy(CarSupplier supplier) {
            this.supplierID = supplier.supplierID;
            this.name = supplier.name;
            this.contactPerson = supplier.contactPerson;
            this.supplyDate = supplier.supplyDate;
            this.contractStatus = supplier.contractStatus;
            return this;
        }

        public CarSupplier build() {
            return new CarSupplier(this);
        }
    }
}