package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import za.co.rideloop.Domain.SecurityCompany;
import za.co.rideloop.Repository.SecurityCompanyRepository;
import za.co.rideloop.Service.ISecurityCompanyService;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SecurityCompanyTest.java
 * SecurityCompanyTest model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@SpringBootTest
@ActiveProfiles("test")
public class SecurityCompanyTest {
    @Autowired
    private ISecurityCompanyService service;

    @Autowired
    private SecurityCompanyRepository repository;

    private SecurityCompany company;

    @BeforeEach
    void setUp() {
        // Clear the table before each test
        repository.deleteAll();

        // Create a dummy company
        company = new SecurityCompany.Builder()
                .securityCompanyID(0) // ID is usually auto-generated
                .name("SafeGuard Security")
                .contactPerson("John Doe")
                .phone("0211234567")
                .email("contact@safeguard.co.za")
                .serviceType("Armed Response")
                .contractStartDate(new Date())
                .contractEndDate(new Date(System.currentTimeMillis() + 86400000L)) // +1 day
                .monthlyFee(5000.00)
                .emergencyHotline("0800-999-111")
                .coverageArea("Cape Town")
                .build();

        company = service.create(company);
    }

    @Test
    void testCreate() {
        SecurityCompany newCompany = new SecurityCompany.Builder()
                .name("Shield Security")
                .contactPerson("Jane Smith")
                .phone("0217654321")
                .email("support@shield.co.za")
                .serviceType("CCTV Monitoring")
                .contractStartDate(new Date())
                .contractEndDate(new Date(System.currentTimeMillis() + 172800000L)) // +2 days
                .monthlyFee(6500.00)
                .emergencyHotline("0800-123-456")
                .coverageArea("Durban")
                .build();

        SecurityCompany created = service.create(newCompany);

        assertNotNull(created);
        assertEquals("Shield Security", created.getName());
    }

    @Test
    void testRead() {
        SecurityCompany found = service.read(company.getSecurityCompanyID());
        assertNotNull(found);
        assertEquals("SafeGuard Security", found.getName());
    }

    @Test
    void testUpdate() {
        company = new SecurityCompany.Builder()
                .securityCompanyID(company.getSecurityCompanyID())
                .name("SafeGuard Security Updated")
                .contactPerson(company.getContactPerson())
                .phone(company.getPhone())
                .email(company.getEmail())
                .serviceType(company.getServiceType())
                .contractStartDate(company.getContractStartDate())
                .contractEndDate(company.getContractEndDate())
                .monthlyFee(5500.00)
                .emergencyHotline(company.getEmergencyHotline())
                .coverageArea(company.getCoverageArea())
                .build();

        SecurityCompany updated = service.update(company);
        assertEquals("SafeGuard Security Updated", updated.getName());
        assertEquals(5500.00, updated.getMonthlyFee());
    }

    @Test
    void testDelete() {
        int id = company.getSecurityCompanyID();
        service.delete(id);

        SecurityCompany deleted = service.read(id);
        assertNull(deleted);
    }

    @Test
    void testGetAll() {
        List<SecurityCompany> allCompanies = service.getAll();
        assertFalse(allCompanies.isEmpty());
        assertEquals(1, allCompanies.size());
    }
}
