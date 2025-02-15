package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Greeting;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsGreetingService implements GreetingService {
    private final JmsMessagingTemplate jmsTemplate;

    public JmsGreetingService(JmsMessagingTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Greeting send(Greeting greeting) {
        jmsTemplate.convertAndSend("greetings", greeting);
        return greeting;
    }
}
