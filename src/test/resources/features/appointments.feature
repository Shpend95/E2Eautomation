Feature: Appointment Flow


  @2_newAppointment @smoke
  Scenario: Scheduling an appointment
    Given the user is on logged in
    When user clicks on appointments
    And user selects doctor,date,time
    And user adds note
    And user clicks on book appointment button
    Then user should see a successful message that appointment has been booked