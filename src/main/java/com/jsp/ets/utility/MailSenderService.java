package com.jsp.ets.utility;

import com.jsp.ets.exception.EmailSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MailSenderService {

    private JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(MailSenderService.class);

    @Async
    public void sendMail(MessageModel messageModel) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(messageModel.getTo());
            helper.setText(messageModel.getText(), true);
            helper.setSubject(messageModel.getSubject());
            helper.setSentDate(messageModel.getSendDate());

            logger.info("Sending email to: {}", messageModel.getTo());
            logger.info("Subject: {}", messageModel.getSubject());
            logger.info("Sent date: {}", messageModel.getSendDate());

        } catch (MessagingException e) {
            logger.error("Error sending email to: {}, subject: {}", messageModel.getTo(), messageModel.getSubject(), e);
            throw new EmailSendException("Failed to send email to " + messageModel.getTo() + " with subject: " + messageModel.getSubject());
        }

        mailSender.send(mimeMessage);
        logger.info("Email successfully sent to: {}", messageModel.getTo());
    }


}
