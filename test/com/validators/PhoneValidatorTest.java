package com.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    PhoneValidator phoVal = new PhoneValidator();

    @Test
    void testHasOnlyNumbers() {
        assertTrue(phoVal.validatePhone("+37068543265","LT"));
    }

    @Test
    void testHasOnlyNumbersFail() {
        assertFalse(phoVal.validatePhone("LabaiSlaptas","LT"));
    }

    @Test
    void testPhoneNumberVariant() {
        assertTrue(phoVal.validatePhone("868156265","LT"));
    }

    @Test
    void testPhoneNumberVariantFail() {
        assertFalse(phoVal.validatePhone("868165","LT"));
    }

    @Test
    void testDifferentCountry() {
        assertTrue(phoVal.validatePhone("+12345678912","US"));
    }

    @Test
    void testDifferentCountryFail() {
        assertFalse(phoVal.validatePhone("+123456789","LT"));
    }

}