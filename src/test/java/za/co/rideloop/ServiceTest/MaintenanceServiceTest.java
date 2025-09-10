package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.rideloop.Domain.Maintenance;
import za.co.rideloop.Factory.MaintenanceFactory;
import za.co.rideloop.Service.MaintenanceService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class MaintenanceServiceTest {


    @Autowired
    private MaintenanceService service;

    private Maintenance maintenance;

    @Test
    void a_create() {
        maintenance = MaintenanceFactory.createMaintenance(
                45L,
                "ABC Insurance",
                "John Doe",
                "0812345678",
                "Full Coverage",
                1200.0,
                "Covers all damages"
        );
        Maintenance created = service.create(maintenance);
        assertNotNull(created);
        assertEquals("ABC Insurance", created.toString().contains("ABC Insurance") ? "ABC Insurance" : "");
    }

    @Test
    void b_read() {
        List<Maintenance> maintenances = service.getAll();
        assertFalse(maintenances.isEmpty());
        Long id = maintenances.get(0).getId();
        Maintenance  read = service.read(id);
        assertNotNull(read.toString());
    }

    @Test
    void c_update() {
        List<Maintenance> maintenances = service.getAll();
        Maintenance existing = maintenances.get(0);
        Maintenance updated = new Maintenance.Builder()
                .setId(existing.getId())
                .setInsuranceCompanyName("XYZ Insurance")
                .setContactPerson(existing.getContactPerson())
                .setContactNumber(existing.getContactNumber())
                .setCoverageType(existing.getCoverageType())
                .setCostPerMonth(existing.getCostPerMonth())
                .setDescription(existing.getDescription())
                .build();
        Maintenance result = service.update(existing.getId(), updated);
        assertEquals("XYZ Insurance", result.toString().contains("XYZ Insurance") ? "XYZ Insurance" : "");
    }

    @Test
    void d_delete() {
        List<Maintenance> maintenances = service.getAll();
        Long id = maintenances.get(0).getId();
        boolean deleted = service.delete(id);
        assertTrue(deleted);
    }

    @Test
    void e_getAll() {
        List<Maintenance> maintenances = service.getAll();
        assertNotNull(maintenances);
    }



}
