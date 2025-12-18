Feature: Role-Based Login

  @PatientLogin @smoke
  Scenario: Valid Patient login
    Given patient is navigated to the website
    When patient enters valid email and password
    And patient clicks on login button
    Then patient is successfully logged in
