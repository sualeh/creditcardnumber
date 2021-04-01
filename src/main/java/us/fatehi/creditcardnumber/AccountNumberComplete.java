/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.right;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.util.Arrays;

final class AccountNumberComplete extends BaseRawData implements AccountNumber {

  private static final long serialVersionUID = -7012531091389412459L;

  private static final int IIN_LEN = 8;

  private final DisposableStringData accountNumber;
  private final AccountNumber panSecure;

  AccountNumberComplete(final String rawAccountNumber) {
    super(requireNonNull(rawAccountNumber, "No raw account number provided"));

    final String accountNumberString = parseAccountNumber(trimToEmpty(rawAccountNumber));
    accountNumber = new DisposableStringData(accountNumberString);

    final boolean passesLuhnCheck = luhnCheck();
    final MajorIndustryIdentifier majorIndustryIdentifier =
        MajorIndustryIdentifier.from(accountNumberString);
    final CardBrand cardBrand = CardBrand.from(accountNumberString);
    final int accountNumberLength = accountNumberString.length();
    final boolean isLengthValid = Arrays.asList(13, 14, 15, 16, 19).contains(accountNumberLength);
    final boolean isPrimaryAccountNumberValid =
        isLengthValid && passesLuhnCheck && cardBrand != CardBrand.Unknown;
    final boolean exceedsMaximumLength = accountNumberLength > 19;

    panSecure =
        new AccountNumberSecure(
            cardBrand,
            majorIndustryIdentifier,
            passesLuhnCheck,
            accountNumberLength,
            isLengthValid,
            isPrimaryAccountNumberValid,
            exceedsMaximumLength);
  }

  @Override
  public void dispose() {
    super.disposeRawData();
    accountNumber.disposeData();
  }

  @Override
  public boolean exceedsMaximumLength() {
    return panSecure.exceedsMaximumLength();
  }

  @Override
  public String getAccountNumber() {
    return accountNumber.getData();
  }

  @Override
  public int getAccountNumberLength() {
    return panSecure.getAccountNumberLength();
  }

  @Override
  public CardBrand getCardBrand() {
    return panSecure.getCardBrand();
  }

  @Override
  public String getIssuerIdentificationNumber() {
    if (!hasRawData()) {
      return null;
    }
    final String accountNumberString = accountNumber.getData();
    return rightPad(left(accountNumberString, IIN_LEN), IIN_LEN, "0");
  }

  @Override
  public String getLastFourDigits() {
    if (!hasRawData()) {
      return null;
    }
    final String accountNumberString = accountNumber.getData();
    return leftPad(right(accountNumberString, 4), 4, "0");
  }

  @Override
  public MajorIndustryIdentifier getMajorIndustryIdentifier() {
    return panSecure.getMajorIndustryIdentifier();
  }

  @Override
  public boolean hasAccountNumber() {
    return accountNumber.hasData();
  }

  @Override
  public boolean isLengthValid() {
    return panSecure.isLengthValid();
  }

  @Override
  public boolean isPrimaryAccountNumberValid() {
    return panSecure.isPrimaryAccountNumberValid();
  }

  @Override
  public boolean passesLuhnCheck() {
    return panSecure.passesLuhnCheck();
  }

  @Override
  public AccountNumber toSecureAccountNumber() {
    return panSecure;
  }

  @Override
  public String toString() {
    if (hasAccountNumber()) {
      return getAccountNumber();
    } else {
      return panSecure.toString();
    }
  }

  private boolean luhnCheck() {
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

  private String parseAccountNumber(final String rawAccountNumber) {
    if (rawAccountNumber == null) {
      return "";
    }
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
}
