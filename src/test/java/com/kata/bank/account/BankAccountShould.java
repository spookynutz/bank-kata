package com.kata.bank.account;

import com.kata.bank.statement.ConsoleStatementPrinter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BankAccountShould {

    public static final BigDecimal AMOUNT_50 = BigDecimal.valueOf(50);
    public static final BigDecimal AMOUNT_20 = BigDecimal.valueOf(20);

    private TestableStatementPrinter statementPrinter;
    private BankAccount bankAccount;

    @Before
    public void setUp() throws Exception {
        statementPrinter = new TestableStatementPrinter();
        bankAccount = new BankAccount(statementPrinter);
    }

    @Test
    public void allow_client_to_make_a_deposit(){
        BigDecimal amountToDeposit = AMOUNT_20;

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
        BigDecimal amountToDeposit = AMOUNT_50;

        bankAccount.deposit(amountToDeposit);
        bankAccount.printStatement();
        assertThat(statementPrinter.printedStatements).containsExactly(
                "OPERATION | DATE | AMOUNT | BALANCE",
                "DEPOSIT | 29/01/2020 | 50.00 | 50.00"
        );
    }

    @Test
    public void allow_client_to_make_multiple_deposits(){
        BigDecimal amountToDeposit = AMOUNT_20;
        BigDecimal anotherAmountToDeposit = AMOUNT_50;

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
        BigDecimal amountToDeposit = AMOUNT_20;
        BigDecimal anotherAmountToDeposit = AMOUNT_50;

        bankAccount.deposit(amountToDeposit);
        new Clock().advanceTimeByOneDay();
        bankAccount.deposit(anotherAmountToDeposit);
        bankAccount.printStatement();
        assertThat(statementPrinter.printedStatements).containsExactly(
                "OPERATION | DATE | AMOUNT | BALANCE",
                "DEPOSIT | 29/01/2020 | 20.00 | 20.00",
                "DEPOSIT | 30/01/2020 | 50.00 | 70.00"
        );
    }

    private class TestableStatementPrinter extends ConsoleStatementPrinter {

        private List<String> printedStatements = new ArrayList<>();

        @Override
        public void print(String statement) {
            super.print(statement);
            printedStatements.add(statement);
        }
    }

    private class Clock {
        public void advanceTimeByOneDay() {
        }
    }
}
