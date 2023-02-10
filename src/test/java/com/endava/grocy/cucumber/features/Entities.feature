@BackEnd
Feature: Make different CRUD actions for entities

  @SmokeTest @TestRunner
  Scenario: Create location entity
    Given basic config for the request is created with base path: api
    When user creates an entity of type LOCATION
    Then user receives code 200