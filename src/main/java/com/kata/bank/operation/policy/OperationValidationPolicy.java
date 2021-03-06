package com.kata.bank.operation.policy;

import com.kata.bank.Money;

import java.math.BigDecimal;

public class OperationValidationPolicy {

    private OperationValidationPolicy() {
    }

    public static boolean isWithdrawAllowed(Money accountBalance, Money amountToWithdraw) {
        if (null == accountBalance) {
            throw new IllegalArgumentException("Account balance cannot be null");
        }

        if (null == amountToWithdraw) {
            throw new IllegalArgumentException("Amount to withdraw cannot be null");
        }

        if (amountIsNegative(amountToWithdraw)){
            throw new IllegalArgumentException("Cannot withdraw negative amount");
        }

        return accountBalance.greaterOrEqualTo(amountToWithdraw);
    }

    private static boolean amountIsNegative(Money amount) {
        return new Money(BigDecimal.ZERO).greaterOrEqualTo(amount);
    }
}
