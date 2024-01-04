package pbs.edu.CarRent.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pbs.edu.CarRent.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMessage(String from, String name, String subject, String content) {
        MimeMessage mail=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            //<>Dodać adres mail
            helper.setTo("<TO>");
            helper.setReplyTo("<TO>");
            //<>
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(content, true);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }
}
