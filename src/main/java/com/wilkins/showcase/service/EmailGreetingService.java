package com.wilkins.showcase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public record EmailGreetingService(MailSender mailSender) implements GreetingService {
    private static final Logger log = LoggerFactory.getLogger(EmailGreetingService.class);

    @Override
    public void sendGreeting(String salutation, String name) {
        log.info("Sending email");
        var message = new SimpleMailMessage();
        message.setFrom("showcase@cleancoders.net");
        message.setTo("test@cleancoders.net");
        message.setSubject("test email");
        message.setText("%s %s".formatted(salutation, name));
        mailSender.send(message);
        log.info("Email sent");
    }
}
