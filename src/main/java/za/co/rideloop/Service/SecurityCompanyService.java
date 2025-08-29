package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.SecurityCompany;
import za.co.rideloop.Repository.SecurityCompanyRepository;

import java.util.List;
import java.util.Optional;

/**
 * SecurityCompanyServices.java
 * SecurityCompanyServices model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@Service
public class SecurityCompanyService {
    private final SecurityCompanyRepository repository;

    @Autowired
    public SecurityCompanyService(SecurityCompanyRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new SecurityCompany in the database.
     * @param securityCompany The SecurityCompany object to save.
     * @return The saved SecurityCompany with the generated ID.
     */
    public SecurityCompany createSecurityCompany(SecurityCompany securityCompany) {
        return repository.save(securityCompany);
    }

    /**
     * Reads a SecurityCompany from the database by its ID.
     * @param id The ID of the SecurityCompany to find.
     * @return The found SecurityCompany or null if not found.
     */
    public SecurityCompany readSecurityCompany(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Updates an existing SecurityCompany in the database.
     * It checks if the security company exists by ID before saving.
     * @param securityCompany The SecurityCompany object with updated information.
     * @return The updated SecurityCompany or null if the original security company was not found.
     */
    public SecurityCompany updateSecurityCompany(SecurityCompany securityCompany) {
        if (securityCompany == null || securityCompany.getSecurityCompanyID() == 0) {
            return null;
        }
        Optional<SecurityCompany> existingCompanyOpt = repository.findById(securityCompany.getSecurityCompanyID());
        if (existingCompanyOpt.isPresent()) {
            return repository.save(securityCompany);
        }
        return null;
    }

    /**
     * Deletes a SecurityCompany from the database by its ID.
     * @param id The ID of the SecurityCompany to delete.
     */
    public void deleteSecurityCompany(int id) {
        repository.deleteById(id);
    }

    /**
     * Retrieves all SecurityCompanies from the database.
     * @return A list of all SecurityCompanies.
     */
    public List<SecurityCompany> getAllSecurityCompanies() {
        return repository.findAll();
}}
