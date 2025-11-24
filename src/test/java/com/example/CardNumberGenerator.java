/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2026, Sualeh Fatehi.
 *
 */

package com.example;

import java.util.Random;

public class CardNumberGenerator {

  public static int calculateCheckDigit(final String cardNumber) {
    int sum = 0;
    boolean alternate = true;

    for (int i = cardNumber.length() - 1; i >= 0; i--) {
      int n = Integer.parseInt(cardNumber.substring(i, i + 1));

      if (alternate) {
        n *= 2;
        if (n > 9) {
          n = n % 10 + 1;
        }
      }

      sum += n;
      alternate = !alternate;
    }

    return (10 - sum % 10) % 10;
  }

  public static String generateRandomCardNumber(final String bin) {
    final Random random = new Random();
    final StringBuilder cardNumber = new StringBuilder(bin);

    // Generate the remaining digits except the last one
    while (cardNumber.length() < 15) {
      cardNumber.append(random.nextInt(10));
    }

    // Calculate the check digit
    final int checkDigit = calculateCheckDigit(cardNumber.toString());
    cardNumber.append(checkDigit);

    return cardNumber.toString();
  }

  public static void main(final String[] args) {
    final String bin = args[0];
    final String cardNumber = generateRandomCardNumber(bin);
    System.out.println("Generated Card Number: " + cardNumber);
  }
}
