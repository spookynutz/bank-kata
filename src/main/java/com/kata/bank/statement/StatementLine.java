package com.kata.bank.statement;

import com.kata.bank.Money;
import com.kata.bank.operation.Operation;

import java.time.LocalDateTime;
import java.util.Objects;

public final class StatementLine {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatementLine that = (StatementLine) o;
        return Objects.equals(accountBalance, that.accountBalance) &&
                Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountBalance, operation);
    }
}
