package com.pi.controllers;

import com.pi.config.AuthData;
import com.pi.config.JwtTokenProvider;
import com.pi.entities.User;
import com.pi.entities.UserPrincipal;
import com.pi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@Slf4j
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;



    @MutationMapping
    public AuthData login(@Argument String username, @Argument String password) {
        log.info("Attempting to log in user: {}", username);
        try {
            User user = userService.getByEmail(username);

            if (!passwordEncoder.matches(password, user.getPassword())) {
                log.error("Login failed for user: {}", username);
                return null ;
            }

            // Assuming UserPrincipal is your UserDetails implementation
            UserPrincipal userPrincipal = new UserPrincipal();
            userPrincipal.setId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setEmail(user.getEmail());
            // Populate other necessary fields in UserPrincipal

            String jwt = jwtTokenProvider.generateToken(userPrincipal);

            log.info("User {} logged in successfully.", username);
            AuthData authData = new AuthData();
              authData.setToken(jwt);
             return authData;

        } catch (Exception e) {
            log.error("Login attempt for user {} failed due to an error: {}", username, e.getMessage());
            return null;
        }
    }

    @MutationMapping
    public User register(@Argument User user, @Argument Long roleId, @Argument Long serviceDepId) {
        log.info("Registering user: {}", user.getUsername());
        log.info("Role id: {}", roleId);
        log.info("ServiceDep id: {}", serviceDepId);




        try {
            // Check if user already exists
            if (userService.existsByUsername(user.getUsername())) {
                log.error("Registration failed: Username {} is already taken.", user.getUsername());
                throw new RuntimeException("Error: Username is already taken.");
            }

            if (userService.existsByEmail(user.getEmail())) {
                log.error("Registration failed: Email {} is already in use.", user.getEmail());
                throw new RuntimeException("Error: Email is already in use.");
            }

            // Create new user's account
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userService.addUser(user, roleId, serviceDepId);

            // Assuming UserPrincipal is your UserDetails implementation
            UserPrincipal userPrincipal = new UserPrincipal();
            userPrincipal.setId(savedUser.getId());
            userPrincipal.setUsername(savedUser.getUsername());
            userPrincipal.setEmail(savedUser.getEmail());
            String jwt = jwtTokenProvider.generateToken(userPrincipal);

            // You can also add logic to save the JWT to the database if needed

            log.info("User {} registered successfully.", user.getUsername());
            return savedUser;
        } catch (Exception e) {
            log.error("An unexpected error occurred while registering user: {}", user.getUsername(), e);
            throw new RuntimeException("An unexpected error occurred. Please try again later.");
        }
    }


}
