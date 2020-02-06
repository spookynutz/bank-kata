package com.kata.bank;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class MoneyTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Money.class).verify();
    }
}