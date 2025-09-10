package za.co.rideloop.Service;

import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Factory.IncidentFactory;
import za.co.rideloop.Repository.CustomerProfileRepository;
import za.co.rideloop.Repository.IncidentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final CustomerProfileRepository profileRepository;

    public IncidentService(IncidentRepository incidentRepository, CustomerProfileRepository profileRepository) {
        this.incidentRepository = incidentRepository;
        this.profileRepository = profileRepository;
    }

    // Create a new incident linked to a CustomerProfile
    public Incident createIncident(String type, String description, CustomerProfile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Incident must be linked to a CustomerProfile");
        }

        // Allow all three types
        if (!"Security".equalsIgnoreCase(type)
                && !"Maintenance".equalsIgnoreCase(type)
                && !"Accident".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("Incident type must be 'Security', 'Maintenance' or 'Accident'");
        }

        Incident incident = IncidentFactory.createIncident(type, description, profile);
        return incidentRepository.save(incident);
    }

    // Get all incidents
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Get profile by ID
    public Optional<CustomerProfile> getProfileById(int profileId) {
        return profileRepository.findById(profileId);
    }

    // Get incidents by CustomerProfile
    public List<Incident> getIncidentsByProfile(CustomerProfile profile) {
        return incidentRepository.findByProfile(profile);
    }

    // Delete an incident by its ID
    public void deleteIncidentById(int incidentID) {
        incidentRepository.deleteById(incidentID);
    }
}
