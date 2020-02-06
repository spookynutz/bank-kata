package com.kata.bank.operation;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class OperationTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Operation.class).verify();
    }
}