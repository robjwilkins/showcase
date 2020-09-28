package com.wilkins.showcase.controllers;

import lombok.Value;
import lombok.With;

import java.io.Serializable;

@Value(staticConstructor = "of")
@With
public class Greeting implements Serializable {

    private static final long serialVersionUID = -2464792326773658903L;
    // private and final modifiers added by @Value annotation
    String salutation;
    String name;
}
