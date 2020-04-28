Feature: Purchase a t-shirt


  @e2e
  Scenario: Verify user is redirected to Contact Us page
    Given I'm on the Home Page
    When user hovers on "Women" tab and click on "T-shirts"
    Then user should be on the "T-shirts" page
    When user adds item "Faded Short Sleeve T-shirts" to the cart
    Then a popup with successful addition of products "Faded Short Sleeve T-shirts" to cart should appear
    When user click on the button "Proceed to checkout"
    Then user should be on the "SHOPPING-CART SUMMARY" page
    And the quantity of item "Faded Short Sleeve T-shirts" should be "1"
    When user clicks on the button "Proceed to checkout"
    Then user is redirected to view Sign In form
    When the user enters a valid email in the create account section
    Then the user should be redirected to account creation page
    When the user enters "First name" as "TestUser" in personal information
    And the user enters "Last name" as "TestUser" in personal information
    And the user enters "Email" as "TestUser" in personal information
    And the user enters "Password" as "TestUser" in personal information
    And the user enters "First name" as "TestUser" in address
    And the user "Last name" as "TestUser" in address
    And the user "Address" as "TestHouseNumber 5, Test Street, 11111" in address
    And the user "City" as "Test City" in address
    And the user "State" as "Test City" in address
    And the user "Postal Code" as "1111AA" in address
    And the user "City" as "Test City" in address
    And the user "Mobile phone" as "999999999" in address
    And the user "Assign an address alias for future reference" as "Office" in address
    When user clicks on the button "Register"

