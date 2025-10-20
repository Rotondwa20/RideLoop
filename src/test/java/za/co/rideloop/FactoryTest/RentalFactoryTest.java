//package za.co.rideloop.FactoryTest;
///**
// * Admin.java
// * Admin Model Class
// *
// * @Author: Mziwamangwevu Ntutu
// * @Student Number: 217054420
// * Group 3 I
// **/
//import org.junit.jupiter.api.Test;
//import za.co.rideloop.Domain.Car;
//import za.co.rideloop.Domain.CustomerProfile;
//import za.co.rideloop.Domain.Location;
//import za.co.rideloop.Domain.Rental;
//import za.co.rideloop.Factory.RentalFactory;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class RentalFactoryTest {
//
//
//    @Test
//    void testCreateRentalValid() {
//        LocalDate date = LocalDate.now();
//        Rental rental = RentalFactory.createRental(1, 1, 500.0,7.0
//        );
//
//        assertNotNull(rental);
//        assertEquals(1, rental.getCarID());
//        assertEquals(2, rental.getCustomerID());
//        assertEquals(date, rental.getDate());
//        assertEquals("City A", rental.getPickupLocation());
//        assertEquals("City B", rental.getDropoffLocation());
//        assertEquals(500.0, rental.getTotalCost());
//    }
//
//    @Test
//    void testCreateRentalInvalidCarID() {
//        LocalDate date = LocalDate.now();
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            RentalFactory.createRental(0, 2, date, 1, 1, 500.0);
//        });
//
//        assertEquals("Invalid Car ID", exception.getMessage());
//    }
//
//    @Test
//    void testCreateRentalInvalidTotalCost() {
//        LocalDate date = LocalDate.now();
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            RentalFactory.createRental(1, 2, date, 1, 1, -100.0);
//        });
//
//        assertEquals("Total cost must be positive", exception.getMessage());
//    }
//}