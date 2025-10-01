package za.co.rideloop.ServiceTest;

/**

 *
 * @Author: Ndyebo Qole
 * @Student Number: 210018615
 * Group 3 I
 **/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Factory.PaymentFactory;
import za.co.rideloop.Service.PaymentService;
import za.co.rideloop.Repository.PaymentRepository;
import za.co.rideloop.Service.RentalService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Ensures each test runs in a transaction and rolls back changes
class PaymentServiceTest {

    @Autowired
    private PaymentService service;

    @Autowired
    private RentalService rentalService;

    private Rental rental;

    @BeforeEach
    void setUp() {
        // Prepare a dummy rental object
        rental = new Rental.RentalBuilder()
                .setRentalID(1) // dummy ID
                .build();
    }

    @Test
    void a_createPayment() {
        Payment payment = PaymentFactory.createPayment(rental, 650.00, "EFT", "Pending");

        System.out.println(payment);
        Payment created = service.create(payment);

        assertNotNull(created);
        assertEquals("EFT", created.getPaymentMethod());
        System.out.println("Created Payment: " + created);
    }

    @Test
    void b_readPayment() {
        Payment payment = PaymentFactory.createPayment(rental, 700.00, "Card", "Pending");
        Payment created = service.create(payment);

        Payment read = service.read(created.getPaymentId());
        assertNotNull(read);
        assertEquals(created.getPaymentId(), read.getPaymentId());
        System.out.println("Read Payment: " + read);
    }

    @Test
    void c_updatePayment() {
        Payment payment = PaymentFactory.createPayment(rental, 500.00, "Cash", "Pending");
        Payment created = service.create(payment);

        Payment updatedPayment = new Payment.PaymentBuilder()
                .PaymentBuilderCopy(created)
                .setPaymentMethod("Card")
                .setPaymentStatus("Paid")
                .build();

        Payment updated = service.update(updatedPayment);
        assertNotNull(updated);
        assertEquals("Card", updated.getPaymentMethod());
        assertEquals("Paid", updated.getPaymentStatus());
        System.out.println("Updated Payment: " + updated);
    }

    @Test
    void d_deletePayment() {
        Payment payment = PaymentFactory.createPayment(rental, 450.00, "EFT", "Pending");
        Payment created = service.create(payment);

        boolean deleted = service.delete(created.getPaymentId());
        assertTrue(deleted);

        Payment read = service.read(created.getPaymentId());
        assertNull(read);
        System.out.println("Deleted Payment ID: " + created.getPaymentId());
    }

    @Test
    void e_getAllPayments() {
        // Create multiple payments
        Payment p1 = PaymentFactory.createPayment(rental, 600.00, "EFT", "Pending");
        Payment p2 = PaymentFactory.createPayment(rental, 700.00, "Card", "Paid");
        service.create(p1);
        service.create(p2);

        List<Payment> all = service.getAll();
        assertNotNull(all);
        assertTrue(all.size() >= 2);
        System.out.println("All Payments: " + all);
    }

    @Test
    void f_getPaymentsByStatus() {
        Payment pending = PaymentFactory.createPayment(rental, 500.00, "EFT", "Pending");
        Payment paid = PaymentFactory.createPayment(rental, 600.00, "Card", "Paid");
        service.create(pending);
        service.create(paid);

        List<Payment> pendingPayments = service.getPaymentsByStatus("Pending");
        assertFalse(pendingPayments.isEmpty());
        assertTrue(pendingPayments.stream().allMatch(p -> p.getPaymentStatus().equals("Pending")));
        System.out.println("Payments with Pending status: " + pendingPayments);
    }

    @Test
    void g_getPaymentsByMethod() {
        Payment eft = PaymentFactory.createPayment(rental, 550.00, "EFT", "Pending");
        Payment card = PaymentFactory.createPayment(rental, 650.00, "Card", "Paid");
        service.create(eft);
        service.create(card);

        List<Payment> cardPayments = service.getPaymentsByMethod("Card");
        assertFalse(cardPayments.isEmpty());
        assertTrue(cardPayments.stream().allMatch(p -> p.getPaymentMethod().equals("Card")));
        System.out.println("Payments with Card method: " + cardPayments);
    }
}