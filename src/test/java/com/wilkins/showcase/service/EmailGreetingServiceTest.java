package com.wilkins.showcase.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EmailGreetingServiceTest {

    GreetingService underTest;
    MailSender mailSender = mock(MailSender.class);

    @BeforeEach
    void beforeEach() {
        underTest = new EmailGreetingService(mailSender);
    }

    @Test
    void canEmailGreeting() {
        var expectedMessage = new SimpleMailMessage();
        expectedMessage.setFrom("showcase@cleancoders.net");
        expectedMessage.setTo("test@cleancoders.net");
        expectedMessage.setSubject("test email");
        expectedMessage.setText("%s %s".formatted("hello", "world"));

        underTest.sendGreeting("hello", "world");

        verify(mailSender).send(expectedMessage);
    }
}