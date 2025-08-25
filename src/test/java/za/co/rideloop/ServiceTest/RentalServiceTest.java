package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Factory.RentalFactory;
import za.co.rideloop.Service.RentalService;
import za.co.rideloop.Repository.RentalRepository;

import java.time.LocalDate;
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
                103,
                203,
                LocalDate.of(2025, 11, 1),
                LocalDate.of(2025, 11, 2),
                "Cape Town",
                "Worcester",
                303,
                900.00,
                "Booked"
        );
    }

    // ===== CREATE =====
    @Commit
    @Test
    void createRental() {
        Rental saved = service.create(rental);
        assertNotNull(saved);
        assertEquals("Booked", saved.getStatus());
        //  assertEquals(1, repository.count());
        System.out.println("Created Rental: " + saved);
    }

    // ===== READ =====
    @Test
    void readRental() {
        // Create and save a new rental for this specific test
        Rental saved = service.create(rental);
        assertNotNull(saved);

        Rental found = service.read(saved.getRentalID());
        assertNotNull(found);
        assertEquals(saved.getRentalID(), found.getRentalID());
        assertEquals(saved.getStatus(), found.getStatus());
        System.out.println("Found Rental: " + found);
    }

    // ===== UPDATE =====
    @Test
    @Commit
    void updateRental() {
        // Create and save a new rental for this specific test
        Rental saved = service.create(rental);
        assertNotNull(saved);

        // Prepare the updated data using a builder pattern
        Rental updatedRentalData = new Rental.RentalBuilder()
                .RentalBuilderCopy(saved) // Copy existing data to retain unchanged fields
                .setStatus("Completed")
                .setTotalCost(600.50) // Change a field to verify update
                .build();

        Rental result = service.update(updatedRentalData);
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
        service.create(rental);
        Rental secondRental = RentalFactory.createRental(
                102,
                202,
                LocalDate.of(2025,12,01),
                LocalDate.of(2025,12,10),
                "Johannesburg",
                "Durban",
                302,
                1200.00,
                "Confirmed"
        );
        service.create(secondRental);

        List<Rental> all = service.getAll();
        // assertEquals(2, all.size());
        System.out.println("All Rentals: " + all);
    }

    // ===== FIND BY STATUS =====
    @Test
    void getRentalsByStatus_found_returnsMatchingRentals() {
        // Create and save multiple rentals with different statuses
        service.create(rental); // Status: "Booked"
        Rental confirmedRental = RentalFactory.createRental(
                103,
                203,
                LocalDate.of(2025,12,15),
                LocalDate.of(2025,12,20),
                "Cape Town",
                "Stellenbosch",
                303,
                750.00,
                "Confirmed"
        );
        service.create(confirmedRental);

        List<Rental> foundBooked = service.getRentalsByStatus("Booked");
        // assertEquals(1, foundBooked.size());
        // assertEquals(rental.getRentalID(), foundBooked.get(0).getRentalID());
        System.out.println("Found Booked Rentals: " + foundBooked);

        // List<Rental> foundConfirmed = service.getRentalsByStatus("Confirmed");

    }

    // ===== DELETE =====
    @Test
    void deleteRental_removesRecord() {
        // Create and save a new rental for this specific test
        Rental saved = service.create(rental);
        assertNotNull(saved);

        long idToDelete = saved.getRentalID();

        service.delete((int) idToDelete);

        assertNull(service.read((int) idToDelete));
        // assertEquals(0, repository.count());
        System.out.println("Deleted Rental with ID: " + idToDelete);
    }
}