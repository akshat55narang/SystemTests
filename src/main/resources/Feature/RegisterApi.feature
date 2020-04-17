Feature: Register Api

  @api
  Scenario: successful registration
    Given I register a user with email "eve.holt@reqres.in" and password "pistol"
    Then I should receive a status code "200" and a user id for register api


  @api
  Scenario: unsuccessful registration
    Given I register a user with email "sydney@fife"
    Then I should receive a status code "400" and error with "Missing password" for register api
