package com.kata.bank.statement;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class StatementLineTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(StatementLine.class).verify();
    }
}