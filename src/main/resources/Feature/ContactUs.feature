Feature: Contact Us


  @contactus
  Scenario: Verify user is redirected to Contact Us page
    Given I'm on the Home Page
    When User clicks on the button "Contact us"
    Then the user should be redirected to Contact us page

  @contactus
  Scenario: Verify user is able to send message to customer service
    Given User is on the contact page
    When user selects "Customer service" as heading
    And user enters the email "test@testapp.com"
    And user enters the order reference "123456"
    And user attaches the file "test.test"
    And user enters the message "This is a test message for customer service"
    And User clicks on the button "Send"
    Then the message should be sent successfully

  @contactus
  Scenario: Verify user is able to send message
    Given User is on the contact page
    When user selects "Webmaster" as heading
    And user enters the email "test@testapp.com"
    And user enters the order reference "123456"
    And user attaches the file "test.test"
    And user enters the message "This is a test message for webmaster"
    And User clicks on the button "Send"
    Then the message should be sent successfully


