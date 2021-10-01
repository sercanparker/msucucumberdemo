Feature: Sale transaction should be completed via api.

  Scenario: Sale transaction is completed successfully by api user
    Given there is merchant api user
    When api user sends sale transaction
    Then sale transaction is completed successfully