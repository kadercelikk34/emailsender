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

    /**
     * init metodu ile email account oluşturulur.
     * @param account
     * @param password
     * @param endPointUrl
     * @return
     */
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

    /**
     * send metodu mail için gerekli parametrelerin set edildiği ve
     * emailin  kuyruğa eklenmesini sağlar
     * @param to  emailin kime gönderileceği
     * @param cc  emaile bilgilendirecek kişi
     * @param subject email başlığı
     * @param content  email içeriği
     * @return
     */
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

    /**
     * sendMail() metodu kuyrukdaki mailleri alıp 60 sn dolmamış ise ve
     * mail gönderme işlemi 3 kez denenmediyse(version)
     * mail gönderme işlemi gercekleştirilir.
     * mail gönderme işlemi başarılı ise status true yapılır ,
     * başarısız ise status false cekilip version artırılır.
     */
    @Async
    protected void sendMail() {
        for(EmailSend emailSend: MyContext.queue()) {
            long between = ChronoUnit.SECONDS.between(emailSend.getLocalTime(), LocalTime.now());
            if (!emailSend.isStatus() && between < 60 && emailSend.getVersion() < 3) {
                try {
                    mailUtil.sendMail("ets.challange@gmail.com", emailSend.getTo(), emailSend.getCc(), emailSend.getSubject(), emailSend.getContent());
                    emailSend.setStatus(true);
                } catch (Exception e){
                    emailSend.setVersion(emailSend.getVersion() +1 );
                    emailSend.setStatus(false);
                }
            }
        }
    }
}
