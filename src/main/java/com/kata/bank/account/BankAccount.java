package com.kata.bank.account;

import com.kata.bank.SystemClock;
import com.kata.bank.operation.Operation;
import com.kata.bank.operation.OperationType;
import com.kata.bank.statement.StatementLine;
import com.kata.bank.statement.StatementPrinter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private StatementPrinter statementPrinter;
    private SystemClock systemClock;
    private List<Operation> operationHistory = new ArrayList<>();

    public BankAccount(StatementPrinter statementPrinter, SystemClock systemClock) {
        this.statementPrinter = statementPrinter;
        this.systemClock = systemClock;
    }

    public void deposit(BigDecimal amountToDeposit) {
        operationHistory.add(new Operation(OperationType.DEPOSIT, amountToDeposit, systemClock.getTime()));
    }

    public void withdraw(BigDecimal amountToWithdraw) {
        operationHistory.add(new Operation(OperationType.WITHDRAW, amountToWithdraw.negate(), systemClock.getTime()));
    }

    public void printStatement() {
        if (operationHistory.isEmpty()) {
            return;
        }
        statementPrinter.print("OPERATION | DATE | AMOUNT | BALANCE");

        BigDecimal accountBalance = BigDecimal.ZERO.setScale(2);

        for (Operation operation : operationHistory) {
            OperationType operationType = operation.getOperationType();
            BigDecimal operationAmount = operation.getOperationAmount();
            LocalDateTime operationDate = operation.getOperationDate();
            accountBalance = accountBalance.add(operationAmount);
            printStatement(new StatementLine(accountBalance, operationType, operationAmount, operationDate));
        }
    }

    private void printStatement(StatementLine statementLine) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fieldSeparator = " | ";
        statementPrinter.print(statementLine.getOperationType() + fieldSeparator + dtf.format(statementLine.getOperationDate()) + fieldSeparator + statementLine.getOperationAmount().abs() + fieldSeparator + statementLine.getAccountBalance());
    }
}
