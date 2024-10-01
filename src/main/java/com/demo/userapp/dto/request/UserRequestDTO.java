package com.demo.userapp.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDTO {

    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;

}
