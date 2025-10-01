package za.co.rideloop.FactoryTest;
/**
 * Admin.java
 * Admin Model Class
 *
 * @Author: Ndyebo Qole
 * @Student Number: 210018615
 * Group 3 I
 **/
import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Factory.PaymentFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @Test
    void createPayment() {

        Rental rental = new Rental.RentalBuilder()
                .setRentalID(456)
                .build();

        Double paymentAmount = 500.00;
        String paymentMethod = "Credit Card";
        String paymentStatus = "Completed";

        Payment payment = PaymentFactory.createPayment(
                rental,
                paymentAmount,
                paymentMethod,
                paymentStatus
        );

        assertNotNull(payment);
        assertEquals(rental, payment.getRental());
        assertEquals(paymentAmount, payment.getPaymentAmount());
        assertEquals(paymentMethod, payment.getPaymentMethod());
        assertEquals(LocalDate.now(), payment.getPaymentDate());
        assertEquals(paymentStatus, payment.getPaymentStatus());

        System.out.println(payment);
    }
}