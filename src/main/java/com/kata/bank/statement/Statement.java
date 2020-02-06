package com.kata.bank.statement;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    private final List<StatementLine> statementLines = new ArrayList<>();

    public Statement(List<StatementLine> statementLines) {
        this.statementLines.addAll(statementLines);
    }

    public List<StatementLine> getStatementLines() {
        return statementLines;
    }

    public boolean isEmpty() {
        return this.statementLines.isEmpty();
    }
}
