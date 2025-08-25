package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Service.IIncidentService;

import java.net.URI;
import java.util.List;

/**
 * IncidentServiceController.java
 * IncidentServiceController model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    private final IIncidentService incidentService;

    @Autowired
    public IncidentController(IIncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        Incident created = incidentService.create(incident);
        return ResponseEntity.created(URI.create("/api/incidents/" + created.getIncidentID()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        return ResponseEntity.ok(incidentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Integer id) {
        Incident existing = incidentService.read(id);
        if (existing == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(existing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Integer id,
                                                   @RequestBody Incident incident) {
        Incident existing = incidentService.read(id);
        if (existing == null) return ResponseEntity.notFound().build();

        Incident toUpdate = new Incident.Builder()
                .incidentID(id)
                .incidentType(incident.getIncidentType() != null ? incident.getIncidentType() : existing.getIncidentType())
                .description(incident.getDescription() != null ? incident.getDescription() : existing.getDescription())
                .build();

        Incident updated = incidentService.update(toUpdate);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Integer id) {
        Incident existing = incidentService.read(id);
        if (existing == null) return ResponseEntity.notFound().build();
        incidentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
