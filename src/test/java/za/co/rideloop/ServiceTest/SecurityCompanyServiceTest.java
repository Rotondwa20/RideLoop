package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.SecurityCompany;
import za.co.rideloop.Factory.SecurityCompanyFactory;
import za.co.rideloop.Repository.SecurityCompanyRepository;
import za.co.rideloop.Service.SecurityCompanyService;


import java.time.LocalDate;
//import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * SecurityCompanyServiceTest.java
 * SecurityCompanyServiceTest model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@SpringBootTest
@Transactional
public class SecurityCompanyServiceTest {
    @Autowired
    private SecurityCompanyService service;

    @Autowired
    private SecurityCompanyRepository repository;

    private SecurityCompany sampleCompany;

    @BeforeEach
    void setUp() {
        // Prepare a new SecurityCompany object for each test, but do not save it yet.
        sampleCompany = SecurityCompanyFactory.build(
                "Test Security",
                "John Smith",
                "0811234567",
                "test@security.com",
                "Armed Response",
                LocalDate.of(2025, 11, 1),
                LocalDate.of(2025, 11, 2),
                5000.00,
                "0800-111-222",
                "Central Business District"
        );
    }

    // ===== CREATE =====
    @Test
    @Commit
    void createSecurityCompany() {
        SecurityCompany savedCompany = service.createSecurityCompany(sampleCompany);
        assertNotNull(savedCompany);
        assertTrue(savedCompany.getSecurityCompanyID() > 0);
        assertEquals("Test Security", savedCompany.getName());
        System.out.println("Created SecurityCompany: " + savedCompany);
    }

    // ===== READ =====
    @Test
    void readSecurityCompany() {
        SecurityCompany savedCompany = service.createSecurityCompany(sampleCompany);
        assertNotNull(savedCompany);

        SecurityCompany found = service.readSecurityCompany(savedCompany.getSecurityCompanyID());
        assertNotNull(found);
        assertEquals(savedCompany.getSecurityCompanyID(), found.getSecurityCompanyID());
        assertEquals(savedCompany.getName(), found.getName());
        System.out.println("Read SecurityCompany: " + found);
    }

    // ===== UPDATE =====
    @Test
    void updateSecurityCompany() {
        SecurityCompany savedCompany = service.createSecurityCompany(sampleCompany);
        assertNotNull(savedCompany);

        // Prepare updated data with the existing ID
        SecurityCompany updatedCompanyData = new SecurityCompany.Builder()
                .securityCompanyID(savedCompany.getSecurityCompanyID())
                .name("Updated Security Co")
                .monthlyFee(6500.00)
                .contactPerson("Jane Doe")
                .phone("0829876543")
                .email("updated@security.com")
                .serviceType("Mobile Patrol")
                .contractStartDate(savedCompany.getContractStartDate())
                .contractEndDate(savedCompany.getContractEndDate())
                .emergencyHotline("0800-333-444")
                .coverageArea("Northern Suburbs")
                .build();

        SecurityCompany result = service.updateSecurityCompany(updatedCompanyData);
        assertNotNull(result);
        assertEquals(savedCompany.getSecurityCompanyID(), result.getSecurityCompanyID());
        assertEquals("Updated Security Co", result.getName());
        assertEquals(6500.00, result.getMonthlyFee());
        System.out.println("Updated SecurityCompany: " + result);
    }

    // ===== DELETE =====
    @Test
    void deleteSecurityCompany() {
        SecurityCompany savedCompany = service.createSecurityCompany(sampleCompany);
        assertNotNull(savedCompany);

        int idToDelete = savedCompany.getSecurityCompanyID();
        service.deleteSecurityCompany(idToDelete);

        assertNull(service.readSecurityCompany(idToDelete));
        System.out.println("Deleted SecurityCompany with ID: " + idToDelete);
    }

    // ===== GET ALL =====
    @Test
    void getAllSecurityCompanies() {
        service.createSecurityCompany(sampleCompany);
        SecurityCompany secondCompany = SecurityCompanyFactory.build(
                "Another Security Co",
                "Alice",
                "0711112222",
                "alice@another.com",
                "Alarm Monitoring",
                LocalDate.of(2025, 12, 11),
                LocalDate.of(2025, 12, 22),
                3000.00,
                "0800-555-666",
                "Southern Suburbs"
        );
        service.createSecurityCompany(secondCompany);

        List<SecurityCompany> all = service.getAllSecurityCompanies();
        //assertEquals(2, all.size());
        System.out.println("All Security Companies:\n" + all);
    }
}
