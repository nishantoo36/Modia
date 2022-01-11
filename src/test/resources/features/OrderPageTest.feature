Feature: Order page tests

  @addToCart
  Scenario: Do Cart without authorisation
    Given Home page without authorizations
    When User click on product menu
    Then verify that product submenu is appearing
    When User Select Games categories
    Then User should navigate to product list page
    When User select last product from game list
    Then verify that last product added in cart
    When User click on 1 product from list
    Then Verify that user is on the same product detail page
    And Verify that user is able to see 4 product screenshots
    When User select 2 quantity
    And Click on Add to cart button
    Then verify the cart data
    When User navigate to cart page
    Then Verify all cart details on cart page
    When User click on the checkout button
    Then User should navigate to login page
    When User click on the registration button
    Then User should navigate to Sign up page
    When User fill all valid data and do registration
    Then User should navigate to order confirmation page
    When User click on confirm order button
    Then User should navigate to success screen
    When User click on confirm button from success screen
    Then User should navigate to my account page
    And User is able to see order status as "Pending"



