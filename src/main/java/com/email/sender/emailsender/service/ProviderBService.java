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
public class ProviderBService {
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String END_POINT = "endPoint";

    @Autowired
    private ProviderEmailUtils providerEmailUtils;
    @Autowired
    private EmailService emailService;

    /**
     * newsLetter metodu ile alınan parametreler ile örnek email oluştrulur.
     * Örnek Newsletter
     * ----------------
     * Subject: Firstname, NewsletterDate tarihli bültenimizi kaçırma
     * Body: Merhaba Firstname Lastname;
     *       Newsletter
     * @param firstName
     * @param lastName
     * @param newsletterDate
     * @param newsletter
     * @return
     */
    public boolean newsLetter(String firstName, String lastName, String newsletterDate, String newsletter){

        EmailInit init = providerEmailUtils.init(ACCOUNT, PASSWORD, END_POINT);
        if (init != null) {
            List<String> to = new ArrayList<>();
            List<String> cc = new ArrayList<>();
            String subject = firstName +" "+ newsletterDate +"  tarihli bültenimizi kaçırma";
            String content = "Merhaba "+firstName +" "+lastName +" /n "+ newsletter;
            emailService.saveEmail(firstName, lastName, content, END_POINT, EmailType.WEEKLY_NEWS_LETTER);
            return providerEmailUtils.send(to, cc, subject, content);
        }

        return false;
    }

}
