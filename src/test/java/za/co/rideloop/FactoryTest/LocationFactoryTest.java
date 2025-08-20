package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Location;
import za.co.rideloop.Factory.LocationFactory;

import static org.junit.jupiter.api.Assertions.*;
/* LocationFactoryTest.java

     Location Factory test class

     Author: Natasha Njili(221785345)

     Date: 18 May 2025 */
class LocationFactoryTest {

    // 🔹 Test 1: Standard creation
    @Test
    void createLocation_shouldCreateValidLocation() {
        double longitude = 18.4233;
        double latitude = -33.9189;

        Location location = LocationFactory.createLocation(longitude, latitude);

        assertNotNull(location);
        assertEquals(longitude, location.getLongitude());
        assertEquals(latitude, location.getLatitude());
    }

    // 🔹 Test 2: Negative coordinates
    @Test
    void createLocation_shouldHandleNegativeCoordinates() {
        double longitude = -45.1234;
        double latitude = -12.9876;

        Location location = LocationFactory.createLocation(longitude, latitude);

        assertNotNull(location);
        assertEquals(longitude, location.getLongitude());
        assertEquals(latitude, location.getLatitude());
    }

    // 🔹 Test 3: Zero coordinates
    @Test
    void createLocation_shouldHandleZeroCoordinates() {
        double longitude = 0.0;
        double latitude = 0.0;

        Location location = LocationFactory.createLocation(longitude, latitude);

        assertNotNull(location);
        assertEquals(longitude, location.getLongitude());
        assertEquals(latitude, location.getLatitude());
    }

    // 🔹 Test 4: Builder direct creation
    @Test
    void builder_shouldCreateLocationDirectly() {
        Location location = new Location.Builder()
                .setLongitude(15.5)
                .setLatitude(-25.5)
                .build();

        assertNotNull(location);
        assertEquals(15.5, location.getLongitude());
        assertEquals(-25.5, location.getLatitude());
    }

    // 🔹 Test 5: Copy builder from existing location
    @Test
    void builder_copy_shouldCreateIdenticalLocation() {
        Location original = LocationFactory.createLocation(10.0, 20.0);

        Location copy = new Location.Builder()
                .copy(original)
                .build();

        assertNotNull(copy);
        assertEquals(original.getLongitude(), copy.getLongitude());
        assertEquals(original.getLatitude(), copy.getLatitude());
        assertEquals(original.getLocationID(), copy.getLocationID());
    }
}


