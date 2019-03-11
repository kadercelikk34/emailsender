package com.email.sender.emailsender.utils;

import com.email.sender.emailsender.models.EmailInit;
import com.email.sender.emailsender.models.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Component
public class ProviderEmailUtils {

    @Autowired
    MailUtil mailUtil;

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
        emailSend.setLocalTime(LocalTime.now());
        MyContext.add(emailSend);

        sendMail();
        return true;
    }

    @Async
    protected void sendMail() {
        for(EmailSend emailSend: MyContext.queue()) {
            long between = ChronoUnit.SECONDS.between(emailSend.getLocalTime(), LocalTime.now());
            if (between > 60 || emailSend.getVersion() < 3) {
                try {
                    mailUtil.sendMail("ets.challange@gmail.com", emailSend.getTo(), emailSend.getCc(), emailSend.getSubject(), emailSend.getContent());
                } catch (Exception e){
                    emailSend.setVersion(emailSend.getVersion() +1 );
                }
            }
        }
    }
}
