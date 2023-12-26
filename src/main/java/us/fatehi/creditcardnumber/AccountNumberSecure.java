/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2024, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static java.util.Objects.requireNonNull;

final class AccountNumberSecure implements AccountNumber {

  private static final long serialVersionUID = 2002490292247684624L;

  private final CardBrand cardBrand;
  private final MajorIndustryIdentifier majorIndustryIdentifier;
  private final boolean passesLuhnCheck;
  private final int accountNumberLength;
  private final boolean isLengthValid;
  private final boolean isPrimaryAccountNumberValid;
  private final boolean isExceedsMaximumLength;

  /**
   * Captures secure information (metadata) that has no part of the actual account number in it.
   *
   * @param accountNumber Account number with metadata
   */
  AccountNumberSecure(final AccountNumber accountNumber) {
    this(
        requireNonNull(accountNumber, "No account number provided").getCardBrand(),
        accountNumber.getMajorIndustryIdentifier(),
        accountNumber.passesLuhnCheck(),
        accountNumber.getAccountNumberLength(),
        accountNumber.isLengthValid(),
        accountNumber.isPrimaryAccountNumberValid(),
        accountNumber.exceedsMaximumLength());
  }

  AccountNumberSecure(
      final CardBrand cardBrand,
      final MajorIndustryIdentifier majorIndustryIdentifier,
      final boolean passesLuhnCheck,
      final int accountNumberLength,
      final boolean isLengthValid,
      final boolean isPrimaryAccountNumberValid,
      final boolean isExceedsMaximumLength) {
    this.cardBrand = requireNonNull(cardBrand, "No card brand provided");
    this.majorIndustryIdentifier =
        requireNonNull(majorIndustryIdentifier, "No major indutry identifier provided");
    this.passesLuhnCheck = passesLuhnCheck;
    this.accountNumberLength = accountNumberLength;
    this.isLengthValid = isLengthValid;
    this.isPrimaryAccountNumberValid = isPrimaryAccountNumberValid;
    this.isExceedsMaximumLength = isExceedsMaximumLength;
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
    return isExceedsMaximumLength;
  }

  @Override
  public String getAccountNumber() {
    return null;
  }

  @Override
  public int getAccountNumberLength() {
    return accountNumberLength;
  }

  @Override
  public CardBrand getCardBrand() {
    return cardBrand;
  }

  @Override
  public String getIssuerIdentificationNumber() {
    return null;
  }

  @Override
  public String getLastFourDigits() {
    return null;
  }

  @Override
  public MajorIndustryIdentifier getMajorIndustryIdentifier() {
    return majorIndustryIdentifier;
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
    return isLengthValid;
  }

  @Override
  public boolean isPrimaryAccountNumberValid() {
    return isPrimaryAccountNumberValid;
  }

  @Override
  public boolean passesLuhnCheck() {
    return passesLuhnCheck;
  }

  @Override
  public AccountNumber toSecureAccountNumber() {
    return this;
  }

  @Override
  public String toString() {
    if (cardBrand == CardBrand.Unknown) {
      return majorIndustryIdentifier.getDescription();
    } else {
      return cardBrand.toString();
    }
  }
}
