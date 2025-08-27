package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Location;
import za.co.rideloop.Service.LocationService;

import java.util.List;

/* LocationController.java
   REST controller for managing Location entities
   Author: Natasha Njili(221785345)
   Date: 19 Aug 2025
*/

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:3000")
public class LocationController {
    @Autowired
    private LocationService service;

    @PostMapping("/create")
    public Location createLocation(@RequestBody Location location) {
        return service.createLocation(location);
    }

    @GetMapping("/{id}")
    public Location readLocation(@PathVariable Integer id) {
        return service.readLocation(id);
    }

    @PutMapping("/update")
    public Location updateLocation(@RequestBody Location location) {
        return service.updateLocation(location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/all")
    public List<Location> getAllLocations() {
        return service.getAllLocations();
    }

    @GetMapping("/search")
    public Location getLocationByCoordinates(
            @RequestParam double longitude,
            @RequestParam double latitude
    ) {
        return service.getLocationByCoordinates(longitude, latitude);
    }
}

