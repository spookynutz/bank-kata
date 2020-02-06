package com.kata.bank.operation;

import java.math.BigDecimal;

public class Deposit {
    private BigDecimal amountToDeposit;

    public Deposit(BigDecimal amountToDeposit) {
        this.amountToDeposit = amountToDeposit.setScale(2);
    }

    public BigDecimal getAmountToDeposit() {
        return amountToDeposit;
    }
}
