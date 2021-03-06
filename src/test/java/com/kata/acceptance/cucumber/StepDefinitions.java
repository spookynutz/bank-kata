package com.kata.acceptance.cucumber;

import com.kata.bank.LocalSystemClock;
import com.kata.bank.Money;
import com.kata.bank.SystemClock;
import com.kata.bank.account.BankAccount;
import com.kata.bank.operation.OperationHistory;
import com.kata.bank.statement.ConsoleStatementPrinter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {

    private final TestableSystemClock systemClock = new TestableSystemClock();
    private final TestableStatementPrinter statementPrinter = new TestableStatementPrinter();
    private final OperationHistory operationHistory = new OperationHistory(systemClock);
    private final BankAccount bankAccount = new BankAccount(statementPrinter, operationHistory);

    @Given("a client makes a deposit of {int} on {int}\\/{int}\\/{int}")
    public void a_client_makes_a_deposit_of_on(Integer operationAmount, Integer day, Integer month, Integer year) {
        makeADeposit(operationAmount, day, month, year);
    }

    private void makeADeposit(Integer operationAmount, Integer day, Integer month, Integer year) {
        systemClock.setTime(year, month, day);
        bankAccount.deposit(new Money(BigDecimal.valueOf(operationAmount)));
    }

    @Given("a withdrawal of {int} on {int}\\/{int}\\/{int}")
    public void a_withdrawal_of_on(Integer operationAmount, Integer day, Integer month, Integer year) {
        systemClock.setTime(year, month, day);
        bankAccount.withdraw(new Money(BigDecimal.valueOf(operationAmount)));
    }

    @Given("a deposit of {int} on {int}\\/{int}\\/{int}")
    public void a_deposit_of_on(Integer operationAmount, Integer day, Integer month, Integer year) {
        makeADeposit(operationAmount, day, month, year);
    }

    @When("he requests to print his account statement")
    public void he_requests_to_print_his_account_statement() {
        bankAccount.printStatement();
    }

    @Then("he would see")
    public void he_would_see(String docString) {
        String printedStatements = String.join(System.getProperty("line.separator"), statementPrinter.printedStatements);
        assertThat(printedStatements).isEqualToIgnoringNewLines(docString);
    }

    class TestableStatementPrinter extends ConsoleStatementPrinter {

        private List<String> printedStatements = new ArrayList<>();

        @Override
        protected void printLine(String message) {
            super.printLine(message);
            printedStatements.add(message);
        }
    }

    class TestableSystemClock extends LocalSystemClock {

        private LocalDateTime time;

        public SystemClock setTime(Integer year, Integer month, Integer day){
            this.time = LocalDateTime.of(year, month, day, 0, 0);
            return this;
        }

        @Override
        public LocalDateTime getTime() {
            return this.time;
        }
    }
}
