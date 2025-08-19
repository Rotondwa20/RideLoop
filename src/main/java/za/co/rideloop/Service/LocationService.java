package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Location;
import za.co.rideloop.Repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    // 🔹 Create a new Location
    public Location createLocation(Location location) {
        return this.repository.save(location);
    }

    // 🔹 Read a Location by ID
    public Location readLocation(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    // 🔹 Update an existing Location
    public Location updateLocation(Location location) {
        return this.repository.save(location);
    }

    // 🔹 Delete a Location by ID
    public void delete(Integer id) {
        this.repository.deleteById(id);
    }

    // 🔹 Get all Locations
    public List<Location> getAllLocations() {
        return this.repository.findAll();
    }

    // 🔹 Optional: Find by longitude and latitude (since streetName does not exist)
    public Location getLocationByCoordinates(double longitude, double latitude) {
        return this.repository.findByLongitudeAndLatitude(longitude, latitude).orElse(null);
    }
}
