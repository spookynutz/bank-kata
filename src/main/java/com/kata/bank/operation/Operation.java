package com.kata.bank.operation;

import com.kata.bank.Money;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Operation {
    private final OperationType operationType;
    private final Money operationAmount;
    private final LocalDateTime operationDate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return operationType == operation.operationType &&
                Objects.equals(operationAmount, operation.operationAmount) &&
                Objects.equals(operationDate, operation.operationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, operationAmount, operationDate);
    }
}
