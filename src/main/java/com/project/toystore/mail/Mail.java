package com.project.toystore.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@ComponentScan(basePackages = "com.project.toystore.mail")
public class Mail {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String sendAddress, String subject, String content){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(sendAddress);
            helper.setSubject(subject);
            helper.setText(content);
            mailSender.send(message);

        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
