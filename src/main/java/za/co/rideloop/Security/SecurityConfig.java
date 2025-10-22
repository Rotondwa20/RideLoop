package za.co.rideloop.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/users/register", "/users/login").permitAll()

                        // Admin endpoints
                        .requestMatchers(
                                "/api/cars/create", "/api/cars/update/**", "/api/cars/delete/**",
                                "/api/locations/create", "/api/locations/update", "/api/locations/delete/**",
                                "/invoice/create", "/invoice/update", "/invoice/delete/**",
                                "/maintenance/create", "/maintenance/update/**", "/maintenance/delete/**",
                                "/payment/update", "/payment/delete/**",
                                "/rental/update", "/rental/delete/**",
                                "/securitycompany/create", "/securitycompany/update", "/securitycompany/delete/**",
                                "/carsupplier/create", "/carsupplier/update", "/carsupplier/delete/**"
                        ).hasRole("ADMIN")

                        // Customer-only endpoints
                        .requestMatchers("/rental/create", "/payment/create", "/api/rewards/process")
                        .hasRole("CUSTOMER")

                        // Shared endpoints for both roles
                        .requestMatchers(
                                "/api/cars/all", "/api/cars/**",
                                "/api/locations/all", "/api/locations/search",
                                "/invoice/getAll", "/invoice/read/**", "/invoice/get-by-reference/**",
                                "/maintenance/all", "/maintenance/{id}",
                                "/payment/getAll", "/payment/read/**", "/payment/get-by-status/**",
                                "/rental/getAll", "/rental/read/**", "/rental/get-by-status/**",
                                "/securitycompany/getAll", "/securitycompany/read/**",
                                "/carsupplier/getAll", "/carsupplier/read/**",
                                "/incidents/**",
                                "/api/rewards/customer/**"
                        ).hasAnyRole("ADMIN", "CUSTOMER")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
