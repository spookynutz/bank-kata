package com.kata.bank.account;

import com.kata.bank.operation.OperationHistory;
import com.kata.bank.operation.OperationType;
import com.kata.bank.statement.StatementPrinter;

import java.math.BigDecimal;

public class BankAccount {

    private final OperationHistory operationHistory;
    private final StatementPrinter statementPrinter;

    public BankAccount(StatementPrinter statementPrinter, OperationHistory operationHistory) {
        this.statementPrinter = statementPrinter;
        this.operationHistory = operationHistory;
    }

    public void deposit(BigDecimal amountToDeposit) {
        operationHistory.add(OperationType.DEPOSIT, amountToDeposit);
    }

    public void withdraw(BigDecimal amountToWithdraw) {
        operationHistory.add(OperationType.WITHDRAW, amountToWithdraw.negate());
    }

    public void printStatement() {
        statementPrinter.print(operationHistory.getStatement());
    }

}
