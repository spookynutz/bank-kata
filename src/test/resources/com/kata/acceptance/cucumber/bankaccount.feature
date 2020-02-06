Feature: Bank account operations

  Scenario: A single deposit
    Given a client makes a deposit of 1000 on 04/02/2020
    When he requests to print his account statement
    Then he would see
      """
      OPERATION | DATE | AMOUNT | BALANCE
      DEPOSIT | 04/02/2020 | 1000.00 | 1000.00
      """

  Scenario: A deposit and a withdrawal
    Given a client makes a deposit of 1000 on 04/02/2020
    And a withdrawal of 200 on 05/02/2020
    When he requests to print his account statement
    Then he would see
      """
      OPERATION | DATE | AMOUNT | BALANCE
      DEPOSIT | 04/02/2020 | 1000.00 | 1000.00
      WITHDRAW | 05/02/2020 | 200.00 | 800.00
      """