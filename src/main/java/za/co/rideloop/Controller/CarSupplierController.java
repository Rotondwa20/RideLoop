package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.CarSupplier;
import za.co.rideloop.Service.CarSupplierService;

import java.net.URI;
import java.util.List;

/**
 * CarSupplierServiceController.java
 * CarSupplierServiceController model class
 *
 * @author : Swatsi Bongani Ratia
 * @studnr : 230724477
 * @group : 3I
 * @Java version: "21.0.3" 2024-04-16 LTS
 */
@RestController
@RequestMapping("/carsupplier") // The base path for all CarSupplier related endpoints
public class CarSupplierController {
    private final CarSupplierService service;

    @Autowired
    public CarSupplierController(CarSupplierService service) {
        this.service = service;
    }

    /**
     * Handles HTTP POST requests to create a new CarSupplier.
     * The CarSupplier object is sent in the request body.
     * @param carSupplier The CarSupplier object to be created.
     * @return The created CarSupplier object, including its generated ID.
     */
    @PostMapping("/create")
    public CarSupplier create(@RequestBody CarSupplier carSupplier) {
        return service.createCarSupplier(carSupplier);
    }

    /**
     * Handles HTTP GET requests to read a CarSupplier by its ID.
     * The CarSupplier ID is passed as a path variable.
     * @param id The ID of the CarSupplier to retrieve.
     * @return The found CarSupplier object, or null if not found.
     */
    @GetMapping("/read/{id}")
    public CarSupplier read(@PathVariable Integer id) {
        return service.readCarSupplier(id);
    }

    /**
     * Handles HTTP PUT requests to update an existing CarSupplier.
     * The updated CarSupplier object is sent in the request body.
     * @param carSupplier The CarSupplier object with updated details.
     * @return The updated CarSupplier object, or null if the original CarSupplier was not found.
     */
    @PutMapping("/update")
    public CarSupplier update(@RequestBody CarSupplier carSupplier) {
        return service.updateCarSupplier(carSupplier);
    }

    /**
     * Handles HTTP DELETE requests to remove a CarSupplier by its ID.
     * The CarSupplier ID is passed as a path variable.
     * @param id The ID of the CarSupplier to be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteCarSupplier(id);
    }

    /**
     * Handles HTTP GET requests to retrieve all CarSuppliers.
     * @return A list of all CarSupplier objects in the database.
     */
    @GetMapping("/getAll")
    public List<CarSupplier> getAll() {
        return service.getAllCarSuppliers();
    }
}
