package com.demo.userapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseDTO {

    @JsonProperty("mensaje")
    private String message;

}
