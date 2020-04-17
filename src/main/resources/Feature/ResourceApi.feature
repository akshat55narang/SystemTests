Feature: Resources Api

  @resourceapi, @api
  Scenario: Verify api for list of resources
    Given I list all the resources
    Then I should receive a status code "200" and multiple resource ids for resource api

  @resourceapi, @api
  Scenario: Verify api for single resource
    Given I list the resource with id "2"
    Then I should receive a status code "200" and a single resource id for resource api


  @resourceapi, @api
  Scenario: Verify api for single not found resource
    Given I list the resource with id "23"
    Then I should receive a status code "404" and no body for resource api
