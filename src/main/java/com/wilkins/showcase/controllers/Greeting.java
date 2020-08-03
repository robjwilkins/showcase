package com.wilkins.showcase.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value(staticConstructor = "of")
@With
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Greeting {
    // private and final modifiers added due to @Value annotation
    String salutation;
    String name;
}
