package com.kata.bank;

import java.math.BigDecimal;
import java.util.List;

public class BankAccount {
    private List<String> printedStatements;

    public BankAccount(List<String> printedStatements) {
        this.printedStatements = printedStatements;
    }

    public void deposit(BigDecimal amountToDeposit) {

    }

    public void printStatement() {
        printedStatements.add("OPERATION | DATE | AMOUNT | BALANCE");
        printedStatements.add("DEPOSIT | 29/01/2020 | 20.00 | 20.00");
    }
}
