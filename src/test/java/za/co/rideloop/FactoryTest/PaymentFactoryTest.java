package za.co.rideloop.FactoryTest;
/**
 * Admin.java
 * Admin Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
import org.junit.jupiter.api.Test;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Factory.PaymentFactory;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @Test
    void createPayment() {

        int rentalID = 456;
        Double paymentAmount = 500.00;
        String paymentMethod = "Credit Card";
        String paymentDate = "2023-10-05";
        String paymentStatus = "Completed";


        Payment payment= PaymentFactory.createPayment(

                rentalID,
                paymentAmount,
                paymentMethod,
                paymentDate,
                paymentStatus
        );
        assertNotNull(payment);

        assertEquals(rentalID, payment.getRentalID());
        assertEquals(paymentMethod, payment.getPaymentMethod());
        assertEquals(paymentDate, payment.getPaymentDate());
        assertEquals(paymentStatus, payment.getPaymentStatus());
        System.out.println(payment);
    }
}