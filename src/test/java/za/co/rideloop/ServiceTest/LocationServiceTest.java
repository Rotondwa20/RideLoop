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

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class LocationServiceTest {
    @Autowired
    private LocationService service;
    private  Location location ;
    @Test
    void a_createLocation(){
        Location newlocation = LocationFactory.createLocation("XasoCress" ,"Khayelitsha" ,"Western Cape" ,"8001");

        location = service.createLocation(newlocation);
        assertNotNull(location);
        System.out.println(location.toString());

    }

    @Test
    void b_readLocation(){
        Location location1= service.readLocation(352);
        assertNotNull(location1);
        System.out.println(location1.toString());
    }

    @Test
    void c_updateLocation(){
        //let's find a street name XasCress and update it
        Location findXasoCres = service.getLocationByStreetName("XasoCress");
        assertNotNull(findXasoCres);

        // change "XasoCres" to "Xaso Creasant"
        Location updateLocation = new Location.Builder()
                                              .copy(findXasoCres)
                                               .setStreetName("Xaso Creasant")
                                              .build();
        Location updated = service.updateLocation(updateLocation);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_deleteLocation (){
       service.delete(402);

    }

    @Test
    void e_getAllLocations(){
        List<Location> locList=service.getAllLocations();
        assertNotNull(locList);
        System.out.println(locList.toString());
    }

}
