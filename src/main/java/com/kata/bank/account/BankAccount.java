package com.kata.bank.account;

import com.kata.bank.SystemClock;
import com.kata.bank.operation.Deposit;
import com.kata.bank.operation.Withdraw;
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
    private List<Deposit> depositHistory = new ArrayList<>();
    private List<Withdraw> withdrawHistory = new ArrayList<>();

    public BankAccount(StatementPrinter statementPrinter, SystemClock systemClock) {
        this.statementPrinter = statementPrinter;
        this.systemClock = systemClock;
    }

    public void deposit(BigDecimal amountToDeposit) {
        depositHistory.add(new Deposit("DEPOSIT", amountToDeposit, systemClock.getTime()));
    }

    public void withdraw(BigDecimal amountToWithdraw) {
        withdrawHistory.add(new Withdraw("WITHDRAW", amountToWithdraw.negate(), systemClock.getTime()));
    }

    public void printStatement() {
        if (depositHistory.isEmpty()) {
            return;
        }
        statementPrinter.print("OPERATION | DATE | AMOUNT | BALANCE");

        BigDecimal accountBalance = BigDecimal.ZERO.setScale(2);

        for (Deposit deposit : depositHistory) {
            String operationType = deposit.getOperationType();
            BigDecimal operationAmount = deposit.getOperationAmount();
            LocalDateTime operationDate = deposit.getOperationDate();
            accountBalance = accountBalance.add(operationAmount);
            printStatement(new StatementLine(accountBalance, operationType, operationAmount, operationDate));
        }

        for (Withdraw withdraw : withdrawHistory) {
            String operationType = withdraw.getOperationType();
            BigDecimal operationAmount = withdraw.getOperationAmount();
            LocalDateTime operationDate = withdraw.getOperationDate();
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
