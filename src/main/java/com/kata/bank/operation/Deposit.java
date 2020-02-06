package com.kata.bank.operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Deposit {
    private BigDecimal amountToDeposit;
    private LocalDateTime depositDate;

    public Deposit(BigDecimal amountToDeposit, LocalDateTime depositDate) {
        this.amountToDeposit = amountToDeposit.setScale(2);
        this.depositDate = depositDate;
    }

    public BigDecimal getAmountToDeposit() {
        return amountToDeposit;
    }

    public LocalDateTime getDepositDate() {
        return depositDate;
    }
}
