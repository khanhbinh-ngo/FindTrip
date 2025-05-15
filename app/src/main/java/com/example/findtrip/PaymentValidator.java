

package com.example.findtrip;

/**
 * Utility class for credit card validation
 */
public class PaymentValidator {

     /**
     * Validates a credit card number using the Luhn algorithm
     * @param cardNumber The card number to validate
     * @return true if the card number is valid according to the Luhn algorithm
     */
    public static boolean isValidCardNumber(String cardNumber) {
        // Remove any non-digit characters
        String digits = cardNumber.replaceAll("\\D", "");

        if (digits.length() < 13 || digits.length() > 19) {
            return false;
        }

        // Luhn algorithm implementation
        int sum = 0;
        boolean alternate = false;

        // Process digits from right to left
        for (int i = digits.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(digits.substring(i, i + 1));

            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }

            sum += n;
            alternate = !alternate;
        }

        // If sum is divisible by 10, the number is valid
        return (sum % 10 == 0);
    }

    /**
     * Gets the card type based on the card number
     * @param cardNumber The card number
     * @return The card type (VISA, MASTERCARD, AMEX, etc.) or UNKNOWN
     */
    public static CardType getCardType(String cardNumber) {
        // Remove any non-digit characters
        String digits = cardNumber.replaceAll("\\D", "");

        // Check card type based on prefix and length
        if (digits.startsWith("4")) {
            return CardType.VISA;
        } else if (digits.matches("^5[1-5].*")) {
            return CardType.MASTERCARD;
        } else if (digits.matches("^3[47].*")) {
            return CardType.AMEX;
        } else if (digits.matches("^6(?:011|5).*")) {
            return CardType.DISCOVER;
        } else {
            return CardType.UNKNOWN;
        }
    }

    /**
     * Enum representing credit card types
     */
    public enum CardType {
        VISA,
        MASTERCARD,
        AMEX,
        DISCOVER,
        UNKNOWN
    }
}
