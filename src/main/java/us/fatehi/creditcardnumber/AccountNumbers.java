/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2025, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.security.Key;

import javax.crypto.Cipher;

public final class AccountNumbers {

  private static final AccountNumber ACCOUNT_NUMBER_EMPTY = new AccountNumberEmpty();

  /**
   * Return a an account number, metadata stored in memory, and the last 4 digits of the card number
   * if available.
   *
   * @param accountNumber Primary account number
   * @return Account number with last 4 digits and metadata or other secure account number
   */
  public static AccountNumber accountNumberLastFour(final AccountNumber accountNumber) {
    if (accountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    }

    if (!(accountNumber instanceof AccountNumberComplete) || !accountNumber.hasAccountNumber()) {
      return accountNumber;
    }

    return new AccountNumberLastFour(accountNumber.getAccountNumber());
  }

  /**
   * Parses the primary account number of the bank card. Can accept card numbers with spaces or
   * dashes. Store only the last digits, and account number metadata.
   *
   * @param rawAccountNumber Raw primary account number
   * @return Account number with last 4 digits and metadata or other secure account number
   */
  public static AccountNumber accountNumberLastFour(final String rawAccountNumber) {
    if (rawAccountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    } else {
      return new AccountNumberLastFour(rawAccountNumber);
    }
  }

  /**
   * Returns the complete account number.
   *
   * @param accountNumber Primary account number
   * @param key
   * @return Complete account number
   */
  public static AccountNumber completeAccountNumber(
      final AccountNumber accountNumber, final Key key) {
    if (accountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    } else if (accountNumber instanceof AccountNumberSealed) {
      AccountNumber completeAccountNumber;
      try {
        completeAccountNumber = ((AccountNumberSealed) accountNumber).toCompleteAccountNumber(key);
      } catch (final Exception e) {
        // TODO: Log exception - use plain Java logging?
        return accountNumber;
      }
      return completeAccountNumber;
    } else {
      return accountNumber;
    }
  }

  /**
   * Parses the primary account number of the bank card. Can accept card numbers with spaces or
   * dashes.
   *
   * @param rawAccountNumber Raw primary account number.
   * @return Complete account number
   */
  public static AccountNumber completeAccountNumber(final String rawAccountNumber) {
    if (rawAccountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    } else {
      return new AccountNumberComplete(rawAccountNumber);
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
   * Return a sealed account number, with a cryptographically sealed account number and metadata
   * stored in memory. If this is not possible, return a secure version of the account number.
   *
   * @param accountNumber Primary account number
   * @param cipher Cipher to seal the account number
   * @return Sealed account number or other secure account number
   */
  public static AccountNumber sealedAccountNumber(
      final AccountNumber accountNumber, final Cipher cipher) {
    if (accountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    } else if (accountNumber instanceof AccountNumberSealed) {
      return accountNumber;
    } else if (!(accountNumber instanceof AccountNumberComplete)
        || !accountNumber.hasAccountNumber()) {
      return accountNumber.toSecureAccountNumber();
    } else {
      return new AccountNumberSealed(accountNumber.getAccountNumber(), cipher);
    }
  }

  /**
   * Parses the primary account number of the bank card. Can accept card numbers with spaces or
   * dashes. Store cryptograhically sealed account number and metadata.
   *
   * @param rawAccountNumber Raw primary account number.
   * @param cipher Cipher to seal the account number.
   * @return Sealed account number or other secure account number
   */
  public static AccountNumber sealedAccountNumber(
      final String rawAccountNumber, final Cipher cipher) {
    if (rawAccountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    } else {
      return new AccountNumberSealed(rawAccountNumber, cipher);
    }
  }

  /**
   * Return a secure account number, with only metadata stored in memory.
   *
   * @param accountNumber Primary account number
   * @return Secure account number
   */
  public static AccountNumber secureAccountNumber(final AccountNumber accountNumber) {
    if (accountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    }

    return accountNumber.toSecureAccountNumber();
  }

  /**
   * Parses the primary account number of the bank card. Can accept card numbers with spaces or
   * dashes. Store only account number metadata.
   *
   * @param rawAccountNumber Raw primary account number
   * @return Secure account number
   */
  public static AccountNumber secureAccountNumber(final String rawAccountNumber) {

    if (rawAccountNumber == null) {
      return ACCOUNT_NUMBER_EMPTY;
    }

    final String accountNumberString = parseAccountNumber(trimToEmpty(rawAccountNumber));

    // Secure information (metadata) that has no part of the actual account in it
    final boolean passesLuhnCheck = AccountNumbers.passesLuhnCheck(accountNumberString);
    final MajorIndustryIdentifier majorIndustryIdentifier =
        MajorIndustryIdentifier.from(accountNumberString);
    final CardBrand cardBrand = CardBrand.from(accountNumberString);
    final int accountNumberLength = accountNumberString.length();
    final boolean isLengthValid = cardBrand.isLengthValid(accountNumberLength);
    final boolean isPrimaryAccountNumberValid =
        isLengthValid && passesLuhnCheck && cardBrand != CardBrand.Unknown;
    final boolean exceedsMaximumLength = accountNumberLength > 19;

    final AccountNumberSecure panSecure =
        new AccountNumberSecure(
            cardBrand,
            majorIndustryIdentifier,
            passesLuhnCheck,
            accountNumberLength,
            isLengthValid,
            isPrimaryAccountNumberValid,
            exceedsMaximumLength);

    return panSecure;
  }

  static String parseAccountNumber(final String rawAccountNumber) {
    final StringBuilder builder = new StringBuilder();
    final int length = rawAccountNumber.length();
    for (int offset = 0; offset < length; ) {
      final int codepoint = rawAccountNumber.codePointAt(offset);
      if (Character.isDigit(codepoint)) {
        final int digit = Character.digit(codepoint, 10);
        builder.append(String.valueOf(digit));
      }
      offset += Character.charCount(codepoint);
    }
    return builder.toString();
  }

  static boolean passesLuhnCheck(final String accountNumber) {
    if (accountNumber == null || accountNumber.length() <= 4) {
      return false;
    }
    final int length = accountNumber.length();
    int sum = 0;
    boolean alternate = false;
    for (int i = length - 1; i >= 0; i--) {
      int digit = Character.digit(accountNumber.charAt(i), 10);
      if (alternate) {
        digit = digit * 2;
        digit = digit > 9 ? digit - 9 : digit;
      }
      sum = sum + digit;
      alternate = !alternate;
    }
    final boolean passesLuhnCheck = sum % 10 == 0;
    return passesLuhnCheck;
  }

  private AccountNumbers() {
    // Prevent instantiation
  }
}
