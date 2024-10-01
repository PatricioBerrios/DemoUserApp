package com.demo.userapp.util;

import com.demo.userapp.dto.ValidationUserRequest;
import com.demo.userapp.dto.request.UserRequestDTO;
import com.demo.userapp.dto.response.UserResponseDTO;
import com.demo.userapp.entity.PhoneEntity;
import com.demo.userapp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class UserUtil {

    @Autowired
    JwtUtil jwtUtil;

    private static final String INVALID_EMAIL_MSG = "El correo electrónico ingresado no es válido.";
    private static final String INVALID_PASSWORD_MSG = "Formato de contraseña no válido.";
    private static final String INVALID_REQUEST = "Request no válido.";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    @Value("${password.regex}")
    private String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"; //Default value for junit testing

    public UserEntity getUserEntity(UserRequestDTO user) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setName(user.getName());
        userEntity.setToken(jwtUtil.generateToken(user.getEmail()));
        return userEntity;
    }

    public List<PhoneEntity> getUserPhones(UserRequestDTO user, String id) {

        if (user.getPhones() == null || user.getPhones().isEmpty()) {
            return List.of();
        } else {
            return user.getPhones().stream()
                    .map(phoneDTO -> {
                        PhoneEntity phoneEntity = new PhoneEntity();
                        phoneEntity.setUserId(id);
                        phoneEntity.setNumber(phoneDTO.getNumber());
                        phoneEntity.setCityCode(phoneDTO.getCityCode());
                        phoneEntity.setCountryCode(phoneDTO.getCountryCode());
                        return phoneEntity;
                    })
                    .collect(Collectors.toList());
        }
    }

    public ValidationUserRequest validateEmailAndPassword(UserRequestDTO user) {

        if (user == null) {
            return ValidationUserRequest.failure(INVALID_REQUEST);
        }

        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            return ValidationUserRequest.failure(INVALID_EMAIL_MSG);
        }

        if (user.getPassword() == null || !Pattern.compile(PASSWORD_REGEX).matcher(user.getPassword()).matches()) {
            return ValidationUserRequest.failure(INVALID_PASSWORD_MSG);
        }

        return ValidationUserRequest.success();
    }

    public UserResponseDTO getUserResponse(UserEntity userEntity) {

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userEntity.getId().toString());
        userResponseDTO.setToken(userEntity.getToken());
        userResponseDTO.setActive(userEntity.isActive());
        userResponseDTO.setCreated(userEntity.getCreated());
        userResponseDTO.setModified(userEntity.getModified());
        userResponseDTO.setLastLogin(userEntity.getLastLogin());
        return userResponseDTO;

    }
}
