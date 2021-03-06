package com.kata.bank.operation;

import com.kata.bank.Money;
import com.kata.bank.SystemClock;
import com.kata.bank.statement.Statement;
import com.kata.bank.statement.StatementLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OperationHistory {
    private List<Operation> operationHistory = new ArrayList<Operation>();
    private SystemClock systemClock;

    public OperationHistory(SystemClock systemClock) {
        this.systemClock = systemClock;
    }

    public void add(OperationType operationType, Money amount) {
        this.operationHistory.add(new Operation(operationType, amount, systemClock.getTime()));
    }

    public Statement getStatement() {
        List<StatementLine> statementLines = new ArrayList<>();

        Money accountBalance = new Money(BigDecimal.valueOf(0));

        for (Operation operation : operationHistory) {
            accountBalance = accountBalance.add(operation.getOperationAmount());
            statementLines.add(new StatementLine(accountBalance, operation));
        }

        return new Statement(statementLines, accountBalance);
    }

    public Money getBalance() {
        return getStatement().getAccountBalance();
    }
}