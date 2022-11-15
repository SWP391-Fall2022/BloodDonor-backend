package swp.medichor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void send(String from, String to, String subject, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setFrom(from);
            mailSender.send(mimeMessage);
            System.out.println("Send mail successfully");
        }
        catch(Exception e) {
            System.out.println("Failed to send email");
            throw new IllegalStateException("Failed to send email");
        }
    }
}
