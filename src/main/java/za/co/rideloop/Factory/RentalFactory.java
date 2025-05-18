package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Rental;

public class RentalFactory {
    // Factory method to create a rental with all details
    public static Rental createRental(int rentalID, int carID, int customerID, String startDate, String endDate,
                                      String pickupLocation, String dropoffLocation, int insuranceID,
                                      double totalCost, String status) {
        return new Rental.RentalBuilder()
                .setRentalID(rentalID)
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
