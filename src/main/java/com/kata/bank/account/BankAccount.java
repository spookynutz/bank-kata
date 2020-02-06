package com.kata.bank.account;

import com.kata.bank.SystemClock;
import com.kata.bank.operation.Operation;
import com.kata.bank.operation.OperationHistory;
import com.kata.bank.operation.OperationType;
import com.kata.bank.statement.StatementPrinter;

import java.math.BigDecimal;

public class BankAccount {

    private final OperationHistory operationHistory;
    private final StatementPrinter statementPrinter;
    private final SystemClock systemClock;

    public BankAccount(StatementPrinter statementPrinter, SystemClock systemClock, OperationHistory operationHistory) {
        this.statementPrinter = statementPrinter;
        this.systemClock = systemClock;
        this.operationHistory = operationHistory;
    }

    public void deposit(BigDecimal amountToDeposit) {
        operationHistory.add(new Operation(OperationType.DEPOSIT, amountToDeposit, systemClock.getTime()));
    }

    public void withdraw(BigDecimal amountToWithdraw) {
        operationHistory.add(new Operation(OperationType.WITHDRAW, amountToWithdraw.negate(), systemClock.getTime()));
    }

    public void printStatement() {
        statementPrinter.print(operationHistory.getStatement());
    }

}
