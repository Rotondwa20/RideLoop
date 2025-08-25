package za.co.rideloop.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Payment;
import za.co.rideloop.Service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment") // The base path for all Payment related endpoints
public class PaymentController {

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    /**
     * Handles HTTP POST requests to create a new Payment.
     * The Payment object is sent in the request body.
     * @param payment The Payment object to be created.
     * @return The created Payment object, including its generated ID.
     */
    @PostMapping("/create")
    public Payment create(@RequestBody Payment payment) {
        return service.create(payment);
    }

    /**
     * Handles HTTP GET requests to read a Payment by its ID.
     * The Payment ID is passed as a path variable.
     * @param id The ID of the Payment to retrieve.
     * @return The found Payment object, or null if not found.
     */
    @GetMapping("/read/{id}")
    public Payment read(@PathVariable Integer id) {
        return service.read(id);
    }

    /**
     * Handles HTTP PUT requests to update an existing Payment.
     * The updated Payment object is sent in the request body.
     * @param payment The Payment object with updated details.
     * @return The updated Payment object, or null if the original Payment was not found.
     */
    @PutMapping("/update")
    public Payment update(@RequestBody Payment payment) {
        return service.update(payment);
    }

    /**
     * Handles HTTP DELETE requests to remove a Payment by its ID.
     * The Payment ID is passed as a path variable.
     * @param id The ID of the Payment to be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    /**
     * Handles HTTP GET requests to retrieve all Payments.
     * @return A list of all Payment objects in the database.
     */
    @GetMapping("/getAll")
    public List<Payment> getAll() {
        return service.getAll();
    }

    /**
     * Handles HTTP GET requests to retrieve Payments by their status.
     * The payment status is passed as a path variable.
     * @param status The status to filter payments by (e.g., "Paid", "Pending").
     * @return A list of Payment objects matching the given status.
     */
    @GetMapping("/get-by-status/{status}")
    public List<Payment> getByStatus(@PathVariable String status) {
        return service.getPaymentsByStatus(status);
    }
}