Feature: User Api

  @api
  Scenario: List multiple users
    Given I call the users api with page size "2"
    Then I should receive a status code "200" and the response body should contain multiple user ids

  @api
  Scenario: Single User
    Given I call the users api for a single user
    Then I should receive a status code "200" and the response body should contain single user id