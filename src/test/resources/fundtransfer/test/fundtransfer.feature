@fundransfer
Feature: Customer Transfer's Fund
    As a customer,
    I want to transfer funds
    so that I can send money to my friends and family

@Jim
Scenario: Valid Payee Jim pays 100
    Given the user is on Fund Transfer View
    When he enters "Jim" as payee name
    And he enters "100" as amount
    And he Submits request for Fund Transfer
    Then ensure the fund transfer is complete with "$100 transferred successfully to Jim!!" message

Scenario: Valid Payee Jim pays 10
    Given the user is on Fund Transfer View
    When he enters "Jim" as payee name
    And he enters "10" as amount
    And he Submits request for Fund Transfer
    Then ensure the fund transfer is complete with "$10 transferred successfully to Jim!!" message


Scenario: Valid Payee Jim pays 20
    Given the user is on Fund Transfer View
    When he enters "Jim" as payee name
    And he enters "20" as amount
    And he Submits request for Fund Transfer
    Then ensure the fund transfer is complete with "$20 transferred successfully to Jim!!" message

