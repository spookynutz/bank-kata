package com.kata.bank.statement;

public interface StatementPrinter {
    void printHeader();
    void print(StatementLine statement);
}
