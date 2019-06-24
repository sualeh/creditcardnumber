/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2016, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */
package us.fatehi.creditcardnumber;


import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.regex.Pattern;

/**
 * Card network that issued the bank card.
 *
 * See <a href=
 *      "http://www.regular-expressions.info/creditcard.html">Finding or
 *      Verifying Credit Card Numbers</a>
 * See <a href=
 *      "http://stackoverflow.com/questions/72768/how-do-you-detect-credit-card-type-based-on-number">How
 *      do you detect Credit card type based on number?</a>
 * @author Sualeh Fatehi
 */
public enum CardBrand
{

 Unknown(Pattern.compile("^unknown$")),
 Visa(Pattern.compile("^4[0-9]{6,}$")),
 // MasterCard numbers start with the numbers 51 through 55, and 2221
 // through 2720.
 MasterCard(Pattern
   .compile("^5[1-5][0-9]{5,}$|^(222[1-9]|2[3-6][0-9][0-9]|27[0-1][0-9]|2720)[0-9]{12}$")),
 AmericanExpress(Pattern.compile("^3[47][0-9]{5,}$")),
 // Diners Club card numbers begin with 300 through 305, 36 or 38.
 DinersClub(Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{4,}$")),
 Discover(Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{3,}$")),
 JCB(Pattern.compile("^(?:2131|1800|35[0-9]{3})[0-9]{3,}$")),;

  public static CardBrand from(final String accountNumber)
  {
    if (isBlank(accountNumber))
    {
      return Unknown;
    }
    for (final CardBrand cardBrand: values())
    {
      if (cardBrand.pattern.matcher(accountNumber).matches())
      {
        return cardBrand;
      }
    }
    return Unknown;
  }

  private final Pattern pattern;

  private CardBrand(final Pattern pattern)
  {
    this.pattern = pattern;
  }

}
