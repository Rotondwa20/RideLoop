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

    @Test
    void createLocation_shouldCreateValidLocation() {
        Location location = LocationFactory.createLocation(
                "Main Road",
                "Claremont",
                "Western Cape",
                "7708"
        );

        assertNotNull(location);
        assertEquals("Main Road", location.getStreetName());
        assertEquals("Claremont", location.getSuburb());
        assertEquals("Western Cape", location.getProvince());
        assertEquals("7708", location.getZipCode());
    }
}

