Feature: Role-Based Login

  @PatientLogin
  Scenario: Valid Patient login
    Given patient is navigated to the website
    When patient enters valid email and password
    And patient clicks on login button
    Then patient is successfully logged in

    @DoctorLogin
    Scenario: Valid Doctor login
      Given doctor is navigated to the website
      When doctor enters valid email and password
      And doctor clicks on login button
      Then doctor is successfully logged in