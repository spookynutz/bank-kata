package com.kata.bank.statement;

import com.kata.bank.operation.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StatementLine {
    private final BigDecimal accountBalance;
    private final OperationType operationType;
    private final BigDecimal operationAmount;
    private final LocalDateTime operationDate;

    public StatementLine(BigDecimal accountBalance, OperationType operationType, BigDecimal operationAmount, LocalDateTime operationDate) {
        this.accountBalance = accountBalance;
        this.operationType = operationType;
        this.operationAmount = operationAmount;
        this.operationDate = operationDate;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public String getOperationType() {
        return operationType.name();
    }

    public BigDecimal getOperationAmount() {
        return operationAmount;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }
}
