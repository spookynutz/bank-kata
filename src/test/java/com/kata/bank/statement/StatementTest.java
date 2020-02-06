package com.kata.bank.statement;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class StatementTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Statement.class).verify();
    }
}