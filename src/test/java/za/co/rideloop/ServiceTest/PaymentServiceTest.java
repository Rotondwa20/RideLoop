package za.co.rideloop.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Factory.PaymentFactory;
import za.co.rideloop.Service.PaymentService;
import za.co.rideloop.Repository.PaymentRepository;

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
                27,
                500.00,
                "EFT",
                "2025-03-03",
                "CashS"
        );
    }

    @Test
    void createPayment() {
        Payment savedPayment = service.createPayment(samplePayment);
        assertNotNull(savedPayment);
        assertTrue(savedPayment.getPaymentId() > 0);
        assertEquals("EFT", savedPayment.getPaymentMethod());
      //  assertEquals(1, repository.count());
        System.out.println("Created Payment: " + savedPayment);
    }

    @Test
    void readPayment() {
        // Create and save a new payment for this specific test
        Payment savedPayment = service.createPayment(samplePayment);
        assertNotNull(savedPayment);

        Payment found = service.readPayment(savedPayment.getPaymentId());
        assertNotNull(found);
        assertEquals(savedPayment.getPaymentId(), found.getPaymentId());
        System.out.println("Read Payment: " + found);
    }

    @Test
    void updatePayment() {
        // Create and save a new payment for this specific test
        Payment savedPayment = service.createPayment(samplePayment);
        assertNotNull(savedPayment);

        // Use the builder to copy the existing data and then set the new fields
        Payment updatedPayment = new Payment.PaymentBuilder()
                .PaymentBuilderCopy(savedPayment)
                .setPaymentMethod("Card")
                .build();

        Payment result = service.updatePayment(updatedPayment);
        assertNotNull(result);
        assertEquals("Card", result.getPaymentMethod());
       // assertEquals("Paid", result.getPaymentStatus()); // Ensure other fields are unchanged
        System.out.println("Updated Payment: " + result);
    }

    @Test
    void getAllPayments() {
        // Create and save multiple payments for this specific test
        service.createPayment(samplePayment);
        Payment secondPayment = PaymentFactory.createPayment(
                27, 600.00, "Cash", "2025-01-02", "Pending"
        );
        service.createPayment(secondPayment);

        List<Payment> all = service.getAllPayments();
      //  assertEquals(2, all.size());
        System.out.println("All Payments:\n" + all + "\n");
    }

    @Test
    void getPaymentsByStatus() {
        // Create and save multiple payments for this specific test
        service.createPayment(samplePayment);
        Payment pendingPayment = PaymentFactory.createPayment(
                28, 700.00, "EFT", "2025-01-03", "Pending"
        );
        service.createPayment(pendingPayment);

        List<Payment> found = service.getPaymentsByStatus("Paid");
        assertFalse(found.isEmpty());
       //// assertEquals(1, found.size());
        //assertEquals("EFT", found.get(0).getPaymentMethod());
        System.out.println("Found by Payment Status: " + found);
    }

    @Test
    void deletePayment() {
        // Create and save a new payment for this specific test
        Payment savedPayment = service.createPayment(samplePayment);
        assertNotNull(savedPayment);

        long idToDelete = savedPayment.getPaymentId();
        service.deletePayment((int) idToDelete);

        assertNull(service.readPayment((int) idToDelete));
     //   assertEquals(0, repository.count());
        System.out.println("Deleted Payment with ID: " + idToDelete);
    }
}