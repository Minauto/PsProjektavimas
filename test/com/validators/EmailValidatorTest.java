package com.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    EmailValidator mailVal = new EmailValidator();

    @Test
    void testHasEtaSymbol() {
        assertTrue(mailVal.hasEtaSymbol("petras.petrauskas@gmail.com"));
    }

    @Test
    void testHasEtaSymbolFail() {
        assertFalse(mailVal.hasEtaSymbol("petras"));
    }

    @Test
    void hasCorrectSymbols() {
        assertTrue(mailVal.hasCorrectSymbols("petras.petrauskas@gmail.com"));
    }

    @Test
    void hasCorrectSymbolsFail() {
        assertFalse(mailVal.hasCorrectSymbols("petras.petrauskas©@gmail.com"));
    }

    @Test
    void hasCorrectDomain() {
        assertTrue(mailVal.hasCorrectDomain("petras.petrauskas@gmail.lt"));
    }

    @Test
    void hasCorrectDomainFail() {
        assertFalse(mailVal.hasCorrectDomain("petras@petrauskas.lietuva"));
    }
}