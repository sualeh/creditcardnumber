/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

public final class AccountNumbers {

  public static AccountNumber newAccountNumber(final String rawAccountNumber) {
    return new BankCardAccountNumber(rawAccountNumber);
  }

  public static AccountNumber newSecureAccountNumber(final String rawAccountNumber) {
    return new BankCardAccountNumber(rawAccountNumber).toSecureAccountNumber();
  }

  private AccountNumbers() {
    // Prevent instantiation
  }
}
