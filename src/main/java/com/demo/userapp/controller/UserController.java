package com.demo.userapp.controller;

import com.demo.userapp.dto.request.UserRequestDTO;
import com.demo.userapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "Email data integrity violation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDTO user) {

        log.info("Received request to register user: {}", user.getEmail());

        ResponseEntity<?> response;
        try {
            response = userService.registerUser(user);
            log.info("User registered successfully: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Error occurred while registering user: {}, Error: {}", user.getEmail(), e.getMessage());
            throw e;
        }

        return response;
    }

}
