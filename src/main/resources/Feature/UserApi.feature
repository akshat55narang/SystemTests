Feature: User Api

  @api
  Scenario: Verify api for multiple users
    Given I call the users api with page size "2"
    Then I should receive a status code "200" and the response body should contain multiple user ids

  @api
  Scenario: Verify api for single user
    Given I call the users api for a single user with id "2"
    Then I should receive a status code "200" and the response body should contain single user id

  @api
  Scenario: Verify api for single not found user
    Given I call the users api for a single user with id "23"
    Then I should receive a status code "404" and the response body should be empty

  @api
  Scenario: Verify api for creating a user
    Given I call the users api to create user with name "morpheus" and job "leader"
    Then I should receive a status code "2011" and the response body contain user with name "morpheus" and  job "leader"

  @api
  Scenario: Verify api for updating a user using PUT HTTP method
    Given I call the users api to update user with id "2" name "name" and job "job" using "PUT" HTTP method
    Then I should receive a status code "200" and the response body contain user with name "name" and  job "job"

  @api
  Scenario: Verify api for updating a user using PATCH HTTP method
    Given I call the users api to update user with id "2" name "name" and job "job" using "PATCH" HTTP method
    Then I should receive a status code "201" and the response body contain user with name "name" and  job "job"

  @api
  Scenario: Verify api for deleting a user
    Given I call the users api to delete user with id "2"
    Then I should receive a status code "204" and an empty response body

