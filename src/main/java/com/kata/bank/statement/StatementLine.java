package com.kata.bank.statement;

import com.kata.bank.operation.Operation;
import com.kata.bank.operation.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StatementLine {
    private final BigDecimal accountBalance;
    private final Operation operation;

    public StatementLine(BigDecimal accountBalance, Operation operation) {
        this.accountBalance = accountBalance;
        this.operation = operation;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public String getOperationType() {
        return operation.getOperationType();
    }

    public BigDecimal getOperationAmount() {
        return operation.getOperationAmount();
    }

    public LocalDateTime getOperationDate() {
        return operation.getOperationDate();
    }
}
