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
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

    public boolean saveEmail(String firstName, String lastName, String content, String endPointUrl, EmailType type) {
        Email email = new Email();
        email.setAccount(firstName + " " + lastName);
        email.setCreatedDate(LocalDate.now().toString());
        email.setEndPointUrl(endPointUrl);
        email.setNewsletter(content);
        email.setEmailType(type);
        emailRepository.save(email);

        return true;
    }
}
