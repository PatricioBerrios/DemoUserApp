package com.demo.userapp.service;

import com.demo.userapp.dto.ValidationUserRequest;
import com.demo.userapp.dto.request.UserRequestDTO;
import com.demo.userapp.dto.response.UserResponseDTO;
import com.demo.userapp.entity.PhoneEntity;
import com.demo.userapp.entity.UserEntity;
import com.demo.userapp.exception.UserEmailIntegrityException;
import com.demo.userapp.repository.PhoneRepository;
import com.demo.userapp.repository.UserRepository;
import com.demo.userapp.service.impl.UserServiceImpl;
import com.demo.userapp.util.MessageUtil;
import com.demo.userapp.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserUtil userUtil;

    @Mock
    UserRepository userRepository;

    @Mock
    PhoneRepository phoneRepository;
    
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    MessageUtil messageUtil;

    @Test
    public void registerUserSuccesfullyTest(){

        UserRequestDTO userRequestDTO = getUser();
        UserEntity userEntity = getUserEntity();

        when(userUtil.validateEmailAndPassword(userRequestDTO))
                .thenReturn(getValidationUserRequestSuccess());
        when(userUtil.getUserEntity(userRequestDTO))
                .thenReturn(userEntity);
        when(userRepository.save(userEntity))
                .thenReturn(userEntity);
        when(userUtil.getUserPhones(any(UserRequestDTO.class), anyString()))
                .thenReturn(getPhones());
        when(phoneRepository.saveAll(anyList()))
                .thenReturn(getPhones());
        when(userUtil.getUserResponse(any(UserEntity.class)))
                .thenReturn(getUserResponseDTO());
        assertNotNull(userService.registerUser(userRequestDTO));

    }

    @Test
    public void registerUserRequestNotValidate(){
        UserRequestDTO userRequestDTO = getUser();

        when(userUtil.validateEmailAndPassword(userRequestDTO))
                .thenReturn(getValidationUserRequestFailure());
        assertNotNull(userService.registerUser(userRequestDTO));
    }

    @Test
    public void registerUserException(){
        UserRequestDTO userRequestDTO = getUser();
        UserEntity userEntity = getUserEntity();

        when(userUtil.validateEmailAndPassword(userRequestDTO))
                .thenReturn(getValidationUserRequestSuccess());
        when(userUtil.getUserEntity(userRequestDTO))
                .thenReturn(userEntity);
        when(userRepository.save(userEntity))
                .thenThrow(new DataIntegrityViolationException("Email already registered"));
        assertThrows(UserEmailIntegrityException.class, () -> {
            userService.registerUser(userRequestDTO);
        });
    }

    private UserResponseDTO getUserResponseDTO() {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId("12345");
        userResponseDTO.setCreated(LocalDateTime.now().minusDays(1));
        userResponseDTO.setModified(LocalDateTime.now());
        userResponseDTO.setLastLogin(LocalDateTime.now().minusHours(2));
        userResponseDTO.setToken("mockToken123456");
        userResponseDTO.setActive(true);

        return userResponseDTO;
    }

    private List<PhoneEntity> getPhones() {
        PhoneEntity phone1 = new PhoneEntity();
        phone1.setUserId("user-1");
        phone1.setNumber("123456789");
        phone1.setCityCode("1");
        phone1.setCountryCode("US");

        PhoneEntity phone2 = new PhoneEntity();
        phone2.setUserId("user-2");
        phone2.setNumber("987654321");
        phone2.setCityCode("44");
        phone2.setCountryCode("GB");

        return Arrays.asList(phone1, phone2);
    }

    private ValidationUserRequest getValidationUserRequestSuccess() {
        return ValidationUserRequest.success();
    }

    private ValidationUserRequest getValidationUserRequestFailure() {
        return ValidationUserRequest.failure("Error");
    }

    private UserEntity getUserEntity() {
        UserEntity user = new UserEntity();

        user.setId(UUID.randomUUID());
        user.setName("Patricio Ber");
        user.setEmail("patricio@example.com");
        user.setPassword("securePassword123");
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken("sampleToken");
        user.setActive(true);

        return user;
    }

    private UserRequestDTO getUser(){

        UserRequestDTO user = new UserRequestDTO();
        user.setEmail("pat@hotmail.com");
        user.setName("Patricio");
        user.setPassword("123456qw");
        return user;
    }
    
}
