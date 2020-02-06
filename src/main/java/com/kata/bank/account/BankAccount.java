package com.kata.bank.account;

import com.kata.bank.SystemClock;
import com.kata.bank.operation.Deposit;
import com.kata.bank.operation.Withdraw;
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
        depositHistory.add(new Deposit(amountToDeposit, systemClock.getTime()));
    }

    public void printStatement() {
        if (depositHistory.isEmpty()) {
            return;
        }
        statementPrinter.print("OPERATION | DATE | AMOUNT | BALANCE");

        BigDecimal accountBalance = BigDecimal.ZERO.setScale(2);

        for (Deposit deposit : depositHistory) {
            String operationType = "DEPOSIT";
            BigDecimal operationAmount = deposit.getAmountToDeposit();
            LocalDateTime operationDate = deposit.getDepositDate();
            accountBalance = accountBalance.add(operationAmount);
            printStatement(accountBalance, operationType, operationAmount, operationDate);
        }

        for (Withdraw withdraw : withdrawHistory) {
            String operationType = "WITHDRAW";
            BigDecimal operationAmount = withdraw.getAmountToWithdraw();
            LocalDateTime operationDate = withdraw.getWithdrawDate();
            accountBalance = accountBalance.subtract(operationAmount);
            printStatement(accountBalance, operationType, operationAmount, operationDate);
        }
    }

    private void printStatement(BigDecimal accountBalance, String operationType, BigDecimal operationAmount, LocalDateTime operationDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fieldSeparator = " | ";
        statementPrinter.print(operationType + fieldSeparator + dtf.format(operationDate) + fieldSeparator + operationAmount + fieldSeparator + accountBalance);
    }

    public void withdraw(BigDecimal amountToWithdraw) {
        withdrawHistory.add(new Withdraw(amountToWithdraw, systemClock.getTime()));
    }
}
