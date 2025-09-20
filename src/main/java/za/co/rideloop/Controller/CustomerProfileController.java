package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Service.CustomerProfileService;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService service;


    @GetMapping("/me")
    public CustomerProfile getOrCreateMyProfile(@RequestParam int userID) {
        return service.getProfileByUserId(userID)
                .orElseGet(() -> {
                    User user = service.getUserById(userID);
                    CustomerProfile profile = new CustomerProfile();
                    profile.setUser(user);
                    profile.setStatus("pending");
                    return service.createProfileForUser(profile, userID);
                });
    }

    @PutMapping("/me")
    public CustomerProfile updateMyProfile(@RequestParam int userID, @RequestBody CustomerProfile profile) {
        profile.setUser(service.getUserById(userID));
        return service.updateProfile(profile, false);
    }


    @PutMapping("/{id}/status")
    public CustomerProfile updateProfileStatus(@PathVariable int id, @RequestParam String status) {
        CustomerProfile profile = service.readProfile(id);
        profile.setStatus(status);
        return service.updateProfile(profile, true); // admin = true
    }



    @PostMapping("/admin")
    public CustomerProfile createProfileForAdmin(@RequestBody CustomerProfile profile) {
        return service.createProfileForUser(profile, profile.getUser().getUserID());
    }

    @PutMapping("/{id}")
    public CustomerProfile updateProfile(@PathVariable int id, @RequestBody CustomerProfile profile) {
        profile.setProfileID(id);
        return service.updateProfile(profile, false); // not changing status
    }

    @GetMapping
    public List<CustomerProfile> getAllProfiles() {
        return service.getAllProfiles();
    }

    @GetMapping("/{id}")
    public CustomerProfile getProfileById(@PathVariable int id) {
        return service.readProfile(id); // already throws exception if not found
    }


    @GetMapping("/filter")
    public List<CustomerProfile> getProfilesByStatus(@RequestParam(defaultValue = "all") String status) {
        if ("all".equalsIgnoreCase(status)) {
            return service.getAllProfiles();
        }
        return service.getProfilesByStatus(status);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable int id) {
        service.deleteProfile(id);
    }
}
