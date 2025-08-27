package za.co.rideloop.FactoryTest;
/**
 * Admin.java
 * Admin Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Factory.RentalFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RentalFactoryTest {

    @Test
    void createRental() {


        int carID = 101;
        int customerID = 201;
        LocalDate startDate = LocalDate.of(2023, 10, 1); // Corrected to use LocalDate.of
        LocalDate endDate = LocalDate.of(2023, 10, 10); // Corrected to use LocalDate.of
        String pickupLocation = "Johannesburg Airport";
        String dropoffLocation = "Cape Town Airport";
        int insuranceID = 301;
        double totalCost = 5000.50;
        String status = "Confirmed";

        Rental rental = RentalFactory.createRental(

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
       // assertEquals(rentalID, rental.getRentalID());
        assertEquals(carID, rental.getCarID());
        assertEquals(customerID, rental.getCustomerID());
        assertEquals(startDate, rental.getStartDate());
        assertEquals(endDate, rental.getEndDate());
        assertEquals(pickupLocation, rental.getPickupLocation());
        assertEquals(dropoffLocation, rental.getDropoffLocation());
        assertEquals(insuranceID, rental.getInsuranceID());
        assertEquals(totalCost, rental.getTotalCost(), 0.001); // Using delta for double comparison
        assertEquals(status, rental.getStatus());

        //System.out.println(rental);

    }
}