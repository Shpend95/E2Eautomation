Feature: Login related scenarios

  @Login @smoke
  Scenario: Valid admin login
    Given user is navigated to the website
    When user enters valid username and password
    And user clicks on login button
    Then user is successfully logged in