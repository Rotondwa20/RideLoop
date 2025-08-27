package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.Maintenance;
import za.co.rideloop.Service.MaintenanceService;

import java.util.List;


@RestController
@RequestMapping("/maintenance")
@CrossOrigin(origins = "http://localhost:3000")
public class MaintenanceController {
    @Autowired
    private MaintenanceService service;


    @PostMapping("/create")
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return service.create(maintenance);
    }


    @GetMapping("/{id}")
    public Maintenance read(@PathVariable Long id) {
        return service.read(id);
    }


    @PutMapping("/update")
    public Maintenance update(@RequestBody Maintenance maintenance) {
        return service.update(maintenance);
    }


    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }


    @GetMapping("/all")
    public List<Maintenance> getAll() {
        return service.getAll();
    }



}
