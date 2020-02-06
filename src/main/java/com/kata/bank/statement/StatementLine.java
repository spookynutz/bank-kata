package com.kata.bank.statement;

import com.kata.bank.Money;
import com.kata.bank.operation.Operation;

import java.time.LocalDateTime;

public class StatementLine {
    private final Money accountBalance;
    private final Operation operation;

    public StatementLine(Money accountBalance, Operation operation) {
        this.accountBalance = accountBalance;
        this.operation = operation;
    }

    public Money getAccountBalance() {
        return accountBalance;
    }

    public String getOperationType() {
        return operation.getOperationType();
    }

    public Money getOperationAmount() {
        return operation.getOperationAmount();
    }

    public LocalDateTime getOperationDate() {
        return operation.getOperationDate();
    }
}
