package com.email.sender.emailsender.service;

import com.email.sender.emailsender.models.EmailInit;
import com.email.sender.emailsender.models.EmailType;
import com.email.sender.emailsender.utils.ProviderEmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProviderAServiceTest {

    @Mock
    ProviderEmailUtils providerEmailUtils;
    @Mock
    EmailService emailService;

    @InjectMocks
    ProviderAService providerAService;

    @Test
    public void testCreateWelcomeNullInit() {
        when(providerEmailUtils.init("testAccount", "testPass","testEndPoint")).thenReturn(null);

        providerAService.createWelcome("testFirstName", "testLastName");
    }

    @Test
    public void testCreateWelcomeInit() {
        EmailInit emailInit = new EmailInit();
        emailInit.setAccount("testAccount");
        emailInit.setEndPointUrl("testEndPointUrl");
        emailInit.setPassword("testPassword");
        emailInit.setPasswordResetUrl("testResetUrl");

        when(providerEmailUtils.init("testAccount", "testPass","testEndPoint")).thenReturn(emailInit);
        when(emailService.saveEmail("test", "test", "test", "test", EmailType.WELCOME)).thenReturn(true);
        when(providerEmailUtils.send(anyList(), anyList(), anyString(),anyString())).thenReturn(true);

        providerAService.createWelcome("testFirstName", "testLastName");
    }


    @Test
    public void testForgotPasswordNullUrl() {
        providerAService.forgotPassword(null);
    }

    @Test
    public void testForgotPassword() {
        when(emailService.saveEmail("test", "test", "test", "test", EmailType.FORGOT_PASSWORD)).thenReturn(true);
        when(providerEmailUtils.send(anyList(), anyList(), anyString(),anyString())).thenReturn(true);
        providerAService.forgotPassword("testpasswordResetUrl");

    }

}