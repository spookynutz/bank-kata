package com.kata.acceptance.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    @Given("a client makes a deposit of {int} on {int}\\/{int}\\/{int}")
    public void a_client_makes_a_deposit_of_on(Integer operationAmount, Integer day, Integer month, Integer year) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("he requests to print his account statement")
    public void he_requests_to_print_his_account_statement() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("he would see")
    public void he_would_see(String docString) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
