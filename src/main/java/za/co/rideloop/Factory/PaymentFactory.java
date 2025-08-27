package za.co.rideloop.Factory;
/**
 * PaymentFactory.java
 * PaymentFactory Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Util.Helper;

import java.time.LocalDate;

public class PaymentFactory {
    // Factory method to create a full payment with all details
    public static Payment createPayment(int rentalID, Double paymentAmount,
                                        String paymentMethod, LocalDate paymentDate, String paymentStatus) {


        // Use the Helper class to validate the parameters
        if (

                !Helper.isValidAmount(paymentAmount) ||
                Helper.isNullOrEmpty(paymentMethod) ||
        //  Helper.isNullOrEmpty(paymentDate) ||
                Helper.isNullOrEmpty(paymentStatus)) {

            // If any validation fails, throw an exception
            throw new IllegalArgumentException("Invalid input provided for creating a Payment object.");
        }
            return new Payment.PaymentBuilder()

                    .setRentalID(rentalID)
                    .setPaymentAmount(paymentAmount)
                    .setPaymentMethod(paymentMethod)
                    .setPaymentDate(paymentDate)
                    .setPaymentStatus(paymentStatus)
                    .build();
        }
    }
