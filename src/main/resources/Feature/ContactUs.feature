Feature: Contact Us

  Background:
    Given I'm on the Home Page

  @ui, @contact
  Scenario: Verify user is redirected to Contact Us page
    When User clicks on the button "Contact us"
    Then the user should be redirected to Contact us page

  @ui, @contact
  Scenario: Verify user is able to send message
    Given User is on the contact page
    When user selects "Customer service" as heading
    And user enters the "email address"
    And user enters the "order reference"
    And user attaches the file "test.test"
    And user enters the default message
    And User clicks on the button "Send"
    Then the message should be sent successfully

