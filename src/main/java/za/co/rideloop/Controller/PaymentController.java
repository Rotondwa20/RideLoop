package za.co.rideloop.Controller;

/**
 * PaymentController.java
 * Controller class for managing Payment operations.
 *
 * @Author: Ndyebo Qole
 * @Student Number: 210018615
 * Group 3I
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment") // Base path for all Payment-related endpoints
public class PaymentController {

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // ===== CREATE =====
    @PostMapping("/create")
    public Payment create(@RequestBody Payment payment) {
        return service.create(payment);
    }

    // ===== READ =====
    @GetMapping("/read/{id}")
    public Payment read(@PathVariable Long id) {
        return service.read(id);
    }

    // ===== UPDATE =====
    @PutMapping("/update")
    public Payment update(@RequestBody Payment payment) {
        return service.update(payment);
    }


    // ===== GET ALL =====
    @GetMapping("/getAll")
    public List<Payment> getAll() {
        return service.getAll();
    }

    // ===== GET BY STATUS =====
    @GetMapping("/get-by-status/{status}")
    public List<Payment> getByStatus(@PathVariable String status) {
        return service.getPaymentsByStatus(status);
    }

    // ===== (Optional) GET BY METHOD =====
    @GetMapping("/get-by-method/{method}")
    public List<Payment> getByMethod(@PathVariable String method) {
        return service.getPaymentsByMethod(method);
    }
}
