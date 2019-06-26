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
package us.fatehi.test.creditcardnumber;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.CardBrand;

public class CardBrandTest
{

  // https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm
  @Test
  public void americanExpress()
  {
    final long[] longCardNumbers = {
                                     378282246310005L,
                                     371449635398431L,
                                     378734493671000L };
    for (final long longCardNumber: longCardNumbers)
    {
      test(longCardNumber, CardBrand.AmericanExpress);
    }
  }

  // https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm
  @Test
  public void dinersClub()
  {
    final long[] longCardNumbers = { 30569309025904L, 38520000023237L };
    for (final long longCardNumber: longCardNumbers)
    {
      test(longCardNumber, CardBrand.DinersClub);
    }
  }

  // https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm
  @Test
  public void discover()
  {
    final long[] longCardNumbers = { 6011000990139424L, 6011111111111117L };
    for (final long longCardNumber: longCardNumbers)
    {
      test(longCardNumber, CardBrand.Discover);
    }
  }

  // https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm
  @Test
  public void jcb()
  {
    final long[] longCardNumbers = { 3530111333300000L, 3566002020360505L };
    for (final long longCardNumber: longCardNumbers)
    {
      test(longCardNumber, CardBrand.JCB);
    }
  }

  // https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm
  @Test
  public void masterCard()
  {
    final long[] longCardNumbers = { 5555555555554444L, 5105105105105100L };
    for (final long longCardNumber: longCardNumbers)
    {
      test(longCardNumber, CardBrand.MasterCard);
    }
  }

  @Test
  public void masterCard2Series_1negative()
  {
    final long longCardNumber = 2221000000000000L;
    for (long i = 1; i <= 10; i++)
    {
      test(longCardNumber - i, CardBrand.Unknown);
    }
  }

  @Test
  public void masterCard2Series_1positive()
  {
    final long longCardNumber = 2221000000000000L;
    for (long i = 0; i <= 10; i++)
    {
      test(longCardNumber + i, CardBrand.MasterCard);
    }
  }

  @Test
  public void masterCard2Series_2negative()
  {
    final long longCardNumber = 2720999999999999L;
    for (long i = 1; i <= 10; i++)
    {
      test(longCardNumber + i, CardBrand.Unknown);
    }
  }

  @Test
  public void masterCard2Series_2positive()
  {
    final long longCardNumber = 2720999999999999L;
    for (long i = 0; i <= 10; i++)
    {
      test(longCardNumber - i, CardBrand.MasterCard);
    }
  }

  // https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm
  @Test
  public void visa()
  {
    final long[] longCardNumbers = {
                                     4111111111111111L,
                                     4012888888881881L,
                                     4222222222222L };
    for (final long longCardNumber: longCardNumbers)
    {
      test(longCardNumber, CardBrand.Visa);
    }
  }

  private void test(final long longCardNumber,
                    final CardBrand expectedCardBrand)
  {
    final String cardNumber = String.valueOf(longCardNumber);
    final CardBrand cardBrand = CardBrand.from(cardNumber);
    assertThat(cardNumber,  cardBrand, is(sameInstance(expectedCardBrand)));
  }

}
