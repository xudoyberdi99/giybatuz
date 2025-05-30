package api.giybat.uz.service;

import api.giybat.uz.util.JwtUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingService {
    @Value("${spring.mail.username}")
     private String fromAccount;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendRegistrationEmail(String email,Integer profileId    ) {
        String subject = "Registration Confirmation";
        String body="<body>\n" +
                "    <h1>Compilite Registration</h1>\n" +
                "    <p>Salom Yaxshimisz</p>\n" +
                "    <p> linkni bosish orqali siz tasdiqlaysz email sizga tegishli ekanligini\n" +
                "        <a href=\"http://localhost:8080/auth/registration/verification/%s\"\n" +
                "           target=\"_blank\"\n" +
                "           style=\"background-color: green; padding: 10px; margin: 5px;\">\n" +
                "            click here\n" +
                "        </a>\n" +
                "    </p>\n" +
                "\n" +
                "</body>";
        body=String.format(body, JwtUtil.encode(profileId));

      //  String body = " Emailni tasdiqlang linkni bosing "+ "http://localhost:8080/auth/registration/verification/" + profileId;
        sendMimeEmail(email, subject, body);
    }
    private void sendMimeEmail(String email, String subject, String body) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setFrom(fromAccount);

            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(msg);
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    private void sendSimpleEmail(String email, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAccount);
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }
}
