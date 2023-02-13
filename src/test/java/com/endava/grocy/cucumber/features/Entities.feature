@BackEnd
Feature: Make different CRUD actions for entities

  @SmokeTest
  Scenario: Create location entity
    Given Basic config for the request is created with base path: api
    When User creates an entity of type LOCATION
    Then User receives code 200