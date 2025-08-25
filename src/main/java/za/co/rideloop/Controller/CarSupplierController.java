package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.CarSupplier;
import za.co.rideloop.Service.ICarSupplierService;

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
@RequestMapping("/api/car-suppliers")
public class CarSupplierController {
    private final ICarSupplierService carSupplierService;

    @Autowired
    public CarSupplierController(ICarSupplierService carSupplierService) {
        this.carSupplierService = carSupplierService;
    }

    @PostMapping
    public ResponseEntity<CarSupplier> createCarSupplier(@RequestBody CarSupplier carSupplier) {
        CarSupplier created = carSupplierService.create(carSupplier);
        return ResponseEntity.created(URI.create("/api/car-suppliers/" + created.getSupplierID()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<CarSupplier>> getAllCarSuppliers() {
        return ResponseEntity.ok(carSupplierService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarSupplier> getCarSupplierById(@PathVariable Integer id) {
        CarSupplier existing = carSupplierService.read(id);
        if (existing == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(existing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarSupplier> updateCarSupplier(@PathVariable Integer id,
                                                         @RequestBody CarSupplier carSupplier) {
        CarSupplier existing = carSupplierService.read(id);
        if (existing == null) return ResponseEntity.notFound().build();

        CarSupplier toUpdate = new CarSupplier.Builder()
                .supplierID(id)
                .name(carSupplier.getName() != null ? carSupplier.getName() : existing.getName())
                .contactPerson(carSupplier.getContactPerson() != null ? carSupplier.getContactPerson() : existing.getContactPerson())
                .supplyDate(carSupplier.getSupplyDate() != null ? carSupplier.getSupplyDate() : existing.getSupplyDate())
                .contractStatus(carSupplier.getContractStatus() != null ? carSupplier.getContractStatus() : existing.getContractStatus())
                .build();

        CarSupplier updated = carSupplierService.update(toUpdate);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarSupplier(@PathVariable Integer id) {
        CarSupplier existing = carSupplierService.read(id);
        if (existing == null) return ResponseEntity.notFound().build();
        carSupplierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
