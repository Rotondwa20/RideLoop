package za.co.rideloop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Repository.PaymentRepository;
import za.co.rideloop.Util.Helper;

import java.util.List;
import java.util.Optional;

/**
 * RentalFactory.java
 * RentalFactory Model Class
 *
 * @Author: Ndyebo Qole
 * @Student Number: 210018615
 * Group 3 I
 **/
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // ===== Create =====
    public Payment create(Payment payment) {
        if (!isValidPayment(payment)) {
            throw new IllegalArgumentException("Invalid payment data");
        }
        return paymentRepository.save(payment);
    }

    // ===== Read =====
    public Payment read(Long id) {
        if (id == null) return null;
        return paymentRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    // ===== Update =====
    public Payment update(Payment payment) {
        if (payment == null || payment.getPaymentId() == null) {
            return null;
        }

        Optional<Payment> existingOpt = paymentRepository.findById(Math.toIntExact(payment.getPaymentId()));
        if (existingOpt.isEmpty()) return null;

        Payment existingPayment = existingOpt.get();
        Payment updated = new Payment.PaymentBuilder()
                .setPaymentId(existingPayment.getPaymentId())
                .setRental(payment.getRental())
                .setPaymentAmount(payment.getPaymentAmount())
                .setPaymentMethod(payment.getPaymentMethod())
                .setPaymentDate(payment.getPaymentDate())
                .setPaymentStatus(payment.getPaymentStatus())
                .build();

        return paymentRepository.save(updated);
    }

    // ===== Delete =====
    public boolean delete(Long id) {
        if (id != null && paymentRepository.existsById(Math.toIntExact(id))) {
            paymentRepository.deleteById(Math.toIntExact(id));
            return true;
        }
        return false;
    }

    // ===== Get All =====
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    // ===== Custom Queries =====
    public List<Payment> getPaymentsByRental(Rental rental) {
        if (rental == null) return List.of();
        return paymentRepository.findByRental(rental);
    }

    public List<Payment> getPaymentsByStatus(String status) {
        if (status == null || status.isBlank()) return List.of();
        return paymentRepository.findByPaymentStatus(status);
    }

    public List<Payment> getPaymentsByMethod(String method) {
        if (method == null || method.isBlank()) return List.of();
        return paymentRepository.findByPaymentMethod(method);
    }

    // ===== Validation =====
    private boolean isValidPayment(Payment payment) {
        return payment != null &&
                payment.getRental() != null &&
                Helper.isValidAmount(payment.getPaymentAmount()) &&
                !Helper.isNullOrEmpty(payment.getPaymentMethod()) &&
                payment.getPaymentDate() != null &&
                Helper.isNotFutureDate(payment.getPaymentDate()) &&
                !Helper.isNullOrEmpty(payment.getPaymentStatus());
    }

    }

