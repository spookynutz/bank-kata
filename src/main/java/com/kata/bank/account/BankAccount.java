package com.kata.bank.account;

import com.kata.bank.SystemClock;
import com.kata.bank.operation.Operation;
import com.kata.bank.operation.OperationHistory;
import com.kata.bank.operation.OperationType;
import com.kata.bank.statement.StatementLine;
import com.kata.bank.statement.StatementPrinter;

import java.math.BigDecimal;

public class BankAccount {

    private final OperationHistory operationHistory = new OperationHistory();
    private StatementPrinter statementPrinter;
    private SystemClock systemClock;

    public BankAccount(StatementPrinter statementPrinter, SystemClock systemClock) {
        this.statementPrinter = statementPrinter;
        this.systemClock = systemClock;
    }

    public void deposit(BigDecimal amountToDeposit) {
        operationHistory.operationHistory.add(new Operation(OperationType.DEPOSIT, amountToDeposit, systemClock.getTime()));
    }

    public void withdraw(BigDecimal amountToWithdraw) {
        operationHistory.operationHistory.add(new Operation(OperationType.WITHDRAW, amountToWithdraw.negate(), systemClock.getTime()));
    }

    public void printStatement() {
        if (operationHistory.operationHistory.isEmpty()) {
            return;
        }
        statementPrinter.printHeader();

        BigDecimal accountBalance = BigDecimal.ZERO.setScale(2);

        for (Operation operation : operationHistory.operationHistory) {
            accountBalance = accountBalance.add(operation.getOperationAmount());
            printStatement(new StatementLine(accountBalance, operation.getOperationType(), operation.getOperationAmount(), operation.getOperationDate()));
        }
    }

    private void printStatement(StatementLine statementLine) {
        statementPrinter.print(statementLine);
    }
}
