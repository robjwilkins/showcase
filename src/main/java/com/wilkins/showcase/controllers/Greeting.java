package com.wilkins.showcase.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;

@Value
@AllArgsConstructor(onConstructor_={@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)})
@With
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Greeting {
    // private and final modifiers added due to @Value annotation
    String salutation;
    String name;
}
