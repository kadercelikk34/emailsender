package com.email.sender.emailsender.service;

import com.email.sender.emailsender.models.Email;
import com.email.sender.emailsender.models.EmailInit;
import com.email.sender.emailsender.models.EmailType;
import com.email.sender.emailsender.repository.EmailRepository;
import com.email.sender.emailsender.utils.ProviderEmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderAService {

    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String END_POINT = "endPoint";

    @Autowired
    private ProviderEmailUtils providerEmailUtils;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private EmailService emailService;

    /**
     * createWelcome metodu ile alınan parametreler ile email oluşturulur.
     * Örnek Welcome
     * -------------
     * Subject: Hoşgeldiniz!
     * Body: Sayın Firstname Lastname, aramıza hoşgeldiniz.
     * @param firstName
     * @param lastName
     * @return
     */
    public boolean createWelcome(String firstName, String lastName){
        EmailInit init = providerEmailUtils.init(ACCOUNT, PASSWORD, END_POINT);
        if (init != null) {
            List<String> to = new ArrayList<>();
            List<String> cc = new ArrayList<>();
            String subject = "Hoşgeldiniz!";
            String content = "Sayın "+ firstName +" "+ lastName +", aramıza hoşgeldiniz";
            emailService.saveEmail(firstName, lastName, content, END_POINT, EmailType.WELCOME);
            return providerEmailUtils.send(to, cc, subject, content);
        }

        return false;
    }

    /**
     * forgotPassword metodu ile örnek email cıktısı oluşturulur.
     * Örnek Forgot Password
     * ---------------------
     * Subject: Şifre yenileme
     * Body: Şifrenizi yenilemek için aşağıdaki linke tıklayınız
     *       PasswordResetUrl
     * @param passwordResetUrl
     * @return
     */
    public boolean forgotPassword(String passwordResetUrl){
        if (passwordResetUrl != null) {
            String subject = "Şifre yenileme";
            String content ="Şifrenizi yenilemek için aşağıdaki linke tıklayınız /n"+
                    passwordResetUrl;
            List<String> to = new ArrayList<>();
            List<String> cc = new ArrayList<>();
            emailService.saveEmail("", "", content, END_POINT, EmailType.FORGOT_PASSWORD);
            return providerEmailUtils.send(to, cc, subject, content);
        }
        return false;

    }
}
