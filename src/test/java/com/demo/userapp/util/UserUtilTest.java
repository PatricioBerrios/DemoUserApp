package com.demo.userapp.util;

import com.demo.userapp.dto.request.PhoneDTO;
import com.demo.userapp.dto.request.UserRequestDTO;
import com.demo.userapp.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserUtilTest {

    @Mock
    JwtUtil jwtUtil;

    @InjectMocks
    UserUtil userUtil;

    @Test
    public void getUserEntityTest(){
        assertNotNull(userUtil.getUserEntity(getUserWithPhones()));
    }

    @Test
    public void getUserPhonesSuccessTest(){
        assertNotNull(userUtil.getUserPhones(getUserWithPhones(), "3"));
    }

    @Test
    public void getUserPhonesEmptyTest(){
        UserRequestDTO userRequestDTO = getUserWithNoPhones();
        assertNotNull(userUtil.getUserPhones(userRequestDTO, "3"));
        userRequestDTO.setPhones(null);
        assertNotNull(userUtil.getUserPhones(userRequestDTO, "3"));
    }

    @Test
    public void validateEmailAndPasswordUserNullTest(){
        assertNotNull(userUtil.validateEmailAndPassword(null));
    }

    @Test
    public void validateEmailAndPasswordInvalidMailTest(){
        UserRequestDTO userRequestDTO = getUserWithPhones();
        userRequestDTO.setEmail("testtest.com");
        assertNotNull(userUtil.validateEmailAndPassword(userRequestDTO));
        userRequestDTO.setEmail(null);
        assertNotNull(userUtil.validateEmailAndPassword(userRequestDTO));
    }

    @Test
    public void validateEmailAndPasswordInvalidPasswordTest(){
        UserRequestDTO userRequestDTO = getUserWithPhones();
        userRequestDTO.setPassword("12345");
        assertNotNull(userUtil.validateEmailAndPassword(userRequestDTO));
        userRequestDTO.setPassword(null);
        assertNotNull(userUtil.validateEmailAndPassword(userRequestDTO));
    }

    @Test
    public void validateEmailAndPasswordSuccessTest() {
        assertNotNull(userUtil.validateEmailAndPassword(getUserWithPhones()));
    }

    @Test
    public void getUserResponseTest(){
        assertNotNull(userUtil.getUserResponse(getUserEntity()));
    }

    private UserRequestDTO getUserWithPhones() {
        UserRequestDTO user = new UserRequestDTO();
        user.setEmail("pat@hotmail.com");
        user.setName("Patricio");
        user.setPassword("StrongPass1");

        PhoneDTO phone = new PhoneDTO();
        phone.setNumber("3456789");
        phone.setCityCode("22");
        phone.setCountryCode("+56");

        user.setPhones(Collections.singletonList(phone));

        return user;
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

    private UserRequestDTO getUserWithNoPhones() {
        UserRequestDTO user = new UserRequestDTO();
        user.setEmail("pat@hotmail.com");
        user.setName("Patricio");
        user.setPassword("Berrios");

        return user;
    }

}
