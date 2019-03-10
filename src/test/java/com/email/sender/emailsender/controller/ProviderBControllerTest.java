package com.email.sender.emailsender.controller;

import com.email.sender.emailsender.service.ProviderAService;
import com.email.sender.emailsender.service.ProviderBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProviderBController.class)
public class ProviderBControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProviderBService service;

    @Test
    public void testForgotPassword() throws Exception {
        given(service.newsLetter(anyString(), anyString(), anyString(), anyString())).willReturn(true);

        mvc.perform(get("/provider-b/weekly-news-letter/{firstName}/{lastName}/{newsletterDate}/{newsletter}", "testName", "lastName", "10-03-2019", "newsletter")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}