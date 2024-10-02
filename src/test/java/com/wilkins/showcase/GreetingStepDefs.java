package com.wilkins.showcase;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingStepDefs extends SpringIntegrationStepDefs {

    JsonGreeting greeting;

    @When("api call to {string}")
    public void apiCallToGreeting(String path) {
        greeting = getRestTemplate().getForObject(getBaseUrl() + path, JsonGreeting.class);
    }

    @Then("{string} greeting is returned")
    public void greetingIsReturned(String greetingMessage) {
        assertThat(greeting.message()).isEqualTo(greetingMessage);
    }

    private record JsonGreeting(String salutation, String name) {
        String message() {
            return salutation + " " + name;
        }
    }
}
