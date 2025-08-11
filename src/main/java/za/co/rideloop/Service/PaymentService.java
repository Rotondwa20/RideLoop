package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Repository.PaymentRepository;
import za.co.rideloop.util.Helper;

import java.util.List;

/**
 * RentalFactory.java
 * RentalFactory Model Class
 *
 * @Author: Mziwamangwevu Ntutu
 * @Student Number: 217054420
 * Group 3 I
 **/
@Service
public class PaymentService {


    @Autowired
    private PaymentRepository repository;

    // ===== Create =====
    public Payment createPayment(Payment payment) {
        if (payment == null ||
               // !Helper.isValidId(payment.getPaymentID()) ||
               // !Helper.isValidId(payment.getRentalID()) ||
                !Helper.isValidAmount(payment.getPaymentAmount()) ||
                Helper.isNullOrEmpty(payment.getPaymentMethod()) ||
                payment.getPaymentDate() == null ||
                !Helper.isNotFutureDate(payment.getPaymentDate()) ||
                Helper.isNullOrEmpty(payment.getPaymentStatus())) {
            return null;
        }
        return this.repository.save(payment);
    }

    // ===== Read =====
    public Payment readPayment(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    // ===== Update =====
    // ===== Update (Prevents duplicates) =====
    public Payment updatePayment(Payment payment) {
        if (payment == null) {
            return null;
        }

        // Find the existing payment
        Payment existingPayment = repository.findById(payment.getPaymentId()).orElse(null);

        if (existingPayment != null) {
            // Update fields
            existingPayment = new Payment.PaymentBuilder()
                    .setPaymentId(existingPayment.getPaymentId()) // Keep same ID
                    .setRentalID(payment.getRentalID())
                    .setPaymentAmount(payment.getPaymentAmount())
                    .setPaymentMethod(payment.getPaymentMethod())
                    .setPaymentDate(payment.getPaymentDate())
                    .setPaymentStatus(payment.getPaymentStatus())
                    .build();

            // Save and return updated payment
            return repository.save(existingPayment);
        }

        return null; // Not found
    }

//    public Payment updatePayment(Payment payment) {
//        Payment existingPayment = repository.findById(payment.getPaymentId()).orElse(null);
//        if (existingPayment != null) {
//
//            return repository.save(existingPayment);
//        } else {
//            return null;
//        }
//    }
//    public Payment updatePayment(Payment payment) {
//        if (payment == null || payment.getPaymentId() == null ||
//                !repository.existsById(payment.getPaymentId())) {
//            return null;
//        }
//        return repository.save(payment);
//    }
//    public Payment updatePayment(Payment payment) {
//        if (payment.getPaymentId() == null || !repository.existsById(payment.getPaymentId())) {
//            return null; // No update if it doesn't exist
//        }
//        return repository.save(payment);
//    }

//    public Payment updatePayment(Payment payment) {
//        if (payment == null ||
//             //   !Helper.isValidId(payment.getPaymentID()) ||
//             //   !Helper.isValidId(payment.getRentalID()) ||
//                !Helper.isValidAmount(payment.getPaymentAmount()) ||
//                Helper.isNullOrEmpty(payment.getPaymentMethod()) ||
//                payment.getPaymentDate() == null ||
//                !Helper.isNotFutureDate(payment.getPaymentDate()) ||
//                Helper.isNullOrEmpty(payment.getPaymentStatus())) {
//            return null;
//        }
//        return this.repository.save(payment);
//    }

    // ===== Delete =====
    public void deletePayment(Integer id) {
        this.repository.deleteById(id);
    }

    // ===== Get All =====
    public List<Payment> getAllPayments() {
        return this.repository.findAll();
    }

    // ===== Find by Status =====
    public List<Payment> getPaymentsByStatus(String status) {
        return this.repository.findByPaymentStatus(status);
    }
}
