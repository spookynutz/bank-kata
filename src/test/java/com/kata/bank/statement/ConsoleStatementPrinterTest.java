package com.kata.bank.statement;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleStatementPrinterTest {

    @Test
    public void do_nothing_with_null_statement(){
        StatementPrinter statementPrinter = new ConsoleStatementPrinter();
        statementPrinter.print(null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        assertThat(output.toString()).isEmpty();
    }
}