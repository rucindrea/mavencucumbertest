#noinspection CucumberUndefinedStep

Feature: Customer Transfers Fund
  As a customer with access to Transfer Funds Online,
  I want to be able to transfer funds from my account
  So that I can easily send money to my friends and family

  Background:
    Given I am on the Transfer Funds Online Page

  Scenario Outline: Valid Payee
    Given I have <an initial balance> in my account
    When I transfer <a certain amount> to "<someone>"
    Then I should see "<a message>"
    And I should have <some remaining balance> left in my account

  Examples:
    | an initial balance | a certain amount | someone | some remaining balance | a message                                                         |
    | 123456789          | 12               | Jim     | 123456777              | $12 transferred successfully to Jim!!                             |
    | 2000               | 100              | Jack    | 2000                   | Transfer failed!! 'Jack' is not registered in your List of Payees |
    | 2000               | 1000000          | Tim     | 2000                   | Transfer failed!! account cannot be overdrawn                     |



