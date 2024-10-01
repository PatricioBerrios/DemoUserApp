package com.demo.userapp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTO {

    private String number;
    private String cityCode;
    private String countryCode;

}
