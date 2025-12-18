Feature: New Patients Registration


  @REGISTER
  Scenario: New Patient for first time
    Given the user is on the mount sinai hospital website
    When user clicks on register now button
    And user fills his information
    And user clicks on Register Patient button
    Then user should see a successful message saying "Welcome" plus the name of employee registered


