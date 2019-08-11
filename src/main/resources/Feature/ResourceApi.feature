Feature: Resources Api

  @resourceapi
  Scenario: Verify api for list of resources
    When I call the register api
    Then I should receive a status code "200" for register api
    And the response body should contain multiple resource ids

  @resourceapi
  Scenario: Verify api for single resource
    When I call the register api for a single id with id "2"
    Then I should receive a status code "200" for register api
    And the response body should contain single resource id

  @resourceapi
  Scenario: Verify api for single not found resource
    When I call the register api for a single id with id "23"
    Then I should receive a status code "404" for register api
    And the response body should not contain any resource ids
