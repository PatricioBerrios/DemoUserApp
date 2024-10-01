package com.demo.userapp.service.impl;

import com.demo.userapp.dto.ValidationUserRequest;
import com.demo.userapp.dto.request.UserRequestDTO;
import com.demo.userapp.entity.UserEntity;
import com.demo.userapp.exception.UserEmailIntegrityException;
import com.demo.userapp.repository.PhoneRepository;
import com.demo.userapp.repository.UserRepository;
import com.demo.userapp.service.UserService;
import com.demo.userapp.util.MessageUtil;
import com.demo.userapp.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserUtil userUtil;

    @Autowired
    MessageUtil messageUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public ResponseEntity<?> registerUser(UserRequestDTO user) {
        try{
            ValidationUserRequest validationResult = userUtil.validateEmailAndPassword(user);

            if (!validationResult.isValid()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(messageUtil.getMessage(validationResult.getMessage()));
            }

            UserEntity userEntity = userRepository.save(userUtil.getUserEntity(user));
            phoneRepository.saveAll(userUtil.getUserPhones(user, userEntity.getId().toString()));

            return ResponseEntity.ok().body(userUtil.getUserResponse(userEntity));
        }catch (DataIntegrityViolationException e) {
            log.error("Data integrity violation occurred while registering user: {}", e.getMessage());
            throw new UserEmailIntegrityException("El correo ya registrado.");
        }
    }

}