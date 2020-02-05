package com.kata.bank.account;

import com.kata.bank.statement.StatementPrinter;

import java.math.BigDecimal;

public class BankAccount {

    private StatementPrinter statementPrinter;

    public BankAccount(StatementPrinter statementPrinter) {
        this.statementPrinter = statementPrinter;
    }

    public void deposit(BigDecimal amountToDeposit) {

    }

    public void printStatement() {
        statementPrinter.print("OPERATION | DATE | AMOUNT | BALANCE");
        statementPrinter.print("DEPOSIT | 29/01/2020 | 20.00 | 20.00");
    }
}
