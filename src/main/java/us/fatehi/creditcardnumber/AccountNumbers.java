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

  public static AccountNumber accountNumber(final String rawAccountNumber) {
    if (rawAccountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    } else {
      return new AccountNumberComplete(rawAccountNumber);
    }
  }

  public static AccountNumber emptyAccountNumber() {
    return ACCOUNT_NUMBER_EMPTY;
  }

  public static AccountNumber secureAccountNumber(final String rawAccountNumber) {
    final AccountNumberComplete pan = new AccountNumberComplete(rawAccountNumber);
    pan.disposeRawData();
    return pan.toSecureAccountNumber();
  }

  private AccountNumbers() {
    // Prevent instantiation
  }
}
