package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Service.CustomerRewardsService;

@RestController
@RequestMapping("/api/customerRewards")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerRewardsController {

    @Autowired
    private CustomerRewardsService service;

    // Create a new reward
    @PostMapping("/create")
    public CustomerRewards createReward(@RequestBody CustomerRewards reward) {
        return service.createReward(reward);
    }

    // Get a reward by ID
    @GetMapping("/{id}")
    public CustomerRewards readReward(@PathVariable int id) {
        return service.readReward(id);
    }

    // Update a reward
    @PutMapping("/update")
    public CustomerRewards updateReward(@RequestBody CustomerRewards reward) {
        return service.updateReward(reward);
    }

    // Delete a reward by ID
    @DeleteMapping("/{id}")
    public void deleteReward(@PathVariable int id) {
        service.deleteReward(id);
    }

}

