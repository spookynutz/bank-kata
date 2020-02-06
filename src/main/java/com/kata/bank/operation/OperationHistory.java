package com.kata.bank.operation;

import com.kata.bank.statement.Statement;
import com.kata.bank.statement.StatementLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OperationHistory {
    public List<Operation> operationHistory = new ArrayList<Operation>();

    public void add(Operation operation){
        this.operationHistory.add(operation);
    }

    public Statement getStatement() {
        List<StatementLine> statementLines = new ArrayList<>();

        BigDecimal accountBalance = BigDecimal.ZERO.setScale(2);

        for (Operation operation : operationHistory) {
            accountBalance = accountBalance.add(operation.getOperationAmount());
            statementLines.add(new StatementLine(accountBalance, operation.getOperationType(), operation.getOperationAmount(), operation.getOperationDate()));
        }

        return new Statement(statementLines);
    }
}