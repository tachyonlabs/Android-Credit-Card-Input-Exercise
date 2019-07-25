package com.tachyonlabs.creditcardinputexercise;

import com.tachyonlabs.creditcardinputexercise.data.CreditCard;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CreditCardUnitTests {
    // Sample card numbers courtesy of https://www.cybersource.com/developers/other_resources/quick_references/test_cc_numbers/
    // except for the Discover one, which didn't meet their specs from https://www.cybersource.com/developers/getting_started/test_and_manage/best_practices/card_type_id/
    // so I used this one instead: 6011011111111119
    // There should be tests of additional valid numbers and invalid numbers/cvv combinations too;
    // these are just for some examples.
    @Test
    public void validAmexCardNumber() {
        assertTrue(CreditCard.isValidCardNumber("378282246310005"));
    }

    @Test
    public void validAmexCvv() {
        assertTrue(CreditCard.isValidCvv("378282246310005", "1234"));
    }

    @Test
    public void validDiscoverCardNumber() {
        assertTrue(CreditCard.isValidCardNumber("6011011111111119"));
    }

    @Test
    public void validDiscoverCvv() {
        assertTrue(CreditCard.isValidCvv("6011011111111119", "234"));
    }

    @Test
    public void validMasterCardCardNumber() {
        assertTrue(CreditCard.isValidCardNumber("2222420000001113"));
    }

    @Test
    public void validMasterCardCvv() {
        assertTrue(CreditCard.isValidCvv("5555555555554444", "987"));
    }

    @Test
    public void validVisaCardNumber() {
        assertTrue(CreditCard.isValidCardNumber("4111111111111111"));
    }

    @Test
    public void validVisaCvv() {
        assertTrue(CreditCard.isValidCvv("4111111111111111", "597"));
    }

}
