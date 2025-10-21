package za.co.rideloop.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import za.co.rideloop.Domain.User;
import za.co.rideloop.Security.CustomUserDetails;
import za.co.rideloop.Security.JwtUtil;
import za.co.rideloop.Service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // ✅ Register user
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(
            @RequestBody User user,
            @RequestParam(required = false) String securityCode,
            HttpServletResponse response) {

        // Add security header
        response.setHeader("X-Content-Type-Options", "nosniff");

        String message = userService.registerUser(user, securityCode);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", message);

        if ("User registered successfully".equals(message)) {
            return ResponseEntity.status(201)
                    .header("X-Content-Type-Options", "nosniff")
                    .body(resp);
        } else {
            return ResponseEntity.status(400)
                    .header("X-Content-Type-Options", "nosniff")
                    .body(resp);
        }
    }

    // ✅ Login user (Spring Security + JWT)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> loginData,
            HttpServletResponse response) {

        response.setHeader("X-Content-Type-Options", "nosniff"); // prevent MIME sniffing

        Map<String, Object> resp = new HashMap<>();
        String email = loginData.get("email");
        String password = loginData.get("password");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();

            // Generate JWT token
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

            // Set JWT in a secure cookie
            Cookie jwtCookie = new Cookie("token", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(true);       // ✅ only send over HTTPS
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(24 * 60 * 60); // 1 day
            jwtCookie.setSecure(Boolean.parseBoolean("Strict")); // ✅ prevent CSRF
            response.addCookie(jwtCookie);

            // Build JSON response
            resp.put("message", "Login successful");
            resp.put("token", token);
            resp.put("user", user);

            return ResponseEntity.ok()
                    .header("X-Content-Type-Options", "nosniff")
                    .body(resp);

        } catch (BadCredentialsException e) {
            resp.put("message", "Invalid email or password");
            return ResponseEntity.status(401)
                    .header("X-Content-Type-Options", "nosniff")
                    .body(resp);
        }
    }
}
