package za.co.rideloop.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Service.CustomerRewardsService;

import java.util.Optional;

@RestController
@RequestMapping("/api/rewards")
public class CustomerRewardsController {

    private final CustomerRewardsService rewardsService;

    public CustomerRewardsController(CustomerRewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    /**
     * Process a payment and update rewards.
     * Example Postman URL:
     * POST http://localhost:8080/rideloopdb/api/rewards/process?paymentId=3&profileId=3
     */
    @PostMapping("/process")
    public ResponseEntity<CustomerRewards> processRewards(
            @RequestParam Integer paymentId,
            @RequestParam Integer profileId) {
        try {
            CustomerRewards rewards = rewardsService.processPaymentRewards(paymentId, profileId);
            return ResponseEntity.ok(rewards);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Get rewards for a specific customer profile.
     * Example Postman URL:
     * GET http://localhost:8080/rideloopdb/api/rewards/customer/3
     */
    @GetMapping("/customer/{profileId}")
    public ResponseEntity<CustomerRewards> getCustomerRewards(@PathVariable Integer profileId) {
        Optional<CustomerRewards> rewardsOpt = rewardsService.getRewardsByProfileId(profileId);
        return rewardsOpt
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
