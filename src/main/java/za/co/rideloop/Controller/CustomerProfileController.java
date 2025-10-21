



package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.co.rideloop.Domain.CustomerProfile;
import za.co.rideloop.Service.CustomerProfileService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/profiles")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerProfileController {

    private final CustomerProfileService profileService;

    @Autowired
    public CustomerProfileController(CustomerProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<CustomerProfile> createProfile(@RequestBody CustomerProfile profile,
                                                         @PathVariable int userId) {
        CustomerProfile created = profileService.createProfileForUser(profile, userId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<CustomerProfile> getProfile(@PathVariable int profileId) {
        return profileService.getProfileById(profileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CustomerProfile> getProfileByUser(@PathVariable int userId) {
        return profileService.getProfileByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CustomerProfile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/status/{status}")
    public List<CustomerProfile> getProfilesByStatus(@PathVariable String status) {
        return profileService.getProfilesByStatus(status);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<CustomerProfile> updateProfile(@RequestBody CustomerProfile profile,
                                                         @PathVariable int profileId,
                                                         @RequestParam(defaultValue = "false") boolean isAdmin) {
        profile.setProfileID(profileId);
        CustomerProfile updated = profileService.updateProfile(profile, isAdmin);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int profileId) {
        profileService.deleteProfile(profileId);
        return ResponseEntity.noContent().build();
    }

    // ---------- Document Upload ----------
    @PostMapping("/{profileId}/document/id")
    public ResponseEntity<Void> uploadIdDocument(@PathVariable int profileId,
                                                 @RequestParam("file") MultipartFile file) throws IOException {
        profileService.addIdDocument(profileId, file.getBytes());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{profileId}/document/license")
    public ResponseEntity<Void> uploadLicenseDoc(@PathVariable int profileId,
                                                 @RequestParam("file") MultipartFile file) throws IOException {
        profileService.addLicenseDoc(profileId, file.getBytes());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{profileId}/document/id-copy")
    public ResponseEntity<Void> uploadIdCopy(@PathVariable int profileId,
                                             @RequestParam("file") MultipartFile file) throws IOException {
        profileService.addIdCopy(profileId, file.getBytes());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{profileId}/document/residence")
    public ResponseEntity<Void> uploadProofOfResidence(@PathVariable int profileId,
                                                       @RequestParam("file") MultipartFile file) throws IOException {
        profileService.addProofOfResidence(profileId, file.getBytes());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{profileId}/document/profile-picture")
    public ResponseEntity<Void> uploadProfilePicture(@PathVariable int profileId,
                                                     @RequestParam("file") MultipartFile file) throws IOException {
        profileService.addProfilePicture(profileId, file.getBytes());
        return ResponseEntity.ok().build();
    }

    // ---------- Document View ----------
    @GetMapping("/{profileId}/document/id")
    public ResponseEntity<byte[]> viewIdDocument(@PathVariable int profileId) {
        byte[] data = profileService.getIdDocument(profileId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"id_document_" + profileId + ".pdf\"")
                .body(data);
    }

    @GetMapping("/{profileId}/document/license")
    public ResponseEntity<byte[]> viewLicenseDoc(@PathVariable int profileId) {
        byte[] data = profileService.getLicenseDoc(profileId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"license_doc_" + profileId + ".pdf\"")
                .body(data);
    }

    @GetMapping("/{profileId}/document/id-copy")
    public ResponseEntity<byte[]> viewIdCopy(@PathVariable int profileId) {
        byte[] data = profileService.getIdCopy(profileId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"id_copy_" + profileId + ".pdf\"")
                .body(data);
    }

    @GetMapping("/{profileId}/document/residence")
    public ResponseEntity<byte[]> viewProofOfResidence(@PathVariable int profileId) {
        byte[] data = profileService.getProofOfResidence(profileId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"proof_of_residence_" + profileId + ".pdf\"")
                .body(data);
    }

    @GetMapping("/{profileId}/document/profile-picture")
    public ResponseEntity<byte[]> viewProfilePicture(@PathVariable int profileId) {
        byte[] data = profileService.getProfilePicture(profileId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or MediaType.IMAGE_PNG depending on your files
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"profile_picture_" + profileId + ".jpg\"")
                .body(data);
    }}