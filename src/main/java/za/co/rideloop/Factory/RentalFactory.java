package za.co.rideloop.Factory;
/**
 * RentalFactory.java
 * RentalFactory Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Util.Helper;

import java.time.LocalDate;

public class RentalFactory {
    // Factory method to create a rental with all details
    public static Rental createRental(int carID, int customerID, LocalDate startDate, LocalDate endDate,
                                      String pickupLocation, String dropoffLocation, int insuranceID,
                                      double totalCost, String status) {

        // Validate input parameters using the Helper class
        if (
                !Helper.isValidInterger(carID) ||
                !Helper.isValidInterger(customerID) ||
//                Helper.isNullOrEmpty(startDate) ||
//                Helper.isNullOrEmpty(endDate) ||
                Helper.isNullOrEmpty(pickupLocation) ||
                Helper.isNullOrEmpty(dropoffLocation) ||
                !Helper.isValidInterger(insuranceID) ||
                !Helper.isValidAmount(totalCost) ||
                Helper.isNullOrEmpty(status)) {

            // If any validation fails, throw an exception
            throw new IllegalArgumentException("Invalid input provided for creating a Rental object.");
        }
        return new Rental.RentalBuilder()

                .setCarID(carID)
                .setCustomerID(customerID)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setPickupLocation(pickupLocation)
                .setDropoffLocation(dropoffLocation)
                .setInsuranceID(insuranceID)
                .setTotalCost(totalCost)
                .setStatus(status)
                .build();
    }
}
