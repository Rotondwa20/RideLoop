package za.co.rideloop.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.CustomerRewards;
import za.co.rideloop.Service.CustomerRewardsService;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerRewardsController {

    private final CustomerRewardsService rewardsService;

    public CustomerRewardsController(CustomerRewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    /** 🎁 Process rewards for each completed payment */
    @PostMapping("/process")
    public ResponseEntity<CustomerRewards> processRewards(
            @RequestParam Long paymentId,
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

    /** 🧾 Get all rewards for a specific customer */
    @GetMapping("/customer/{profileID}")
    public ResponseEntity<List<CustomerRewards>> getCustomerRewards(@PathVariable Integer profileID) {
        List<CustomerRewards> rewardsList = rewardsService.getAllRewardsByProfileId(profileID);

        if (rewardsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rewardsList);
    }

    /** 🏆 Get reward summary (total points + current tier) */
    @GetMapping("/customer/{profileID}/summary")
    public ResponseEntity<RewardSummaryDTO> getCustomerRewardSummary(@PathVariable Integer profileID) {
        int totalPoints = rewardsService.getTotalPointsForProfile(profileID);
        String tier = rewardsService.getTierForProfile(profileID);

        RewardSummaryDTO summary = new RewardSummaryDTO(profileID, totalPoints, tier);
        return ResponseEntity.ok(summary);
    }

    /** DTO for reward summary */
    public static class RewardSummaryDTO {
        private Integer profileID;
        private int totalPoints;
        private String tier;

        public RewardSummaryDTO(Integer profileID, int totalPoints, String tier) {
            this.profileID = profileID;
            this.totalPoints = totalPoints;
            this.tier = tier;
        }

        public Integer getProfileID() { return profileID; }
        public int getTotalPoints() { return totalPoints; }
        public String getTier() { return tier; }
    }
}
