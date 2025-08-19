package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.Location;
import za.co.rideloop.Factory.LocationFactory;
import za.co.rideloop.Service.LocationService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class LocationServiceTest {

    @Autowired
    private LocationService service;

    private static Location location;

    // 🔹 Test A: Create a Location
    @Test
    void a_createLocation() {
        Location newLocation = LocationFactory.createLocation(18.4233, -33.9189);

        location = service.createLocation(newLocation);
        assertNotNull(location);
        System.out.println("Created: " + location);
    }

    // 🔹 Test B: Read a Location by ID
    @Test
    void b_readLocation() {
        // Use the location created in previous test
        Location location1 = service.readLocation(location.getLocationID());
        assertNotNull(location1);
        System.out.println("Read: " + location1);
    }

    // 🔹 Test C: Update a Location
    @Test
    void c_updateLocation() {
        // Copy existing location and modify coordinates
        Location updatedLocation = new Location.Builder()
                .copy(location)
                .setLongitude(18.5000)
                .setLatitude(-33.9500)
                .build();

        Location result = service.updateLocation(updatedLocation);
        assertNotNull(result);
        System.out.println("Updated: " + result);
    }

    // 🔹 Test D: Delete a Location
    @Test
    void d_deleteLocation() {
        service.delete(location.getLocationID());
        Location deleted = service.readLocation(location.getLocationID());
        assertNull(deleted);
        System.out.println("Deleted location with ID: " + location.getLocationID());
    }

    // 🔹 Test E: Get all Locations
    @Test
    void e_getAllLocations() {
        List<Location> locList = service.getAllLocations();
        assertNotNull(locList);
        System.out.println("All locations: " + locList);
    }
}