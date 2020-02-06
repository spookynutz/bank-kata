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
            BigDecimal depositAmount = deposit.getAmountToDeposit();
            LocalDateTime depositDate = deposit.getDepositDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            accountBalance = accountBalance.add(depositAmount);
            statementPrinter.print("DEPOSIT | " + dtf.format(depositDate) + " | " + depositAmount + " | " + accountBalance);
        }

        for (Withdraw withdraw : withdrawHistory) {
            BigDecimal withdrawAmount = withdraw.getAmountToWithdraw();
            LocalDateTime withdrawDate = withdraw.getWithdrawDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            accountBalance = accountBalance.subtract(withdrawAmount);
            statementPrinter.print("WITHDRAW | " + dtf.format(withdrawDate) + " | " + withdrawAmount + " | " + accountBalance);
        }
    }

    public void withdraw(BigDecimal amountToWithdraw) {
        withdrawHistory.add(new Withdraw(amountToWithdraw, systemClock.getTime()));
    }
}
