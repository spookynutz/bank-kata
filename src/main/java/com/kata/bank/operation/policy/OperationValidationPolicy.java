package com.kata.bank.operation.policy;

import com.kata.bank.Money;

public class OperationValidationPolicy {

    private OperationValidationPolicy() {
    }

    public static boolean isWithdrawAllowed(Money accountBalance, Money amountToWithdraw) {
        return accountBalance.greaterOrEqualTo(amountToWithdraw);
    }
}
