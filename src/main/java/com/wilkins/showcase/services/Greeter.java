package com.wilkins.showcase.services;

import com.wilkins.showcase.controllers.Greeting;
import org.springframework.stereotype.Component;

@Component
public class Greeter {

    public String greet(Greeting greeting) {
        return greeting.getSalutation() + " " + greeting.getName();
    }
}
