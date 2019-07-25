package com.tachyonlabs.creditcardinputexercise.data;

import java.util.Calendar;
import java.util.Locale;

public class CreditCard {
    private String creditCardNumber;
    private String expirationDate;
    private String cvv;
    private String firstName;
    private String lastName;

    public CreditCard() {
        this.creditCardNumber = null;
        this.expirationDate = null;
        this.cvv = null;
        this.firstName = null;
        this.lastName = null;
    }

    public CreditCard(String creditCardNumber, String expirationDate, String cvv, String firstName, String lastName) {
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static boolean isValidCardNumber(String cardNumber) {
        // the helper methods follow requirements from
        // https://www.cybersource.com/developers/getting_started/test_and_manage/best_practices/card_type_id/
        return luhnValidation(cardNumber) && (isValidAmex(cardNumber) || isValidDiscover(cardNumber) ||
                isValidMasterCard(cardNumber) || isValidVisa(cardNumber));
    }

    private static boolean luhnValidation(String cardNumber) {
        // see https://en.wikipedia.org/wiki/Luhn_algorithm
        int total = 0;
        for (int i = 1; i <= cardNumber.length(); i++) {
            int digit = Character.getNumericValue(cardNumber.charAt(cardNumber.length() - i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            total += digit;
        }
        return total % 10 == 0;
    }

    private static boolean isValidAmex(String cardNumber) {
        // see https://www.cybersource.com/developers/getting_started/test_and_manage/best_practices/card_type_id/
        return cardNumber.length() == 15 && cardNumber.charAt(0) == '3' && (cardNumber.charAt(1) == '4' || cardNumber.charAt(1) == '7');
    }

    private static boolean isValidDiscover(String cardNumber) {
        // see https://www.cybersource.com/developers/getting_started/test_and_manage/best_practices/card_type_id/
        if (cardNumber.length() == 16) {
            if (cardNumber.startsWith("6011")) {
                int fifthAndSixth = Integer.parseInt(cardNumber.substring(4, 6));
                return (fifthAndSixth >= 0 && fifthAndSixth <= 9) || (fifthAndSixth >= 20 && fifthAndSixth <= 49) ||
                        fifthAndSixth == 74 || (fifthAndSixth >= 77 && fifthAndSixth <= 79) ||
                        (fifthAndSixth >= 86 && fifthAndSixth <= 99);
            } else if (cardNumber.charAt(0) == '6') {
                int secondAndThird = Integer.parseInt(cardNumber.substring(1, 3));
                return secondAndThird >= 44 && secondAndThird <= 59;
            }
        }
        return false;
    }

    private static boolean isValidMasterCard(String cardNumber) {
        // see https://www.cybersource.com/developers/getting_started/test_and_manage/best_practices/card_type_id/
        return cardNumber.length() == 16 &&
                ((cardNumber.charAt(0) == '5' && cardNumber.charAt(1) >= '1' && cardNumber.charAt(1) <= '5') ||
                        (cardNumber.charAt(0) == '2' && cardNumber.charAt(1) >= '2' && cardNumber.charAt(1) <= '7'));
    }

    private static boolean isValidVisa(String cardNumber) {
        // see https://www.cybersource.com/developers/getting_started/test_and_manage/best_practices/card_type_id/
        return cardNumber.length() >= 16 && cardNumber.length() <= 19 && cardNumber.charAt(0) == '4';
    }

    public static boolean isValidExpirationDate(String expirationDate) {
        String[] expDateParts = expirationDate.split("/");
        if (expDateParts.length != 2 || expDateParts[0].isEmpty() || expDateParts[1].isEmpty()) {
            return false;
        }
        int expMonth = Integer.parseInt(expDateParts[0]);
        int expYear = Integer.parseInt(expDateParts[1]);
        if (!(expMonth >= 1 && expMonth <= 12)) {
            return false;
        }
        Calendar today = Calendar.getInstance(Locale.getDefault());
        int todayMonth = today.get(Calendar.MONTH) + 1;
        int todayYear = today.get(Calendar.YEAR) % 100;
        return (expYear > todayYear) || (expYear == todayYear && expMonth >= todayMonth);
    }

    public static boolean isValidCvv(String cardNumber, String cvv) {
        // see https://www.cvvnumber.com/cvv.html as linked to in the specs
        return (isValidAmex(cardNumber) && cvv.length() == 4) ||
                ((isValidDiscover(cardNumber) || isValidMasterCard(cardNumber) || isValidVisa(cardNumber)) && cvv.length() == 3);
    }

    public static String cleanName(String firstOrLastName) {
        // strip leading and trailing spaces, change any consecutive multiple internal spaces to one
        return firstOrLastName.trim().replaceAll("\\s+", " ");
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
