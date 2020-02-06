package com.kata.bank.statement;

import com.kata.bank.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Statement {
    private final List<StatementLine> statementLines = new ArrayList<>();
    private final Money accountBalance;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statement statement = (Statement) o;
        return Objects.equals(statementLines, statement.statementLines) &&
                Objects.equals(accountBalance, statement.accountBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statementLines, accountBalance);
    }
}
