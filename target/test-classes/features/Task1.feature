Feature: Testing Enter a number page
  As a test engineer
  I want to see correct and incorrect behaviour of the Enter a number page

  Scenario Outline: Wrong number or text is entered
    Given I am on enter a number page
    When I enter incorrect number: "<number>"
    And I click submit
    Then I see caution: "<message>"

    Examples:
      | number  | message               |
      | 40      | Number is too small   |
      | 110     | Number is too big     |
      | ten     | Please enter a number |

  Scenario: Correct number is entered
    Given I am on enter a number page
    When I enter a correct number: 64
    And I click submit
    Then I see the square root of the number in a popup box

