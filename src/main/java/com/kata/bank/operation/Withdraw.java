package com.kata.bank.operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Withdraw {
    private String operationType;
    private BigDecimal amountToWithdraw;
    private LocalDateTime withdrawDate;

    public Withdraw(String operationType, BigDecimal operationAmount, LocalDateTime operationDate) {
        this.operationType = operationType;
        this.amountToWithdraw = operationAmount.setScale(2);
        this.withdrawDate = operationDate;
    }

    public LocalDateTime getWithdrawDate() {
        return withdrawDate;
    }

    public BigDecimal getAmountToWithdraw() {
        return amountToWithdraw;
    }

    public String getOperationType() {
        return operationType;
    }
}
