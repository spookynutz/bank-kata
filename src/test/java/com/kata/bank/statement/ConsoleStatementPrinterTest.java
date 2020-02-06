package com.kata.bank.statement;

import com.kata.bank.Money;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleStatementPrinterTest {

    private final StatementPrinter statementPrinter = new ConsoleStatementPrinter();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream consoleOut = System.out;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(consoleOut);
    }

    @Test
    public void do_nothing_with_null_statement(){
        statementPrinter.print(null);
        assertThat(output.toString()).isEmpty();
    }

    @Test
    public void print_nothing_when_statement_has_no_statement_lines(){
        statementPrinter.print(new Statement(new ArrayList<>(), new Money(BigDecimal.ZERO)));
        assertThat(output.toString()).isEmpty();
    }


}