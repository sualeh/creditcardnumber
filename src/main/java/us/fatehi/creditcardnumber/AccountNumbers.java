/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

public final class AccountNumbers {

  private static final AccountNumber ACCOUNT_NUMBER_EMPTY = new AccountNumberEmpty();

  /**
   * Parses the primary account number of the bank card. Can accept card numbers with spaces or
   * dashes.
   *
   * @param rawAccountNumber Raw primary account number.
   */
  public static AccountNumber accountNumber(final String rawAccountNumber) {
    if (rawAccountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    } else {
      return new AccountNumberComplete(rawAccountNumber);
    }
  }

  /**
   * Parses the primary account number of the bank card. Can accept card numbers with spaces or
   * dashes. Store only the last digits, and account number metadata.
   *
   * @param rawAccountNumber Raw primary account number.
   */
  public static AccountNumber accountNumberLastFour(final String rawAccountNumber) {
    if (rawAccountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    } else {
      return new AccountNumberLastFour(rawAccountNumber);
    }
  }

  /** Represents a missing account number. */
  public static AccountNumber emptyAccountNumber() {
    return ACCOUNT_NUMBER_EMPTY;
  }

  /**
   * Since insecure account numbers can mutate and become secure by calling dispose(), there are no
   * equals(...) and hashCode() methods provided. Use this method to check if two instances are
   * equal at a given point of time.
   *
   * @param pan1 First account number to compare
   * @param pan2 Second account number to compare
   * @return If the two account numbers are available, and those are equal
   */
  public static boolean equals(final AccountNumber pan1, final AccountNumber pan2) {
    if (pan1 == null || pan2 == null) {
      return false;
    }
    if (pan1 == pan2) {
      return true;
    }
    if (!pan1.hasAccountNumber() || !pan2.hasAccountNumber()) {
      return false;
    }
    return pan1.getAccountNumber().equals(pan2.getAccountNumber());
  }

  /**
   * Parses the primary account number of the bank card. Can accept card numbers with spaces or
   * dashes. Store only account number metadata.
   *
   * @param rawAccountNumber Raw primary account number.
   */
  public static AccountNumber secureAccountNumber(final String rawAccountNumber) {
    final AccountNumberComplete pan = new AccountNumberComplete(rawAccountNumber);
    pan.disposeRawData();
    return pan.toSecureAccountNumber();
  }

  private AccountNumbers() {
    // Prevent instantiation
  }
}
