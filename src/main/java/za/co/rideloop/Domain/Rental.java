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
    private LocalDate date;
    private int pickupLocation;
    private int dropoffLocation;
    private double totalCost;
    private double distanceInKm;
    protected Rental() {
    }

    private Rental(RentalBuilder builder) {
        this.rentalID = builder.rentalID;
        this.carID = builder.carID;
        this.customerID = builder.customerID;
        this.date = builder.date;
        this.pickupLocation = builder.pickupLocation;
        this.dropoffLocation = builder.dropoffLocation;
        this.totalCost = builder.totalCost;
        this.distanceInKm = builder.distanceInKm;
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

    public LocalDate getDate() {
        return date;
    }

    public int  getPickupLocation() {
        return pickupLocation;
    }

    public int getDropoffLocation() {
        return dropoffLocation;
    }

    public double getTotalCost() {
        return totalCost;
    }


    public double getDistanceInKm() {
        return distanceInKm;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalID=" + rentalID +
                ", carID=" + carID +
                ", customerID=" + customerID +
                ", date=" + date +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropoffLocation='" + dropoffLocation + '\'' +
                ", totalCost=" + totalCost +
                ", distanceInKm=" + distanceInKm +
                '}';
    }

    public static class RentalBuilder {
        private int rentalID;
        private int carID;
        private int customerID;
        private LocalDate date;
        private int pickupLocation;
        private int dropoffLocation;
        private double totalCost;

        private double distanceInKm;

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

        public RentalBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public RentalBuilder setPickupLocation(int pickupLocation) {
            this.pickupLocation = pickupLocation;
            return this;
        }

        public RentalBuilder setDropoffLocation(int dropoffLocation) {
            this.dropoffLocation = dropoffLocation;
            return this;
        }

        public RentalBuilder setTotalCost(double totalCost) {
            this.totalCost = totalCost;
            return this;
        }
        public RentalBuilder setDistanceInKm(double distanceInKm) {
            this.distanceInKm = distanceInKm;
            return this;
        }

        public Rental build() {
            return new Rental(this);
        }
    }
}
