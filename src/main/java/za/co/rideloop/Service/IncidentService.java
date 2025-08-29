package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Repository.IncidentRepository;

import java.util.List;

/**
 * IncidentService.java
 * IncidentService model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@Service
public class IncidentService {
    @Autowired
    private IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new Incident in the database.
     * @param incident The Incident object to save.
     * @return The saved Incident with the generated ID.
     */
    public Incident createIncident(Incident incident) {
        return repository.save(incident);
    }

    /**
     * Reads an Incident from the database by its ID.
     * @param id The ID of the Incident to find.
     * @return The found Incident or null if not found.
     */
    public Incident readIncident(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Updates an existing Incident in the database.
     * It checks if the incident exists by ID before saving.
     * @param incident The Incident object with updated information.
     * @return The updated Incident or null if the original incident was not found.
     */
    public Incident updateIncident(Incident incident) {
        if (incident == null || incident.getIncidentID() == 0) {
            return null;
        }
        return repository.existsById(incident.getIncidentID())
                ? repository.save(incident)
                : null;
    }

    /**
     * Deletes an Incident from the database by its ID.
     * @param id The ID of the Incident to delete.
     */
    public void deleteIncident(int id) {
        repository.deleteById(id);
    }

    /**
     * Retrieves all Incidents from the database.
     * @return A list of all Incidents.
     */
    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }
}
