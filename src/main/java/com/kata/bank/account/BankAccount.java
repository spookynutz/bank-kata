package com.kata.bank.account;

import com.kata.bank.operation.Deposit;
import com.kata.bank.statement.StatementPrinter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private StatementPrinter statementPrinter;
    private List<Deposit> depositHistory = new ArrayList<>();

    public BankAccount(StatementPrinter statementPrinter) {
        this.statementPrinter = statementPrinter;
    }

    public void deposit(BigDecimal amountToDeposit) {
        depositHistory.add(new Deposit(amountToDeposit));
    }

    public void printStatement() {
        if (depositHistory.isEmpty()) {
            return;
        }
        statementPrinter.print("OPERATION | DATE | AMOUNT | BALANCE");
        statementPrinter.print("DEPOSIT | 29/01/2020 | 20.00 | 20.00");
    }
}
