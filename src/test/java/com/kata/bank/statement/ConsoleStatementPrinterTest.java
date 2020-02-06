package com.kata.bank.statement;

import com.kata.bank.Money;
import com.kata.bank.operation.Operation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.kata.bank.operation.OperationType.DEPOSIT;
import static com.kata.bank.operation.OperationType.WITHDRAW;
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

    @Test
    public void print_statement_to_console_in_correct_order(){
        Money accountBalanceAfterFirstDeposit = ofAmount(100);
        Money accountBalanceAfterFirstWithdraw = ofAmount(80);
        Money accountBalanceAfterSecondDeposit = ofAmount(110);
        Money finalAccountBalance = ofAmount(60);

        Operation firstDeposit = makeADeposit(ofAmount(100), onDate(4, 2, 2020));
        Operation firstWithdraw = makeAWithdraw(ofAmount(20), onDate(5, 2, 2020));
        Operation secondDeposit = makeADeposit(ofAmount(30), onDate(6, 2, 2020));
        Operation secondWithdraw = makeAWithdraw(ofAmount(50), onDate(7, 2, 2020));

        ArrayList<StatementLine> statementLines = new ArrayList<>();
        statementLines.add(new StatementLine(accountBalanceAfterFirstDeposit, firstDeposit));
        statementLines.add(new StatementLine(accountBalanceAfterFirstWithdraw, firstWithdraw));
        statementLines.add(new StatementLine(accountBalanceAfterSecondDeposit, secondDeposit));
        statementLines.add(new StatementLine(finalAccountBalance, secondWithdraw));

        Statement statement = new Statement(statementLines, finalAccountBalance);

        statementPrinter.print(statement);

        assertThat(output.toString()).isEqualToIgnoringNewLines(
                        "OPERATION | DATE | AMOUNT | BALANCE" +
                        "DEPOSIT | 04/02/2020 | 100.00 | 100.00" +
                        "WITHDRAW | 05/02/2020 | 20.00 | 80.00" +
                        "DEPOSIT | 06/02/2020 | 30.00 | 110.00" +
                        "WITHDRAW | 07/02/2020 | 50.00 | 60.00");
    }

    private Money ofAmount(int val) {
        return new Money(BigDecimal.valueOf(val));
    }

    private Operation makeAWithdraw(Money operationAmount, LocalDateTime operationDate) {
        return new Operation(WITHDRAW, operationAmount, operationDate);
    }

    private Operation makeADeposit(Money operationAmount, LocalDateTime operationDate) {
        return new Operation(DEPOSIT, operationAmount, operationDate);
    }

    private LocalDateTime onDate(int dayOfMonth, int month, int year) {
        return LocalDateTime.of(year, month, dayOfMonth, 0, 0);
    }
}