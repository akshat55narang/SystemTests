Feature: Login Api

  @api
  Scenario: successful login
    Given I login a user with email "eve.holt@reqres.in" and password "cityslicka"
    Then I should receive a status code "200" and a user id for login api

  @api
  Scenario: login with incorrect password
    Given I login a user with email "eve.holt@reqres.in" and password "incorrectpassword"
    Then I should receive a status code "200" and a user id for login api

  @api
  Scenario: login without password
    Given I login a user with email "peter@klaven" and no password
    Then I should receive a status code "400" and error with "Missing password" for login api
