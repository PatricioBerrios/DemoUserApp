package com.demo.userapp.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MessageUtilTest {

    @InjectMocks
    private MessageUtil messageUtil;

    @Test
    public void getMessageTest(){
        assertNotNull(messageUtil.getMessage("Test"));
    }

}
