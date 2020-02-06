package com.kata.bank.statement;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsoleStatementPrinter implements StatementPrinter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String FIELD_SEPARATOR = " | ";
    private static final String STATEMENT_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";

    @Override
    public void print(List<StatementLine> statementLines) {
        if (statementLines.isEmpty()) {
            return;
        }

        printLine(STATEMENT_HEADER);

        for (StatementLine statementLine : statementLines) {
            printLine(statementLine.getOperationType() +
                    FIELD_SEPARATOR +
                    DATE_TIME_FORMATTER.format(statementLine.getOperationDate()) +
                    FIELD_SEPARATOR +
                    statementLine.getOperationAmount().abs() +
                    FIELD_SEPARATOR +
                    statementLine.getAccountBalance());
        }
    }

    protected void printLine(String message) {
        System.out.println(message);
    }
}
