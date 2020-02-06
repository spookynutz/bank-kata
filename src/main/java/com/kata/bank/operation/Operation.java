package com.kata.bank.operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operation {
    private OperationType operationType;
    private BigDecimal operationAmount;
    private LocalDateTime operationDate;

    public Operation(OperationType operationType, BigDecimal operationAmount, LocalDateTime operationDate) {
        this.operationType = operationType;
        this.operationAmount = operationAmount.setScale(2);
        this.operationDate = operationDate;
    }

    public BigDecimal getOperationAmount() {
        return operationAmount;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public String getOperationType() {
        return operationType.name();
    }
}
