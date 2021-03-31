/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import java.io.Serializable;

final class AccountNumberInfo implements Serializable {

  private static final long serialVersionUID = 2002490292247684624L;

  private final CardBrand cardBrand;
  private final MajorIndustryIdentifier majorIndustryIdentifier;
  private final boolean passesLuhnCheck;
  private final int accountNumberLength;
  private final boolean isLengthValid;
  private final boolean isPrimaryAccountNumberValid;
  private final boolean isExceedsMaximumLength;

  public AccountNumberInfo(
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
    final AccountNumberInfo other = (AccountNumberInfo) obj;
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

  public boolean exceedsMaximumLength() {
    return isExceedsMaximumLength;
  }

  public int getAccountNumberLength() {
    return accountNumberLength;
  }

  public CardBrand getCardBrand() {
    return cardBrand;
  }

  public MajorIndustryIdentifier getMajorIndustryIdentifier() {
    return majorIndustryIdentifier;
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

  public boolean isLengthValid() {
    return isLengthValid;
  }

  public boolean isPrimaryAccountNumberValid() {
    return isPrimaryAccountNumberValid;
  }

  public boolean passesLuhnCheck() {
    return passesLuhnCheck;
  }

  @Override
  public String toString() {
    return "AccountNumberInfo [cardBrand="
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
