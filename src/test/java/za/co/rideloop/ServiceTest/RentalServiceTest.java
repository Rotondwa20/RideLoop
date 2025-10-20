package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.Car;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.Location;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Factory.RentalFactory;
import za.co.rideloop.Service.RentalService;
import za.co.rideloop.Repository.RentalRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@SpringBootTest
@Transactional
class RentalServiceTest {
    @Mock
    private RentalRepository repository;

    @InjectMocks
    private RentalService service;

    private Rental rental;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rental = new Rental.RentalBuilder()
                .setCarID(1)
                .setCustomerID(2)
                .setDate(LocalDate.now())
                .setPickupLocation(1)
                .setDropoffLocation(1)
                .setTotalCost(500.0)
                .setDistanceInKm(50.0)
                .build();
    }

    @Test
    void testCreateRental() {
        when(repository.save(rental)).thenReturn(rental);

        Rental created = service.create(rental);
        assertNotNull(created);
        assertEquals(rental.getCarID(), created.getCarID());
    }

    @Test
    void testCreateRentalInvalid() {
        Rental invalidRental = new Rental.RentalBuilder()
                .setCarID(0) // invalid
                .setCustomerID(0)
                .setDate(null)
                .setPickupLocation(1)
                .setDropoffLocation(1)
                .setTotalCost(-100.0)
                .setDistanceInKm(-10.0)
                .build();

        Rental result = service.create(invalidRental);
        assertNull(result); // Service should return null for invalid data
    }

    @Test
    void testReadRental() {
        when(repository.findById(1)).thenReturn(Optional.of(rental));

        Rental found = service.read(1);
        assertNotNull(found);
        assertEquals(rental.getCarID(), found.getCarID());
    }

    @Test
    void testUpdateRental() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(rental));
        when(repository.save(any(Rental.class))).thenReturn(rental);

        Rental updatedRental = new Rental.RentalBuilder()
                .setRentalID(rental.getRentalID())
                .setCarID(10)
                .setCustomerID(20)
                .setDate(LocalDate.now())
                .setPickupLocation(1)
                .setDropoffLocation(1)
                .setTotalCost(1000.0)
                .build();

        Rental result = service.update(updatedRental);
        assertNotNull(result);
        assertEquals(10, result.getCarID());
        assertEquals(1000.0, result.getTotalCost());
    }

    @Test
    void testDeleteRental() {
        doNothing().when(repository).deleteById(1);
        service.delete(1);

    }

    @Test
    void testGetAllRentals() {
        when(repository.findAll()).thenReturn(Arrays.asList(rental));
        List<Rental> rentals = service.getAll();
        assertEquals(1, rentals.size());
    }

    @Test
    void testGetRentalsByCustomer() {
        when(repository.findByCustomerID(2)).thenReturn(Arrays.asList(rental));
        List<Rental> rentals = service.getRentalsByCustomer(2);
        assertEquals(1, rentals.size());
        assertEquals(2, rentals.get(0).getCustomerID());
    }
}