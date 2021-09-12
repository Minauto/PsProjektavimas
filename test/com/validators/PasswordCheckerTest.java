package com.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    PasswordChecker pswCh = new PasswordChecker();

    @Test
    void testPasswordLength() {
        assertTrue(pswCh.isNotShorter("LabaiSlaptas",6));
    }

    @Test
    void testPasswordLengthFail() {
        assertFalse(pswCh.isNotShorter("123",6));
    }

    @Test
    void testHasUpperCase() {
        assertTrue(pswCh.hasUpperCase("LabaiSlaptas"));
    }

    @Test
    void testHasUpperCaseFail() {
        assertFalse(pswCh.hasUpperCase("labaislaptas"));
    }

    @Test
    void testHasSpecialChar() {
        assertTrue(pswCh.hasSpecialChar("LabaiSlaptas!"));
    }

    @Test
    void testHasSpecialCharFail() {
        assertFalse(pswCh.hasSpecialChar("slaptas"));
    }
}