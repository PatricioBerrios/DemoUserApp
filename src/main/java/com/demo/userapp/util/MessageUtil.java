package com.demo.userapp.util;

import com.demo.userapp.dto.response.MessageResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

    public MessageResponseDTO getMessage(String message){
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setMessage(message);
        return messageResponseDTO;
    }

}
