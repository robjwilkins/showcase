Feature: Greeting can be retrieved

  Scenario: Default greeting returned
    When api call to "/greetings"
    Then "hello world" greeting is returned

  Scenario: Special greeting returned
    When api call to "/greetings?salutation=Morning&name=Robin"
    Then "Morning Robin" greeting is returned
