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


    public Location createLocation(Location location) {
        return this.repository.save(location);
    }


    public Location readLocation(Integer id) {
        return this.repository.findById(id).orElse(null);
    }


    public Location updateLocation(Location location) {
        return this.repository.save(location);
    }


    public void delete(Integer id) {
        this.repository.deleteById(id);
    }


    public List<Location> getAllLocations() {
        return this.repository.findAll();
    }


    public Location getLocationByCoordinates(double longitude, double latitude) {
        List<Location> locations = this.repository.findByLongitudeAndLatitude(longitude, latitude);
        return locations.isEmpty() ? null : locations.get(0);
    }
}
