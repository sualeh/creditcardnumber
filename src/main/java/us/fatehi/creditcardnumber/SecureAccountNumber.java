/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

final class SecureAccountNumber implements AccountNumber {

  private static final long serialVersionUID = 2002490292247684624L;

  private final CardBrand cardBrand;
  private final MajorIndustryIdentifier majorIndustryIdentifier;
  private final boolean passesLuhnCheck;
  private final int accountNumberLength;
  private final boolean isLengthValid;
  private final boolean isPrimaryAccountNumberValid;
  private final boolean isExceedsMaximumLength;

  public SecureAccountNumber(
      final CardBrand cardBrand,
      final MajorIndustryIdentifier majorIndustryIdentifier,
      final boolean passesLuhnCheck,
      final int accountNumberLength,
      final boolean isLengthValid,
      final boolean isPrimaryAccountNumberValid,
      final boolean isExceedsMaximumLength) {
    this.cardBrand = cardBrand;
    this.majorIndustryIdentifier = majorIndustryIdentifier;
    this.passesLuhnCheck = passesLuhnCheck;
    this.accountNumberLength = accountNumberLength;
    this.isLengthValid = isLengthValid;
    this.isPrimaryAccountNumberValid = isPrimaryAccountNumberValid;
    this.isExceedsMaximumLength = isExceedsMaximumLength;
  }

  @Override
  public void disposeRawData() {
    // No-op
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final SecureAccountNumber other = (SecureAccountNumber) obj;
    if (accountNumberLength != other.accountNumberLength) {
      return false;
    }
    if (cardBrand != other.cardBrand) {
      return false;
    }
    if (isPrimaryAccountNumberValid != other.isPrimaryAccountNumberValid) {
      return false;
    }
    if (majorIndustryIdentifier != other.majorIndustryIdentifier) {
      return false;
    }
    if (passesLuhnCheck != other.passesLuhnCheck) {
      return false;
    }
    return true;
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
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + accountNumberLength;
    result = prime * result + (cardBrand == null ? 0 : cardBrand.hashCode());
    result = prime * result + (isPrimaryAccountNumberValid ? 1231 : 1237);
    result =
        prime * result + (majorIndustryIdentifier == null ? 0 : majorIndustryIdentifier.hashCode());
    result = prime * result + (passesLuhnCheck ? 1231 : 1237);
    return result;
  }

  @Override
  public boolean hasIssuerIdentificationNumber() {
    return false;
  }

  @Override
  public boolean hasLastFourDigits() {
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
    return "AccountNumber [cardBrand="
        + cardBrand
        + ", majorIndustryIdentifier="
        + majorIndustryIdentifier
        + ", passesLuhnCheck="
        + passesLuhnCheck
        + ", accountNumberLength="
        + accountNumberLength
        + ", isLengthValid="
        + isLengthValid
        + ", isPrimaryAccountNumberValid="
        + isPrimaryAccountNumberValid
        + ", isExceedsMaximumLength="
        + isExceedsMaximumLength
        + "]";
  }
}
