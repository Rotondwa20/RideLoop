package za.co.rideloop.FactoryTest;

import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Factory.PaymentFactory;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @Test
    void createPayment() {
        int paymentId = 123;
        int rentalID = 456;
        Double paymentAmount = 500.00;
        String paymentMethod = "Credit Card";
        String paymentDate = "2023-10-05";
        String paymentStatus = "Completed";

        Payment payment= PaymentFactory.createPayment(
                paymentId,
                rentalID,
                paymentAmount,
                paymentMethod,
                paymentDate,
                paymentStatus
        );
        assertNotNull(payment);
        assertEquals(paymentId, payment.getPaymentId());
        assertEquals(paymentId, payment.getPaymentId());
        assertEquals(rentalID, payment.getRentalID());
        assertEquals(paymentMethod, payment.getPaymentMethod());
        assertEquals(paymentDate, payment.getPaymentDate());
        assertEquals(paymentStatus, payment.getPaymentStatus());
        System.out.println(payment);
    }
}