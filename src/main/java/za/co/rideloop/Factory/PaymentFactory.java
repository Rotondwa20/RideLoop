package za.co.rideloop.Factory;

import za.co.rideloop.Domain.Payment;

public class PaymentFactory {
    // Factory method to create a full payment with all details
    public static Payment createPayment(int paymentId, int rentalID, Double paymentAmount,
                                        String paymentMethod, String paymentDate, String paymentStatus) {
        return new Payment.PaymentBuilder()
                .setPaymentId(paymentId)
                .setRentalID(rentalID)
                .setPaymentAmount(paymentAmount)
                .setPaymentMethod(paymentMethod)
                .setPaymentDate(paymentDate)
                .setPaymentStatus(paymentStatus)
                .build();
    }
}
