package com.kata.bank;

import java.math.BigDecimal;

public class Money {
    private BigDecimal amount;

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
}