Feature: Adding employees

  Background:
    Given user has navigated to the website
    And the user is successfully logged in



  @addEmployee
    Scenario: Adding an employee in HRM system
    When user clicks on PIM
    And user clicks on ADD button
    When user enters username, middlename,password
    And user clicks on SAVE button
    Then user should see an confirmation message saying "employee added successfully"

