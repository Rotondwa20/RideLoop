package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Incident;
import za.co.rideloop.Repository.IncidentRepository;
import za.co.rideloop.Service.IncidentService;

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
@RequestMapping("/incident") // The base path for all Incident related endpoints
public class IncidentController {

        private final IncidentService service;

        @Autowired
        public IncidentController(IncidentService service) {
            this.service = service;
        }

        /**
         * Handles HTTP POST requests to create a new Incident.
         * The Incident object is sent in the request body.
         * @param incident The Incident object to be created.
         * @return The created Incident object, including its generated ID.
         */
        @PostMapping("/create")
        public Incident create(@RequestBody Incident incident) {
            return service.createIncident(incident);
        }

        /**
         * Handles HTTP GET requests to read an Incident by its ID.
         * The Incident ID is passed as a path variable.
         * @param id The ID of the Incident to retrieve.
         * @return The found Incident object, or null if not found.
         */
        @GetMapping("/read/{id}")
        public Incident read(@PathVariable Integer id) {
            return service.readIncident(id);
        }

        /**
         * Handles HTTP PUT requests to update an existing Incident.
         * The updated Incident object is sent in the request body.
         * @param incident The Incident object with updated details.
         * @return The updated Incident object, or null if the original Incident was not found.
         */
        @PutMapping("/update")
        public Incident update(@RequestBody Incident incident) {
            return service.updateIncident(incident);
        }

        /**
         * Handles HTTP DELETE requests to remove an Incident by its ID.
         * The Incident ID is passed as a path variable.
         * @param id The ID of the Incident to be deleted.
         */
        @DeleteMapping("/delete/{id}")
        public void delete(@PathVariable Integer id) {
            service.deleteIncident(id);
        }

        /**
         * Handles HTTP GET requests to retrieve all Incidents.
         * @return A list of all Incident objects in the database.
         */
        @GetMapping("/getAll")
        public List<Incident> getAll() {
            return service.getAllIncidents();
        }
}
