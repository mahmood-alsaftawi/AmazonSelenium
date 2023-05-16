Feature: GoToAmazon

  Scenario: amazon.ca page loads properly
    Given I launch chrome browser
    When I navigate to amazon.ca
    Then verify amazon.ca logo is present
    And close browser
