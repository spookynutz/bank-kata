package com.kata.bank.statement;

import com.kata.bank.Money;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    private final List<StatementLine> statementLines = new ArrayList<>();
    private Money accountBalance;

    public Statement(List<StatementLine> statementLines, Money accountBalance) {
        this.accountBalance = accountBalance;
        this.statementLines.addAll(statementLines);
    }

    public List<StatementLine> getStatementLines() {
        return statementLines;
    }

    public boolean isEmpty() {
        return this.statementLines.isEmpty();
    }

    public Money getAccountBalance() {
        return accountBalance;
    }
}
