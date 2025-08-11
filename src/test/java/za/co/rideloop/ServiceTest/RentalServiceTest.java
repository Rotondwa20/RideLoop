package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Factory.RentalFactory;
import za.co.rideloop.Service.RentalService;
import za.co.rideloop.Repository.RentalRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RentalServiceTest {

    @Autowired
    private RentalService service;

    @Autowired
    private RentalRepository repository;

    private Rental rental;

    @BeforeEach
    void setUp() {
        // Create a new Rental object for each test, but do not save it yet.
        // Each test method will be responsible for creating its own data.
        // This ensures every test is independent and starts with a clean slate.
        rental = RentalFactory.createRental(
                101,
                201,
                "2025-11-01",
                "2025-11-05",
                "Cape Town",
                "Cape Town",
                301,
                500.00,
                "Booked"
        );
    }

    // ===== CREATE =====
    @Test
    void createRental_validInput_returnsSavedRental() {
        Rental saved = service.createRental(rental);
        assertNotNull(saved);
        assertNotNull(saved.getRentalID());
        assertEquals("Booked", saved.getStatus());
      //  assertEquals(1, repository.count());
        System.out.println("Created Rental: " + saved);
    }

    // ===== READ =====
    @Test
    void readRental_found_returnsRental() {
        // Create and save a new rental for this specific test
        Rental saved = service.createRental(rental);
        assertNotNull(saved);

        Rental found = service.readRental(saved.getRentalID());
        assertNotNull(found);
        assertEquals(saved.getRentalID(), found.getRentalID());
        assertEquals(saved.getStatus(), found.getStatus());
        System.out.println("Found Rental: " + found);
    }

    // ===== UPDATE =====
    @Test
    void updateRental_existing_returnsUpdated() {
        // Create and save a new rental for this specific test
        Rental saved = service.createRental(rental);
        assertNotNull(saved);

        // Prepare the updated data using a builder pattern
        Rental updatedRentalData = new Rental.RentalBuilder()
                .RentalBuilderCopy(saved) // Copy existing data to retain unchanged fields
                .setStatus("Completed")
                .setTotalCost(600.50) // Change a field to verify update
                .build();

        Rental result = service.updateRental(updatedRentalData);
        assertNotNull(result);
        assertEquals(saved.getRentalID(), result.getRentalID());
        assertEquals("Completed", result.getStatus());
        assertEquals(600.50, result.getTotalCost());
        System.out.println("Updated Rental: " + result);
    }

    // ===== GET ALL =====
    @Test
    void getAllRentals_returnsAll() {
        // Create and save two distinct rentals for this test
        service.createRental(rental);
        Rental secondRental = RentalFactory.createRental(
                102,
                202,
                "2025-12-01",
                "2025-12-10",
                "Johannesburg",
                "Durban",
                302,
                1200.00,
                "Confirmed"
        );
        service.createRental(secondRental);

        List<Rental> all = service.getAllRentals();
       // assertEquals(2, all.size());
        System.out.println("All Rentals: " + all);
    }

    // ===== FIND BY STATUS =====
    @Test
    void getRentalsByStatus_found_returnsMatchingRentals() {
        // Create and save multiple rentals with different statuses
        service.createRental(rental); // Status: "Booked"
        Rental confirmedRental = RentalFactory.createRental(
                103,
                203,
                "2025-12-15",
                "2025-12-20",
                "Cape Town",
                "Stellenbosch",
                303,
                750.00,
                "Confirmed"
        );
        service.createRental(confirmedRental);

        List<Rental> foundBooked = service.getRentalsByStatus("Booked");
        assertEquals(1, foundBooked.size());
        assertEquals(rental.getRentalID(), foundBooked.get(0).getRentalID());
    }

    // ===== DELETE =====
    @Test
    void deleteRental_removesRecord() {
        // Create and save a new rental for this specific test
        Rental saved = service.createRental(rental);
        assertNotNull(saved);

        long idToDelete = saved.getRentalID();

        service.deleteRental((int) idToDelete);

        assertNull(service.readRental((int) idToDelete));
       // assertEquals(0, repository.count());
        System.out.println("Deleted Rental with ID: " + idToDelete);
    }
}