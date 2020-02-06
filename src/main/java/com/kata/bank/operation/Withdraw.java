package com.kata.bank.operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Withdraw {
    private String operationType;
    private BigDecimal operationAmount;
    private LocalDateTime operationDate;

    public Withdraw(String operationType, BigDecimal operationAmount, LocalDateTime operationDate) {
        this.operationType = operationType;
        this.operationAmount = operationAmount.setScale(2);
        this.operationDate = operationDate;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public BigDecimal getOperationAmount() {
        return operationAmount;
    }

    public String getOperationType() {
        return operationType;
    }
}
