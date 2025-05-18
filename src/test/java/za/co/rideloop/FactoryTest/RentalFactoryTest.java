package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Factory.RentalFactory;

import static org.junit.jupiter.api.Assertions.*;

class RentalFactoryTest {

    @Test
    void createRental() {

        int rentalID = 1;
        int carID = 101;
        int customerID = 201;
        String startDate = "2023-10-01";
        String endDate = "2023-10-05";
        String pickupLocation = "Johannesburg Airport";
        String dropoffLocation = "Cape Town Airport";
        int insuranceID = 301;
        double totalCost = 5000.50;
        String status = "Confirmed";

        Rental rental = RentalFactory.createRental(
                rentalID,
                carID,
                customerID,
                startDate,
                endDate,
                pickupLocation,
                dropoffLocation,
                insuranceID,
                totalCost,
                status
        );
        System.out.println(rental);
        assertNotNull(rental);
        assertEquals(rentalID, rental.getRentalID());
        assertEquals(carID, rental.getCarID());
        assertEquals(customerID, rental.getCustomerID());
        assertEquals(startDate, rental.getStartDate());
        assertEquals(endDate, rental.getEndDate());
        assertEquals(pickupLocation, rental.getPickupLocation());
        assertEquals(dropoffLocation, rental.getDropoffLocation());
        assertEquals(insuranceID, rental.getInsuranceID());
        assertEquals(totalCost, rental.getTotalCost(), 0.001); // Using delta for double comparison
        assertEquals(status, rental.getStatus());

        System.out.println(rental);

    }
}