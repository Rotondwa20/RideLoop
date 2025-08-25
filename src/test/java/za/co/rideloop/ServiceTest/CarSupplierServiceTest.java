package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import za.co.rideloop.Domain.CarSupplier;
import za.co.rideloop.Repository.CarSupplierRepository;
import za.co.rideloop.Service.ICarSupplierService;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CarSupplierServiceTest.java
 * CarSupplierServiceTest model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@SpringBootTest
@ActiveProfiles("test")
public class CarSupplierServiceTest {

    @Autowired
    private ICarSupplierService service;

    @Autowired
    private CarSupplierRepository repository;

    private CarSupplier supplier;

    @BeforeEach
    void setUp() {
        // Clear table
        repository.deleteAll();

        // Create dummy supplier
        supplier = new CarSupplier.Builder()
                .supplierID(0) // ID usually auto-generated
                .name("Toyota South Africa")
                .contactPerson("Sipho Mokoena")
                .supplyDate(new Date())
                .contractStatus("Active")
                .build();

        supplier = service.create(supplier);
    }

    @Test
    void testCreate() {
        CarSupplier newSupplier = new CarSupplier.Builder()
                .name("Nissan SA")
                .contactPerson("Jane Smith")
                .supplyDate(new Date())
                .contractStatus("Pending")
                .build();

        CarSupplier created = service.create(newSupplier);

        assertNotNull(created);
        assertEquals("Nissan SA", created.getName());
        assertEquals("Pending", created.getContractStatus());
    }

    @Test
    void testRead() {
        CarSupplier found = service.read(supplier.getSupplierID());
        assertNotNull(found);
        assertEquals("Toyota South Africa", found.getName());
    }

    @Test
    void testUpdate() {
        supplier = new CarSupplier.Builder()
                .supplierID(supplier.getSupplierID())
                .name("Toyota SA Updated")
                .contactPerson(supplier.getContactPerson())
                .supplyDate(supplier.getSupplyDate())
                .contractStatus("Suspended")
                .build();

        CarSupplier updated = service.update(supplier);

        assertEquals("Toyota SA Updated", updated.getName());
        assertEquals("Suspended", updated.getContractStatus());
    }

    @Test
    void testDelete() {
        int id = supplier.getSupplierID();
        service.delete(id);

        CarSupplier deleted = service.read(id);
        assertNull(deleted);
    }

    @Test
    void testGetAll() {
        List<CarSupplier> allSuppliers = service.getAll();
        assertFalse(allSuppliers.isEmpty());
        assertEquals(1, allSuppliers.size());
    }
}
