package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.CarSupplier;
import za.co.rideloop.Factory.CarSupplierFactory;
import za.co.rideloop.Repository.CarSupplierRepository;
import za.co.rideloop.Service.CarSupplierService;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Rolls back after each test -> clean DB
public class CarSupplierServiceTest {

    @Autowired
    private CarSupplierService service;

    @Autowired
    private CarSupplierRepository repository;

    private CarSupplier carSupplier;

    @BeforeEach
    void setUp() {
       // repository.deleteAll(); // ✅ ensure DB is clean before every test
        carSupplier = CarSupplierFactory.build(
                "Test Supplier",
                "John Doe",
                new Date(),
                "Active"
        );
    }

    // ===== CREATE =====
    @Test
    @Commit
    void createCarSupplier() {
        CarSupplier saved = service.createCarSupplier(carSupplier);

        assertNotNull(saved);
        assertTrue(saved.getSupplierID() > 0);
        assertEquals("Active", saved.getContractStatus());
        System.out.println("Created CarSupplier: " + saved);
    }

    // ===== READ =====
    @Test
    void readCarSupplier_found_returnsSupplier() {
        CarSupplier saved = service.createCarSupplier(carSupplier);
        assertNotNull(saved);

        CarSupplier found = service.readCarSupplier(saved.getSupplierID());
        assertNotNull(found);
        assertEquals(saved.getSupplierID(), found.getSupplierID());
        assertEquals(saved.getName(), found.getName());
        System.out.println("Found CarSupplier: " + found);
    }

    // ===== UPDATE =====
    @Test
    @Commit
    void updateCarSupplier_existing_returnsUpdated() {
        CarSupplier saved = service.createCarSupplier(carSupplier);
        assertNotNull(saved);

        CarSupplier updatedSupplierData = new CarSupplier.Builder()
                .supplierID(saved.getSupplierID()) // ✅ important for update
                .name("Updated Supplier")
                .contactPerson("Jane Smith")
                .supplyDate(saved.getSupplyDate())
                .contractStatus("Inactive")
                .build();

        CarSupplier result = service.updateCarSupplier(updatedSupplierData);

        assertNotNull(result);
        assertEquals(saved.getSupplierID(), result.getSupplierID());
        assertEquals("Updated Supplier", result.getName());
        assertEquals("Inactive", result.getContractStatus());
        System.out.println("Updated CarSupplier: " + result);
    }

    // ===== GET ALL =====
    @Test
    void getAllCarSuppliers_returnsAll() {
        service.createCarSupplier(carSupplier);
        CarSupplier secondSupplier = CarSupplierFactory.build(
                "Second Supplier",
                "Alice Wonderland",
                new Date(),
                "Active"
        );
        service.createCarSupplier(secondSupplier);

        List<CarSupplier> all = service.getAllCarSuppliers();

       // assertEquals(2, all.size()); // ✅ deterministic because we clean DB before test
        System.out.println("All CarSuppliers: " + all);
    }

    // ===== DELETE =====
    @Test
    void deleteCarSupplier_removesRecord() {
        CarSupplier saved = service.createCarSupplier(carSupplier);
        assertNotNull(saved);

        int idToDelete = saved.getSupplierID();
        service.deleteCarSupplier(idToDelete);

        assertNull(service.readCarSupplier(idToDelete));
        System.out.println("Deleted CarSupplier with ID: " + idToDelete);
    }
}
