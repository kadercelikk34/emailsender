package com.email.sender.emailsender.controller;

import com.email.sender.emailsender.service.ProviderAService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProviderAController.class)
public class ProviderAControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProviderAService service;

    @Test
    public void testWelcome() throws Exception {
        given(service.createWelcome(anyString(),anyString())).willReturn(true);

        mvc.perform(get("/provider-a/welcome/{firstName}/{lastName}", "testFirstName", "testLastName")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testForgotPassword() throws Exception {
        given(service.forgotPassword(anyString())).willReturn(true);

        mvc.perform(get("/provider-a/forgot-password/{passwordResetUrl}", "testUrl")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}