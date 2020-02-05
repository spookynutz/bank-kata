package com.kata.bank.account;

import com.kata.bank.statement.ConsoleStatementPrinter;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BankAccountShould {

    @Test
    public void allow_client_to_make_a_deposit(){
        BigDecimal amountToDeposit = BigDecimal.valueOf(20);

        TestableStatementPrinter statementPrinter = new TestableStatementPrinter();

        BankAccount bankAccount = new BankAccount(statementPrinter);
        bankAccount.deposit(amountToDeposit);
        bankAccount.printStatement();

        assertThat(statementPrinter.printedStatements).containsExactly(
                "OPERATION | DATE | AMOUNT | BALANCE",
                "DEPOSIT | 29/01/2020 | 20.00 | 20.00"
        );
    }

    @Test
    public void display_nothing_when_printing_statement_on_brand_new_account(){
        TestableStatementPrinter statementPrinter = new TestableStatementPrinter();
        BankAccount bankAccount = new BankAccount(statementPrinter);
        bankAccount.printStatement();

        assertThat(statementPrinter.printedStatements).isEmpty();
    }

    class TestableStatementPrinter extends ConsoleStatementPrinter {

        private List<String> printedStatements = new ArrayList<>();

        @Override
        public void print(String statement) {
            super.print(statement);
            printedStatements.add(statement);
        }
    }
}
