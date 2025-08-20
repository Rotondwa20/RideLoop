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

    // 🔹 Create a new Location
    @PostMapping("/create")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location created = service.createLocation(location);
        return ResponseEntity.ok(created);
    }

    // 🔹 Read a Location by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Location> readLocation(@PathVariable Integer id) {
        Location loc = service.readLocation(id);
        if (loc != null) {
            return ResponseEntity.ok(loc);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Update a Location
    @PutMapping("/update")
    public ResponseEntity<Location> updateLocation(@RequestBody Location location) {
        Location updated = service.updateLocation(location);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Delete a Location by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Get all Locations
    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locList = service.getAllLocations();
        return ResponseEntity.ok(locList);
    }

    // 🔹 Get Location by coordinates
    @GetMapping("/search")
    public ResponseEntity<Location> getLocationByCoordinates(
            @RequestParam double longitude,
            @RequestParam double latitude
    ) {
        Location loc = service.getLocationByCoordinates(longitude, latitude);
        if (loc != null) {
            return ResponseEntity.ok(loc);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

