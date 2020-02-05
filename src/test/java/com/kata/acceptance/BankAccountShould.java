package com.kata.acceptance;

import com.kata.bank.BankAccount;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountShould {

    @Test
    public void allow_client_to_make_a_deposit(){
        BigDecimal amountToDeposit = BigDecimal.valueOf(20);
        List<String> printedStatements = new ArrayList<>();

        BankAccount bankAccount = new BankAccount(printedStatements);
        bankAccount.deposit(amountToDeposit);
        bankAccount.printStatement();

        assertThat(printedStatements).containsExactly(
                "OPERATION | DATE | AMOUNT | BALANCE",
                "DEPOSIT | 29/01/2020 | 20.00 | 20.00"
        );
    }
}
