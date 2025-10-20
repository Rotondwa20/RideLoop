package za.co.rideloop.Factory;
/**
 * RentalFactory.java
 * RentalFactory Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
import za.co.rideloop.Domain.Car;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.Location;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Util.Helper;

import java.time.LocalDate;


public class RentalFactory {
    public static Rental createRental(int carID, int customerID, LocalDate date,
                                     int pickupLocation, int dropoffLocation, double totalCost, double distanceInKm) {
        // ===== VALIDATION =====
        if (!Helper.isValidInterger(carID)) {
            throw new IllegalArgumentException("Invalid Car ID");
        }
        if (!Helper.isValidInterger(customerID)) {
            throw new IllegalArgumentException("Invalid Customer ID");
        }
        if (!Helper.isNotFutureDate(date)) {
            throw new IllegalArgumentException("Rental date cannot be in the future or null");
        }
        if (Helper.isValidInterger(pickupLocation)) {
            throw new IllegalArgumentException("Pickup location cannot be null or empty");
        }
        if (Helper.isValidInterger(dropoffLocation)) {
            throw new IllegalArgumentException("Dropoff location cannot be null or empty");
        }
        if (!Helper.isValidAmount(totalCost)) {
            throw new IllegalArgumentException("Total cost must be positive");
        }
        if (!Helper.isValidAmount(distanceInKm)) {
            throw new IllegalArgumentException("Distance in KM must be positive");
        }

        // ===== BUILD RENTAL =====
        return new Rental.RentalBuilder()
                .setCarID(carID)
                .setCustomerID(customerID)
                .setDate(date)
                .setPickupLocation(pickupLocation)
                .setDropoffLocation(dropoffLocation)
                .setTotalCost(totalCost)
                .setDistanceInKm(distanceInKm)
                .build();
    }
}