package com.kata.bank.operation.policy;

import com.kata.bank.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static com.kata.bank.operation.policy.OperationValidationPolicy.isWithdrawAllowed;
import static org.assertj.core.api.Assertions.assertThat;

public class OperationValidationPolicyTest {

    private static final Money AMOUNT_100 = new Money(BigDecimal.valueOf(100));
    private static final Money AMOUNT_50 = new Money(BigDecimal.valueOf(50));
    private static final Money AMOUNT_NULL = null;
    private static final Money AMOUNT_NEGATIVE = new Money(BigDecimal.valueOf(-2));

    @Test
    public void allow_withdraw_when_account_balance_above_amount_to_withdraw(){
        Money accountBalance = AMOUNT_100;
        Money amountToWithdraw = AMOUNT_50;
        assertThat(isWithdrawAllowed(accountBalance, amountToWithdraw)).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_account_balance_is_null(){
        Money accountBalance = AMOUNT_NULL;
        Money amountToWithdraw = AMOUNT_50;
        assertThat(isWithdrawAllowed(accountBalance, amountToWithdraw));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_amount_to_withdraw_is_null(){
        Money accountBalance = AMOUNT_100;
        Money amountToWithdraw = AMOUNT_NULL;
        assertThat(isWithdrawAllowed(accountBalance, amountToWithdraw));
    }

    @Test
    public void disallow_withdraw_when_account_balance_below_amount_to_withdraw(){
        Money accountBalance = AMOUNT_50;
        Money amountToWithdraw = AMOUNT_100;
        assertThat(isWithdrawAllowed(accountBalance, amountToWithdraw)).isFalse();
    }

    @Test
    public void allow_withdraw_when_account_balance_equal_to_amount_to_withdraw(){
        Money accountBalance = AMOUNT_50;
        Money amountToWithdraw = AMOUNT_50;
        assertThat(isWithdrawAllowed(accountBalance, amountToWithdraw)).isTrue();
    }

    @Test
    public void disallow_withdraw_when_account_balance_is_negative(){
        Money accountBalance = AMOUNT_NEGATIVE;
        Money amountToWithdraw = AMOUNT_100;
        assertThat(isWithdrawAllowed(accountBalance, amountToWithdraw)).isFalse();
    }

}