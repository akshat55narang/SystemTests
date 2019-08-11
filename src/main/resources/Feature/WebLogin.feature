Feature: Login

  Scenario: Authentication UI when user clicks on Sign In button
    Given User clicks on the button "Sign in"
    Then user is redirected to view Sign In form
    When the user enters a valid email in the create account section
    Then the user should be redirected to account creation page

