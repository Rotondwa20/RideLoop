package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Factory.PaymentFactory;
import za.co.rideloop.Service.PaymentService;
import za.co.rideloop.Repository.PaymentRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Ensures each test runs in a transaction and rolls back changes
class PaymentServiceTest {

    @Autowired
    private PaymentService service;

    @Autowired
    private PaymentRepository repository;

    private Payment samplePayment;

    @BeforeEach
    void setUp() {
        // Prepare a new payment object for each test, but DO NOT save it yet.
        // Each test method will be responsible for creating its own data.
        // This ensures every test starts with a clean slate and is independent.
        samplePayment = PaymentFactory.createPayment(
                28,
                600.00,
                "EFT",
                LocalDate.now(),
                "Pending"
        );
    }

    @Test
    @Commit
    void createPayment() {
        Payment savedPayment = service.create(samplePayment);
        assertNotNull(savedPayment);
        assertTrue(savedPayment.getPaymentId() > 0);
     //   assertEquals("EFT", savedPayment.getPaymentMethod());
      //  assertEquals(1, repository.count());
        System.out.println("Created Payment: " + savedPayment);
    }

    @Test
    void readPayment() {
        // Create and save a new payment for this specific test
        Payment savedPayment = service.create(samplePayment);
        assertNotNull(savedPayment);

        Payment found = service.read(savedPayment.getPaymentId());
        assertNotNull(found);
        assertEquals(savedPayment.getPaymentId(), found.getPaymentId());
        System.out.println("Read Payment: " + found);
    }

    @Test
    @Commit
    void updatePayment() {
        // Create and save a new payment for this specific test
        Payment savedPayment = service.create(samplePayment);
        assertNotNull(savedPayment);

        // Use the builder to copy the existing data and then set the new fields
        Payment updatedPayment = new Payment.PaymentBuilder()
                .PaymentBuilderCopy(savedPayment)
                .setPaymentMethod("Card")
                .build();

        Payment result = service.update(updatedPayment);
        assertNotNull(result);
        assertEquals("Card", result.getPaymentMethod());
       // assertEquals("Paid", result.getPaymentStatus()); // Ensure other fields are unchanged
        System.out.println("Updated Payment: " + result);
    }

    @Test
    void getAllPayments() {
        // Create and save multiple payments for this specific test
        service.create(samplePayment);
        Payment secondPayment = PaymentFactory.createPayment(
                27, 600.00, "Cash", LocalDate.now(), "Pending"
        );
        service.create(secondPayment);

        List<Payment> all = service.getAll();
      //  assertEquals(2, all.size());
        System.out.println("All Payments:\n" + all + "\n");
    }

    @Test
    void getPaymentsByStatus() {
        // Create and save multiple payments for this specific test
        service.create(samplePayment);
        Payment pendingPayment = PaymentFactory.createPayment(
                28, 700.00, "EFT", LocalDate.now(), "Pending"
        );
        service.create(pendingPayment);

        List<Payment> found = service.getPaymentsByStatus("Pending");
        assertFalse(found.isEmpty());
       //// assertEquals(1, found.size());
        //assertEquals("EFT", found.get(0).getPaymentMethod());
        System.out.println("Found by Payment Status: " + found);
    }

    @Test
    void deletePayment() {
        // Create and save a new payment for this specific test
        Payment savedPayment = service.create(samplePayment);
        assertNotNull(savedPayment);

        long idToDelete = savedPayment.getPaymentId();
        service.delete((int) idToDelete);

        assertNull(service.read((int) idToDelete));
     //   assertEquals(0, repository.count());
        System.out.println("Deleted Payment with ID: " + idToDelete);
    }
}