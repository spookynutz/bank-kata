package com.kata.bank.operation;

import com.kata.bank.Money;

import java.time.LocalDateTime;

public class Operation {
    private OperationType operationType;
    private Money operationAmount;
    private LocalDateTime operationDate;

    public Operation(OperationType operationType, Money operationAmount, LocalDateTime operationDate) {
        this.operationType = operationType;
        this.operationAmount = operationAmount;
        this.operationDate = operationDate;
    }

    public Money getOperationAmount() {
        return operationAmount;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public String getOperationType() {
        return operationType.name();
    }
}
