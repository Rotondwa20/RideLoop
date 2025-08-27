package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Service.CustomerRewardsService;

import java.util.List;


@RestController
@RequestMapping("/api/customerRewards")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerRewardsController {

    @Autowired
    private CustomerRewardsService service;

    @PostMapping("/create")
    public CustomerRewards createReward(@RequestBody CustomerRewards reward) {
        return service.createReward(reward);
    }

    @GetMapping("/{id}")
    public CustomerRewards readReward(@PathVariable Long id) {
        return service.readReward(id);
    }

    @PutMapping("/update")
    public CustomerRewards updateReward(@RequestBody CustomerRewards reward) {
        return service.updateReward(reward);
    }

    @DeleteMapping("/{id}")
    public void deleteReward(@PathVariable Long id) {
        service.deleteReward(id);
    }

    @GetMapping("/all")
    public List<CustomerRewards> getAllRewards() {
        return service.getAllRewards();
    }
}
