@BackEnd
Feature: Feature to the the stock journal redirect

  @SmokeTest
  Scenario: Check redirect to stock journal
    Given basic config for the request is created with base path: /
    When user navigates to /stockjournal endpoint
    Then user receives code 200
#    And user is on page "pageName"