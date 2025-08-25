package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.SecurityCompany;
import za.co.rideloop.Service.ISecurityCompanyService;

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
@RequestMapping("/api/security-companies")
public class SecurityCompanyController {
    private final ISecurityCompanyService securityCompanyService;

    @Autowired
    public SecurityCompanyController(ISecurityCompanyService securityCompanyService) {
        this.securityCompanyService = securityCompanyService;
    }

    @PostMapping
    public ResponseEntity<SecurityCompany> createSecurityCompany(@RequestBody SecurityCompany securityCompany) {
        SecurityCompany created = securityCompanyService.create(securityCompany);
        return ResponseEntity.created(URI.create("/api/security-companies/" + created.getSecurityCompanyID()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<SecurityCompany>> getAllSecurityCompanies() {
        return ResponseEntity.ok(securityCompanyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecurityCompany> getSecurityCompanyById(@PathVariable Integer id) {
        SecurityCompany existing = securityCompanyService.read(id);
        if (existing == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(existing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecurityCompany> updateSecurityCompany(@PathVariable Integer id,
                                                                 @RequestBody SecurityCompany securityCompany) {
        SecurityCompany existing = securityCompanyService.read(id);
        if (existing == null) return ResponseEntity.notFound().build();

        SecurityCompany toUpdate = new SecurityCompany.Builder()
                .securityCompanyID(id)
                .name(securityCompany.getName() != null ? securityCompany.getName() : existing.getName())
                .contactPerson(securityCompany.getContactPerson() != null ? securityCompany.getContactPerson() : existing.getContactPerson())
                .phone(securityCompany.getPhone() != null ? securityCompany.getPhone() : existing.getPhone())
                .email(securityCompany.getEmail() != null ? securityCompany.getEmail() : existing.getEmail())
                .serviceType(securityCompany.getServiceType() != null ? securityCompany.getServiceType() : existing.getServiceType())
                .contractStartDate(securityCompany.getContractStartDate() != null ? securityCompany.getContractStartDate() : existing.getContractStartDate())
                .contractEndDate(securityCompany.getContractEndDate() != null ? securityCompany.getContractEndDate() : existing.getContractEndDate())
                .monthlyFee(securityCompany.getMonthlyFee() != 0 ? securityCompany.getMonthlyFee() : existing.getMonthlyFee())
                .emergencyHotline(securityCompany.getEmergencyHotline() != null ? securityCompany.getEmergencyHotline() : existing.getEmergencyHotline())
                .coverageArea(securityCompany.getCoverageArea() != null ? securityCompany.getCoverageArea() : existing.getCoverageArea())
                .build();

        SecurityCompany updated = securityCompanyService.update(toUpdate);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecurityCompany(@PathVariable Integer id) {
        SecurityCompany existing = securityCompanyService.read(id);
        if (existing == null) return ResponseEntity.notFound().build();
        securityCompanyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
