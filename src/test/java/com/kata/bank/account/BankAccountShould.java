package com.kata.bank.account;

import com.kata.bank.LocalSystemClock;
import com.kata.bank.Money;
import com.kata.bank.SystemClock;
import com.kata.bank.operation.OperationHistory;
import com.kata.bank.operation.exception.OperationNotAllowedException;
import com.kata.bank.statement.ConsoleStatementPrinter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BankAccountShould {

    public static final Money AMOUNT_50 = new Money(BigDecimal.valueOf(50));
    public static final Money AMOUNT_20 = new Money(BigDecimal.valueOf(20));
    public static final Money AMOUNT_40 = new Money(BigDecimal.valueOf(40));
    public static final Money AMOUNT_30 = new Money(BigDecimal.valueOf(30));

    private OperationHistory operationHistory;
    private TestableStatementPrinter statementPrinter;
    private BankAccount bankAccount;
    private TestableSystemClock systemClock;

    @Before
    public void setUp() throws Exception {
        statementPrinter = new TestableStatementPrinter();
        systemClock = new TestableSystemClock();
        operationHistory = new OperationHistory(systemClock);
        bankAccount = new BankAccount(statementPrinter, operationHistory);
    }

    @Test
    public void allow_client_to_make_a_deposit(){
        Money amountToDeposit = AMOUNT_20;

        bankAccount.deposit(amountToDeposit);
        bankAccount.printStatement();
        assertThat(statementPrinter.printedStatements).containsExactly(
                "OPERATION | DATE | AMOUNT | BALANCE",
                "DEPOSIT | 29/01/2020 | 20.00 | 20.00"
        );
    }

    @Test
    public void display_nothing_when_printing_statement_on_brand_new_account(){
        bankAccount.printStatement();
        assertThat(statementPrinter.printedStatements).isEmpty();
    }

    @Test
    public void allow_client_to_make_a_deposit_of_another_amount(){
        Money amountToDeposit = AMOUNT_50;

        bankAccount.deposit(amountToDeposit);
        bankAccount.printStatement();
        assertThat(statementPrinter.printedStatements).containsExactly(
                "OPERATION | DATE | AMOUNT | BALANCE",
                "DEPOSIT | 29/01/2020 | 50.00 | 50.00"
        );
    }

    @Test
    public void allow_client_to_make_multiple_deposits(){
        Money amountToDeposit = AMOUNT_20;
        Money anotherAmountToDeposit = AMOUNT_50;

        bankAccount.deposit(amountToDeposit);
        bankAccount.deposit(anotherAmountToDeposit);
        bankAccount.printStatement();
        assertThat(statementPrinter.printedStatements).containsExactly(
                "OPERATION | DATE | AMOUNT | BALANCE",
                "DEPOSIT | 29/01/2020 | 20.00 | 20.00",
                "DEPOSIT | 29/01/2020 | 50.00 | 70.00"
        );
    }

    @Test
    public void be_able_to_display_deposits_made_on_different_dates(){
        Money amountToDeposit = AMOUNT_20;
        Money anotherAmountToDeposit = AMOUNT_50;

        bankAccount.deposit(amountToDeposit);
        systemClock.advanceTimeByOneDay();
        bankAccount.deposit(anotherAmountToDeposit);
        bankAccount.printStatement();
        assertThat(statementPrinter.printedStatements).containsExactly(
                "OPERATION | DATE | AMOUNT | BALANCE",
                "DEPOSIT | 29/01/2020 | 20.00 | 20.00",
                "DEPOSIT | 30/01/2020 | 50.00 | 70.00"
        );
    }

    @Test
    public void be_able_to_withdraw_money_from_an_account(){
        Money amountToDeposit = AMOUNT_50;
        Money amountToWithdraw = AMOUNT_20;

        bankAccount.deposit(amountToDeposit);
        bankAccount.withdraw(amountToWithdraw);
        bankAccount.printStatement();
        assertThat(statementPrinter.printedStatements).containsExactly(
                "OPERATION | DATE | AMOUNT | BALANCE",
                "DEPOSIT | 29/01/2020 | 50.00 | 50.00",
                "WITHDRAW | 29/01/2020 | 20.00 | 30.00"
        );
    }

    @Test
    public void be_able_to_mix_multiple_deposits_and_withdrawals_on_an_account(){
        Money amountToDeposit = AMOUNT_50;
        Money amountToWithdraw = AMOUNT_20;
        Money anotherAmountToDeposit = AMOUNT_40;
        Money anotherAmountToWithdraw = AMOUNT_30;

        bankAccount.deposit(amountToDeposit);
        bankAccount.withdraw(amountToWithdraw);
        bankAccount.deposit(anotherAmountToDeposit);
        bankAccount.withdraw(anotherAmountToWithdraw);
        bankAccount.printStatement();
        assertThat(statementPrinter.printedStatements).containsExactly(
                "OPERATION | DATE | AMOUNT | BALANCE",
                "DEPOSIT | 29/01/2020 | 50.00 | 50.00",
                "WITHDRAW | 29/01/2020 | 20.00 | 30.00",
                "DEPOSIT | 29/01/2020 | 40.00 | 70.00",
                "WITHDRAW | 29/01/2020 | 30.00 | 40.00"
        );
    }

    @Test(expected = OperationNotAllowedException.class)
    public void not_be_allowed_to_withdraw_more_than_account_balance(){
        bankAccount.withdraw(AMOUNT_20);
    }

    private class TestableStatementPrinter extends ConsoleStatementPrinter {

        private List<String> printedStatements = new ArrayList<>();

        @Override
        protected void printLine(String message) {
            super.printLine(message);
            printedStatements.add(message);
        }
    }

    private class TestableSystemClock extends LocalSystemClock {

        private LocalDateTime time = LocalDateTime.of(2020, 1, 29, 0, 0);

        public SystemClock advanceTimeByOneDay(){
            this.time = this.time.plus(1, ChronoUnit.DAYS);
            return this;
        }

        @Override
        public LocalDateTime getTime() {
            return this.time;
        }
    }
}
