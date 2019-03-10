package com.email.sender.emailsender.utils;

import com.email.sender.emailsender.models.EmailInit;
import com.email.sender.emailsender.models.EmailSend;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProviderEmailUtils {

    public  EmailInit init(String account, String password, String endPointUrl ){
        if (account == null || password == null || endPointUrl == null) {
            return null;
        }

        EmailInit emailInit =new EmailInit();
        emailInit.setAccount(account);
        emailInit.setPassword(password);
        emailInit.setEndPointUrl(endPointUrl);
        return emailInit;
    }

    public boolean send(List<String> to, List<String> cc, String subject, String content){
        EmailSend emailSend =new EmailSend();
        emailSend.setTo(to);
        emailSend.setCc(cc);
        emailSend.setSubject(subject);
        emailSend.setContent(content);
        emailSend.setVersion(1);

        //Email send yapılır send başarısız ise version arttırılır ve tekrar denenir
        // eğer version 3 den büyük ise return false edilir

        return true;
    }
}
