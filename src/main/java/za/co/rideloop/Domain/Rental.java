package za.co.rideloop.Domain;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Rental.java
 * Rental Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalID;
    private int carID;
    private int customerID;
    private LocalDate startDate;
    private LocalDate endDate;
    private String pickupLocation;
    private String dropoffLocation;
    private int insuranceID;

    private double totalCost;
    private String status;

    protected Rental() {
    }

    private Rental(RentalBuilder builder) {
        this.rentalID = builder.rentalID;
        this.carID = builder.carID;
        this.customerID = builder.customerID;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.pickupLocation = builder.pickupLocation;
        this.dropoffLocation = builder.dropoffLocation;
        this.insuranceID = builder.insuranceID;
        this.totalCost = builder.totalCost;
        this.status = builder.status;
    }

    public int getRentalID() {
        return rentalID;
    }

    public int getCarID() {
        return carID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public int getInsuranceID() {
        return insuranceID;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "RentalRepository{" +
                "rentalID=" + rentalID +
                ", carID=" + carID +
                ", customerID=" + customerID +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropoffLocation='" + dropoffLocation + '\'' +
                ", insuranceID=" + insuranceID +
                ", totalCost=" + totalCost +
                ", status='" + status + '\'' +
                '}';
    }
    public static class RentalBuilder {
        private int rentalID;
        private int carID;
        private int customerID;
        private LocalDate startDate;
        private LocalDate endDate;
        private String pickupLocation;
        private String dropoffLocation;
        private int insuranceID;
        private double totalCost;
        private String status;

        public RentalBuilder() {
        }

        public RentalBuilder setRentalID(int rentalID) {
            this.rentalID = rentalID;
            return this;
        }

        public RentalBuilder setCarID(int carID) {
            this.carID = carID;
            return this;
        }

        public RentalBuilder setCustomerID(int customerID) {
            this.customerID = customerID;
            return this;
        }

        public RentalBuilder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public RentalBuilder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public RentalBuilder setPickupLocation(String pickupLocation) {
            this.pickupLocation = pickupLocation;
            return this;
        }

        public RentalBuilder setDropoffLocation(String dropoffLocation) {
            this.dropoffLocation = dropoffLocation;
            return this;
        }

        public RentalBuilder setInsuranceID(int insuranceID) {
            this.insuranceID = insuranceID;
            return this;
        }

        public RentalBuilder setTotalCost(double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public RentalBuilder setStatus(String status) {
            this.status = status;
            return this;
        }
        public RentalBuilder RentalBuilderCopy(Rental rental) {
            this.rentalID = rental.rentalID;
            this.carID = rental.carID;
            this.customerID = rental.customerID;
            this.startDate = rental.startDate;
            this.endDate = rental.endDate;
            this.pickupLocation = rental.pickupLocation;
            this.dropoffLocation = rental.dropoffLocation;
            this.insuranceID = rental.insuranceID;
            this.totalCost = rental.totalCost;
            this.status = rental.status;
            return this;
        }

        public Rental build() {
            // You can add validation here if needed
            return new Rental(this);
        }
    }
}
