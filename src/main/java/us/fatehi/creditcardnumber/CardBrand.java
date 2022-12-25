/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2023, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.regex.Pattern;

/**
 * Card network that issued the bank card. Attempt to do a best-guess at the card brand by just the
 * first four digits of a card number, to allow for type-ahead logic. A slightly fuller check is
 * done by checking if the number of digits is right (length check). Additionally, a Luhn check is
 * done on the account number as well.
 *
 * <p>See:
 *
 * <ul>
 *   <li><a href= "http://www.regular-expressions.info/creditcard.html">Finding or Verifying Credit
 *       Card Numbers</a>
 *   <li><a href=
 *       "http://stackoverflow.com/questions/72768/how-do-you-detect-credit-card-type-based-on-number">How
 *       do you detect Credit card type based on number?</a>
 */
public enum CardBrand {
  Unknown(
      "^unknown$",
      new Predicate<Integer>() {

        @Override
        public boolean test(final Integer length) {
          return length > 6;
        }
      }),

  // Visa numbers start with 4
  Visa("^4[0-9]{3,}$", new LengthCheck(13, 16, 19)),

  // MasterCard numbers start with the numbers 51 through 55, and 2221
  // through 2720
  MasterCard(
      "^(?:5[1-5][0-9]{2}|222[1-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]*$", new LengthCheck(16)),

  // American Express numbers start with 35 or 37
  AmericanExpress("^3[47][0-9]{2,}$", new LengthCheck(15)),

  // Discover numbers start with 6011 or 65 (though Discover also processes for other
  // networks such as UnionPay)
  // See
  // https://web.archive.org/web/20170822221741/https://www.discovernetwork.com/downloads/IPP_VAR_Compliance.pdf
  Discover("^6(?:011|5[0-9]{3})[0-9]*$", new LengthCheck(16, 19)),

  // MIR can be 16-19 digits in length.
  MIR("^220[0-4][0-9]*$", new LengthRangeCheck(16, 19)),

  // UnionPay numbers start with 62
  UnionPay("^62[0-9]{2,}$", new LengthRangeCheck(16, 19)),

  // RuPay numbers start with 60, 6521, or 6522
  RuPay("^(?:60[0-9]{2}|6521|6522)[0-9]*$", new LengthCheck(16)),

  // Diners Club card numbers begin with 300 through 305, 36 or 38
  DinersClub("^3(?:0[0-5]|[68][0-9])[0-9]{1,}$", new LengthRangeCheck(14, 19)),

  // Maestro has multiple ranges
  Maestro("^(?:5018|5020|5038|5893|6304|6759|676[1-3])[0-9]*$", new LengthRangeCheck(12, 19)),

  // JCB can begin with 1800 or 2131 and be 15 digits in length, or
  // begin with 35 and be 16-19 digits in length.
  JCB("^(?:2131|1800|35[0-9]{2})[0-9]*$", new LengthRangeCheck(15, 19)),

  // Universal Air Travel Plan numbers start with 1
  UATP("^1[0-9]{3,}$", new LengthCheck(15));

  private static final class LengthCheck implements Predicate<Integer> {

    private final int[] validLengths;

    LengthCheck(final int... validLengths) {
      this.validLengths = validLengths;
    }

    @Override
    public boolean test(final Integer length) {
      for (final int validLength : validLengths) {
        if (length == validLength) {
          return true;
        }
      }
      return false;
    }
  }

  private static final class LengthRangeCheck implements Predicate<Integer> {

    private final int minLength;
    private final int maxLength;

    LengthRangeCheck(final int minLength, final int maxLength) {
      this.minLength = minLength;
      this.maxLength = maxLength;
    }

    @Override
    public boolean test(final Integer length) {
      return length >= minLength && length <= maxLength;
    }
  }

  /**
   * Java 8 forward-compatible version of what should be a lambda. Written to compile in Java 7 for
   * use in Andriod projects.
   */
  private static interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     */
    boolean test(T t);
  }

  public static CardBrand from(final String accountNumber) {
    if (isBlank(accountNumber)) {
      return Unknown;
    }
    for (final CardBrand cardBrand : values()) {
      if (cardBrand.pattern.matcher(accountNumber).matches()) {
        return cardBrand;
      }
    }
    return Unknown;
  }

  private final Pattern pattern;
  private final Predicate<Integer> lengthCheck;

  CardBrand(final String patternRegEx, final Predicate<Integer> lengthCheck) {
    this.pattern = Pattern.compile(patternRegEx);
    this.lengthCheck = lengthCheck;
  }

  public boolean isLengthValid(final int accountNumberLength) {
    return lengthCheck.test(accountNumberLength);
  }
}
