package com.kata.bank;

import java.math.BigDecimal;
import java.util.Objects;

public final class Money {
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount.setScale(2);
    }

    public Money negate() {
        return new Money(amount.negate());
    }

    public Money add(Money operationAmount) {
        return new Money(this.amount.add(operationAmount.amount));
    }

    public Money abs() {
        return new Money(amount.abs());
    }

    @Override
    public String toString() {
        return amount.setScale(2).toString();
    }

    public boolean greaterOrEqualTo(Money other) {
        return this.amount.compareTo(other.amount) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}