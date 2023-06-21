Feature: This feature covers all the API related scenario

  Background:
    Given a JWT is generated

  @api
  Scenario: Adding an employee
    Given a request is prepared to created an employee
    When a POST call is made to create an employee
    Then the status code for the created employee is 201


