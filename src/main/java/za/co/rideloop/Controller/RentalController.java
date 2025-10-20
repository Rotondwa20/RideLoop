package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Rental;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Service.RentalService;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RentalService service;

    @Autowired
    public RentalController(RentalService service) {
        this.service = service;
    }

    // ===== CREATE =====
    @PostMapping("/create")
    public ResponseEntity<Rental>create(@RequestBody Rental rental) {
   Rental saved = service.create(rental);
        return ResponseEntity.ok(saved);

    }

    // ===== READ =====
    @GetMapping("/read/{id}")
    public Rental read(@PathVariable Integer id) {
        return service.read(id);
    }




    // ===== GET ALL =====
    @GetMapping("/getAll")
    public List<Rental> getAll() {
        return service.getAll();
    }

    // ===== GET RENTALS BY CUSTOMER =====
    // Pass the full CustomerProfile object or just use ID in service
// RentalController.java
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Rental>> getRentalsByCustomer(@PathVariable int customerId) {
        List<Rental> rentals = service.getRentalsByCustomer(customerId);
        return ResponseEntity.ok(rentals);
    }
}
