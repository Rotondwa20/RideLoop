package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Rental;
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

    @PostMapping("/create")
    public Rental create(@RequestBody Rental rental) {
        return service.create(rental);
    }

    @GetMapping("/read/{id}")
    public Rental read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Rental update(@RequestBody Rental rental) {
        return service.update(rental);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/getAll")
    public List<Rental> getAll() {
        return service.getAll();
    }

    @GetMapping("/get-by-status/{status}")
    public List<Rental> getByStatus(@PathVariable String status) {
        return service.getRentalsByStatus(status);
    }
}