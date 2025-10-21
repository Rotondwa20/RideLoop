package za.co.rideloop.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthFilter jwtAuthFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // ✅ Global CORS Configuration for Spring Security
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true); // needed for cookies / JWTs if used in headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // ✅ Enable CORS globally
                .cors().and()
                // ✅ Disable CSRF for APIs
                .csrf(csrf -> csrf.disable())
                // ✅ Configure authorization
                .authorizeHttpRequests(auth -> auth

                        // Public endpoints (no authentication)
                        .requestMatchers("/users/register", "/users/login").permitAll()

                        // Admin-only endpoints
                        .requestMatchers(
                                "/api/cars/create", "/api/cars/update/**", "/api/cars/delete/**",
                                "/api/locations/create", "/api/locations/update", "/api/locations/delete/**",
                                "/invoice/create", "/invoice/update", "/invoice/delete/**",
                                "/maintenance/create", "/maintenance/update/**", "/maintenance/delete/**",
                                "/payment/create", "/payment/update", "/payment/delete/**",
                                "/rental/create", "/rental/update", "/rental/delete/**",
                                "/securitycompany/create", "/securitycompany/update", "/securitycompany/delete/**",
                                "/carsupplier/create", "/carsupplier/update", "/carsupplier/delete/**",
                                "/incidents/delete/**"
                        ).hasRole("ADMIN")

                        // Shared endpoints
                        .requestMatchers(
                                "/api/cars/all", "/api/cars/{id}",
                                "/api/locations/all", "/api/locations/search",
                                "/invoice/getAll", "/invoice/read/**", "/invoice/get-by-reference/**",
                                "/maintenance/all", "/maintenance/{id}",
                                "/payment/getAll", "/payment/read/**", "/payment/get-by-status/**",
                                "/rental/getAll", "/rental/read/**", "/rental/get-by-status/**",
                                "/securitycompany/getAll", "/securitycompany/read/**",
                                "/carsupplier/getAll", "/carsupplier/read/**",
                                "/incidents/create", "/incidents/rental/**", "/incidents/getAll"
                        ).hasAnyRole("ADMIN", "CUSTOMER")

                        // Anything else must be authenticated
                        .anyRequest().authenticated()
                )

                // ✅ Add JWT Filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // ✅ Stateless (no session cookies)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
