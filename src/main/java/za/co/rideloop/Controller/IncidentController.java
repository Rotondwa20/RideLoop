package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Service.CustomerProfileService;
import za.co.rideloop.Service.IncidentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/incidents")
@CrossOrigin(origins = "http://localhost:3000")
public class IncidentController {

    private final IncidentService incidentService;

    @Autowired
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    // Create a new incident
    @PostMapping("/create")
    public ResponseEntity<?> createIncident(
            @RequestParam String type,
            @RequestParam String description,
            @RequestParam int profileId
    ) {
        Optional<CustomerProfile> optionalProfile = incidentService.getProfileById(profileId);
        if (optionalProfile.isEmpty()) {
            return ResponseEntity.badRequest().body("Profile not found");
        }

        CustomerProfile profile = optionalProfile.get();
        try {
            Incident incident = incidentService.createIncident(type, description, profile);
            return ResponseEntity.ok(incident);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Log the full stack trace
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }

    }

    // Get all incidents
    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }

    // Get incidents by profile ID
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<?> getIncidentsByProfile(@PathVariable int profileId) {
        Optional<CustomerProfile> optionalProfile = incidentService.getProfileById(profileId);
        if (optionalProfile.isEmpty()) {
            return ResponseEntity.badRequest().body("Profile not found");
        }

        CustomerProfile profile = optionalProfile.get();
        List<Incident> incidents = incidentService.getIncidentsByProfile(profile);
        return ResponseEntity.ok(incidents);
    }

    // Delete incident by ID
    @DeleteMapping("/{incidentId}")
    public ResponseEntity<Void> deleteIncident(@PathVariable int incidentId) {
        incidentService.deleteIncidentById(incidentId);
        return ResponseEntity.noContent().build();
    }
}
