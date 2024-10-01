package com.demo.userapp.service;

import com.demo.userapp.dto.request.UserRequestDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> registerUser(UserRequestDTO user);

}
