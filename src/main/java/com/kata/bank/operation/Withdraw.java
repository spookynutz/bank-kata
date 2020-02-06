package com.kata.bank.operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Withdraw {
    private BigDecimal amountToWithdraw;
    private LocalDateTime withdrawDate;

    public Withdraw(BigDecimal amountToWithdraw, LocalDateTime withdrawDate) {
        this.amountToWithdraw = amountToWithdraw.setScale(2);
        this.withdrawDate = withdrawDate;
    }

    public LocalDateTime getWithdrawDate() {
        return withdrawDate;
    }

    public BigDecimal getAmountToWithdraw() {
        return amountToWithdraw;
    }
}
