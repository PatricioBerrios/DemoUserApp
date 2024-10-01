package com.demo.userapp.controller;

import com.demo.userapp.dto.request.UserRequestDTO;
import com.demo.userapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    public void registerUserSuccessfullyTest(){

        UserRequestDTO userRequestDTO = getUser();
        when(userService.registerUser(userRequestDTO))
                .thenReturn(ResponseEntity.ok().build());
        assertNotNull(userController.registerUser(userRequestDTO));
    }

    @Test
    public void registerUserExceptionTest(){

        UserRequestDTO userRequestDTO = getUser();

        when(userService.registerUser(userRequestDTO))
                .thenThrow(new DataIntegrityViolationException("Email already registered"));
        assertThrows(DataIntegrityViolationException.class, () -> {
            userController.registerUser(userRequestDTO);
        });
    }

    private UserRequestDTO getUser(){

        UserRequestDTO user = new UserRequestDTO();
        user.setEmail("pat@hotmail.com");
        user.setName("Patricio Berrios");
        user.setPassword("123456qw");
        return user;
    }

}
