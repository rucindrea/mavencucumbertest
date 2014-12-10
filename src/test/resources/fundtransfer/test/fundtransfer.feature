Feature: Customer Transfers Fund
    As a customer with access to Transfer Funds Online,
    I want to be able to transfer funds from my account
    So that I can easily send money to my friends and family

    Scenario: Valid Payee
        Given I am on Fund Transfer Page
        When I enter "Jim" as payee name
        And I enter "100" as amount
        And I confirm the transfer
        Then the money is transfered with "$100 transferred successfully to Jim!!" message

    Scenario: Invalid Payee
        Given I am on Fund Transfer Page
        When I enter "Jim" as payee name
        And I enter "100" as amount
        And I confirm the transfer
        Then I see a failure message "Transfer failed!! 'Jack' is not registered in your List of Payees" displayed

    Scenario: Account is overdrawn past the overdraft limit
        Given I am on Fund Transfer Page
        When I enter "Jim" as payee name
        And I enter "100" as amount
        And I confirm the transfer
        Then I see a failure message "Transfer failed!! account cannot be overdrawn" displayed




