Feature: Adding the employee in HRMS Application

  Background:
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on add employee option

  @regression
  Scenario: Adding one employee from feature file
    #Given user is navigated to HRMS application
    And user enters firstName middleName and lastName
    And user clicks on save button
    Then employee added successfully

    @123
    Scenario: Adding one employee using cucumber feature file
      And user enters "Ostap" "Ostapovich" and "Bandera"
      And user clicks on save button
      Then employee added successfully

      @screen
      Scenario Outline: Adding multiple employees
        And user provides "<firstName>" "<middleName>" and "<lastName>"
        And user clicks on save button
        Then employee added successfully
        Examples:
        |firstName|middleName|lastName|
        |Stepan    |UA        |Bandera |
        |Ivan      |UA        |Mazepa  |
        |Bogdan    |UA        |Khmelnytskyi|
        |Ivan      |UA        |Franko      |

        @test @datatable
        Scenario: Add employee using data table
          When user provides multiple employee data and verify they are added
            |firstName|middleName|lastName|
            |Stepan    |UA        |Bandera |
            |Ivan      |UA        |Mazepa  |
            |Ivan      |UA        |Franko  |

          @excel
          Scenario: Adding multiple employees from excel file
            When user add multiple employees from excel file using "EmployeeData" sheet and verify the user added
