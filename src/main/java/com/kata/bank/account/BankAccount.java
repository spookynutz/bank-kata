package com.kata.bank.account;

import com.kata.bank.Money;
import com.kata.bank.operation.OperationHistory;
import com.kata.bank.operation.OperationType;
import com.kata.bank.operation.exception.OperationNotAllowedException;
import com.kata.bank.operation.policy.OperationValidationPolicy;
import com.kata.bank.statement.StatementPrinter;

public class BankAccount {

    private final OperationHistory operationHistory;
    private final StatementPrinter statementPrinter;

    public BankAccount(StatementPrinter statementPrinter, OperationHistory operationHistory) {
        this.statementPrinter = statementPrinter;
        this.operationHistory = operationHistory;
    }

    public void deposit(Money amountToDeposit) {
        operationHistory.add(OperationType.DEPOSIT, amountToDeposit);
    }

    public void withdraw(Money amountToWithdraw) {
        if (!OperationValidationPolicy.isWithdrawAllowed(operationHistory.getBalance(), amountToWithdraw)){
            throw new OperationNotAllowedException("Withdraw amount above account balance. Operation rejected");
        }
        operationHistory.add(OperationType.WITHDRAW, amountToWithdraw.negate());
    }

    public void printStatement() {
        statementPrinter.print(operationHistory.getStatement());
    }

}
