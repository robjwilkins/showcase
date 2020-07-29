package com.wilkins.showcase.controllers;

import lombok.Value;
import lombok.With;

@Value(staticConstructor = "of")
@With
public class Greeting {
    // private and final modifiers added due to @Value annotation
    String salutation;
    String name;
}
