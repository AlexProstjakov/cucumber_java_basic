Feature: Testing of "People with jobs" page
  As a test engineer
  I want to test functionality of the web page

  Background:
    Given I am on People with jobs page

  @Outline1
  Scenario Outline: Add new person
    When I click Add Person
    And I add new person with "<name>" and "<job>"
    And I click Add Person button
    Then I want to check that there are 4 people on the list
    And I want to see a person in the list with "<name>" and "<job>"
    Then I click reset list button
    And I want to check that there are 3 people on the list
    And I want to see added person removed


  Examples:
    | name          | job     |
    | Alex          | Pilot   |
    | Karina        | Tester  |
    | Anna-Marija   | Teacher |

  @Scenario2
  Scenario: Edit a person
    When I click Edit first person
    And I replace existing with new person with name "Alex" and job "Pilot"
    Then I want to see edited person in a list with name "Alex" and job "Pilot"
    Then I click reset list button
    And I want to see first person with name "Mike" and job "Web Designer"

  @Scenario3
  Scenario: Remove a person
    When I click Remove person button
    Then I want to see this person removed
    And I want to check that there are 2 people on the list
    Then I click reset list button
    And I want to check that there are 3 people on the list
    And  I want to see first person with name "Mike" and job "Web Designer"

  @Scenario4
  Scenario Outline: Check that clear button on adding a user works correctly
    When I click Add Person
    And I add new person with "<name>" and "<job>"
    And I click Clear all fields button
    And I click Add Person button
    Then I want to see added person in the list with empty name and job

  Examples:
    | name          | job     |
    | Karina        | Tester  |

