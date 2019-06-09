Feature: Resources Api


  Scenario: Verify api for list of resources
    Given I call the register api
    Then I should receive a status code "200" and the response body should contain multiple resource ids


  Scenario: Verify api for single resource
    Given I call the register api for a single id with id "2"
    Then I should receive a status code "200" and the response body should contain single user id


  Scenario: Verify api for single not found resource
    Given I call the register api for a single id with id "23"
    Then I should receive a status code "404" and the response body should be empty