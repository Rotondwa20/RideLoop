package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Service.CustomerRewardsService;

import java.util.List;

@RestController
@RequestMapping("/customerRewards")
public class CustomerRewardsController {

    private final CustomerRewardsService service;

    @Autowired
    public CustomerRewardsController(CustomerRewardsService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerRewards> create(@RequestBody CustomerRewards customerRewards) {
        CustomerRewards created = service.create(customerRewards);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CustomerRewards> read(@PathVariable Long id) {
        try {
            CustomerRewards reward = service.read(id);
            return ResponseEntity.ok(reward);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerRewards> update(@PathVariable Long id, @RequestBody CustomerRewards updatedReward) {
        try {
            CustomerRewards updated = service.updateReward(id, updatedReward);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerRewards>> getAll() {
        List<CustomerRewards> rewards = service.getAll();
        return ResponseEntity.ok(rewards);
    }
}
