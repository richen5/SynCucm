Feature: Adding the employee in HRMS Application

  @regression @boss
  Scenario: Adding one employee from feature file
    #Given user is navigated to HRMS application
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on add employee option
    And user enters firstName middleName and lastName
    And user clicks on save button
    Then employee added successfully

    @test
    Scenario: Adding one employee using cucumber feature file
      When user enters valid admin credentials
      And user clicks on login button
      Then admin user is successfully logged in
      When user clicks on PIM option
      And user clicks on add employee option
      And user enters "Ostap" "Ostapovich" and "Bandera"
      And user clicks on save button
      Then employee added successfully

      Scenario Outline: Adding multiple employees
        When user enters valid admin credentials
        And user clicks on login button
        Then admin user is successfully logged in
        When user clicks on PIM option
        And user clicks on add employee option