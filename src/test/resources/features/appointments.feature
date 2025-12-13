Feature: Appointment Flow


  @newAppointment @smoke
  Scenario: Scheduling an appointment
    Given the user is on dashboard page
    When user clicks on  the book appointment button
    And user selects patient,doctor,date,time
    And user adds note
    And user clicks on book appointment button
    Then user should see a successful message that appointment has been booked