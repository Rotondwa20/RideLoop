package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.SecurityCompany;
import za.co.rideloop.Service.SecurityCompanyService;


import java.net.URI;
import java.util.List;

/**
 * SecurityCompanyServiceController.java
 * SecurityCompanyServiceController model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@RestController
@RequestMapping("/securitycompany")
public class SecurityCompanyController {
    private final SecurityCompanyService service;

    @Autowired
    public SecurityCompanyController(SecurityCompanyService service) {
        this.service = service;
    }

    /**
     * Handles HTTP POST requests to create a new SecurityCompany.
     * The SecurityCompany object is sent in the request body.
     * @param securityCompany The SecurityCompany object to be created.
     * @return The created SecurityCompany object, including its generated ID.
     */
    @PostMapping("/create")
    public SecurityCompany create(@RequestBody SecurityCompany securityCompany) {
        return service.createSecurityCompany(securityCompany);
    }

    /**
     * Handles HTTP GET requests to read a SecurityCompany by its ID.
     * The SecurityCompany ID is passed as a path variable.
     * @param id The ID of the SecurityCompany to retrieve.
     * @return The found SecurityCompany object, or null if not found.
     */
    @GetMapping("/read/{id}")
    public SecurityCompany read(@PathVariable Integer id) {
        return service.readSecurityCompany(id);
    }

    /**
     * Handles HTTP PUT requests to update an existing SecurityCompany.
     * The updated SecurityCompany object is sent in the request body.
     * @param securityCompany The SecurityCompany object with updated details.
     * @return The updated SecurityCompany object, or null if the original SecurityCompany was not found.
     */
    @PutMapping("/update")
    public SecurityCompany update(@RequestBody SecurityCompany securityCompany) {
        return service.updateSecurityCompany(securityCompany);
    }

    /**
     * Handles HTTP DELETE requests to remove a SecurityCompany by its ID.
     * The SecurityCompany ID is passed as a path variable.
     * @param id The ID of the SecurityCompany to be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteSecurityCompany(id);
    }

    /**
     * Handles HTTP GET requests to retrieve all SecurityCompanies.
     * @return A list of all SecurityCompany objects in the database.
     */
    @GetMapping("/getAll")
    public List<SecurityCompany> getAll() {
        return  service.getAllSecurityCompanies();
        //return service.getAllSecurityCompanies();
    }
}
