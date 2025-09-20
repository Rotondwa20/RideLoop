package za.co.rideloop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable int id) {
        Optional<User> user = userService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (user.isPresent()) {
            response.put("user", user.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "User not found");
            return ResponseEntity.status(404).body(response);
        }
    }


    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(
            @RequestBody User user,
            @RequestParam(required = false) String securityCode) {

        String message = userService.registerUser(user, securityCode);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);

        if ("User registered successfully".equals(message)) {
            return ResponseEntity.status(201).body(response);
        } else {
            return ResponseEntity.status(400).body(response);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User with ID " + id + " deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Map<String, Object> response = new HashMap<>();
        Optional<User> userOpt = userService.loginUser(email, password);

        if (userOpt.isEmpty()) {
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(400).body(response);
        }

        response.put("message", "Login successful");
        response.put("user", userOpt.get());
        return ResponseEntity.ok(response);
    }




}
