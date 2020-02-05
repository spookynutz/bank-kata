package com.kata.bank.statement;

public class ConsoleStatementPrinter implements StatementPrinter {

    @Override
    public void print(String statement) {
        System.out.println(statement);
    }
}
