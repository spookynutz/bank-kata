package com.kata.acceptance.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/kata/acceptance/cucumber",
        glue = "com.kata.acceptance.cucumber"
)
public class BankAccountCucumberTest {
}
