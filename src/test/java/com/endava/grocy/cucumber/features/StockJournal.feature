@BackEnd
Feature: Feature to the the stock journal redirect

  @SmokeTest
  Scenario: Check redirect to stock journal
    Given Basic config for the request is created with base path: /
    When User navigates to /stockjournal endpoint
    Then User receives code 200
#    And user is on page "pageName"

  @TestRunner
  Scenario: Check product amount on Stock Overview page
    Given User logs in with the following credentials
      | admin | admin |
