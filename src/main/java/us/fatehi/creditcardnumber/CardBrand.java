/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.regex.Pattern;

/**
 * Card network that issued the bank card.
 *
 * <p>See <a href= "http://www.regular-expressions.info/creditcard.html">Finding or Verifying Credit
 * Card Numbers</a> See <a href=
 * "http://stackoverflow.com/questions/72768/how-do-you-detect-credit-card-type-based-on-number">How
 * do you detect Credit card type based on number?</a>
 *
 * @author Sualeh Fatehi
 */
public enum CardBrand {
  Unknown("^unknown$"),
  // Visa can be 13, 16 or 19 digits in length
  Visa("^4[0-9]{12}(?:[0-9]{3}){0,2}$"),
  // MasterCard numbers start with the numbers 51 through 55, and 2221
  // through 2720.
  MasterCard("^(?:5[1-5][0-9]{2}|222[1-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$"),
  AmericanExpress("^3[47][0-9]{13}$"),
  // Discover can be 16 or 19 digits in length
  Discover("^6(?:011|5[0-9]{2})[0-9]{12}(?:[0-9]{3})?$"),
  MIR("^220[0-4][0-9]{12}$"),
  // China Unionpay can be 16 or 19 digits in length
  UnionPay("^62[0-9]{14}(?:[0-9]{3})?$"),
  // Diners Club card numbers begin with 300 through 305, 36 or 38.
  DinersClub("^3(?:0[0-5]|[68][0-9])[0-9]{11}$"),
  // Maestro can be 12-19 digits in length
  Maestro("^(?:5018|5020|5038|5893|6304|6759|676[1-3])[0-9]{8,15}$"),
  JCB("^(?:2131|1800|35[0-9]{3})[0-9]{11}$"),
  UATP("^1[0-9]{14}$");

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

  CardBrand(final String patternRegEx) {
    this.pattern = Pattern.compile(patternRegEx);
  }
}
