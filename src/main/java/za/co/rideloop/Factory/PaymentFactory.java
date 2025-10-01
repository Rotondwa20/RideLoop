package za.co.rideloop.Factory;
/**


 *
 * @Author: Ndyebo Qole
 * @Student Number: 210018615
 * Group 3 I
 **/
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Util.Helper;

import java.time.LocalDate;

public class PaymentFactory {

    public static Payment createPayment(
            Rental rental,
            Double paymentAmount,
            String paymentMethod,
            String paymentStatus
    ) {
        if (rental == null) {
            throw new IllegalArgumentException("Rental cannot be null");
        }
        if (paymentAmount == null || paymentAmount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }
        if (paymentStatus == null || paymentStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment status cannot be null or empty");
        }

        return new Payment.PaymentBuilder()
                .setRental(rental)
                .setPaymentAmount(paymentAmount)
                .setPaymentMethod(paymentMethod.trim())
                .setPaymentDate(LocalDate.now()) // default to current date
                .setPaymentStatus(paymentStatus.trim())
                .build();

    }
    }
