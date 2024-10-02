package com.wilkins.showcase;

import io.cucumber.java.en.When;

public class GreetingStepDefs extends SpringIntegrationStepDefs {
    @When("api call to \\/greetings")
    public void apiCallToGreeting() {
        var greeting = getRestTemplate().getForObject(getBaseUrl() + "/greetings", String.class);
    }
}
