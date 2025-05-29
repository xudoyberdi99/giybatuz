package api.giybat.uz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingService {
    @Value("${spring.mail.username}")
     private String fromAccount;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendRegistrationEmail(String email,Integer profileId    ) {
        String subject = "Registration Confirmation";
        String body = " Emailni tasdiqlang linkni bosing "+ "http://localhost:8080/auth/registration/verification/" + profileId;
        sendEmail(email, subject, body);
    }

    private void sendEmail(String email, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAccount);
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }
}
