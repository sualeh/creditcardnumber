package us.fatehi.test.utility;

import us.fatehi.creditcardnumber.AccountNumber;

public class AccountNumbersTestUtility {

  public static boolean equivalent(final AccountNumber pan1, final AccountNumber pan2) {
    if (pan1 == pan2) {
      return true;
    }
    if (pan1 == null || pan2 == null) {
      return false;
    }

    // Compare metadata
    if (pan1.getCardBrand() != pan2.getCardBrand()) {
      return false;
    }
    if (pan1.getMajorIndustryIdentifier() != pan2.getMajorIndustryIdentifier()) {
      return false;
    }
    if (pan1.passesLuhnCheck() != pan2.passesLuhnCheck()) {
      return false;
    }
    if (pan1.getAccountNumberLength() != pan2.getAccountNumberLength()) {
      return false;
    }
    if (pan1.isLengthValid() != pan2.isLengthValid()) {
      return false;
    }
    if (pan1.isPrimaryAccountNumberValid() != pan2.isPrimaryAccountNumberValid()) {
      return false;
    }
    if (pan1.exceedsMaximumLength() != pan2.exceedsMaximumLength()) {
      return false;
    }

    return true;
  }

  private AccountNumbersTestUtility() {
    // Prevent instantation
  }
}
