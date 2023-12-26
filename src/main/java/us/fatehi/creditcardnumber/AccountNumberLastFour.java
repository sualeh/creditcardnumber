/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2024, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

final class AccountNumberLastFour implements AccountNumber {

  private static final long serialVersionUID = -7012531091389412459L;

  private final AccountNumber panSecure;
  private final String last4;

  AccountNumberLastFour(final String rawAccountNumber) {

    // Make sure that the data between the last 4 and secure card information is consistent, so have
    // only one constructor that takes the whole card number.
    final AccountNumber accountNumberComplete = new AccountNumberComplete(rawAccountNumber);

    last4 = accountNumberComplete.getLastFourDigits();
    panSecure = accountNumberComplete.toSecureAccountNumber();

    accountNumberComplete.disposeRawData();
  }

  @Override
  public void dispose() {
    // No-op
  }

  @Override
  public void disposeRawData() {
    // No-op
  }

  @Override
  public boolean exceedsMaximumLength() {
    return panSecure.exceedsMaximumLength();
  }

  @Override
  public String getAccountNumber() {
    return null;
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
    return null;
  }

  @Override
  public String getLastFourDigits() {
    return last4;
  }

  @Override
  public MajorIndustryIdentifier getMajorIndustryIdentifier() {
    return panSecure.getMajorIndustryIdentifier();
  }

  @Override
  public String getRawData() {
    return null;
  }

  @Override
  public boolean hasAccountNumber() {
    return false;
  }

  @Override
  public boolean hasRawData() {
    return false;
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
    return String.format("%s-%s", getCardBrand(), last4);
  }
}
