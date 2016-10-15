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


import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.right;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;
import us.fatehi.creditcardnumber.PrimaryAccountNumber;

public class PrimaryAccountNumberTest
{

  @Test
  public void pan_1()
  {
    final String rawAccountNumber = null;
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(rawAccountNumber);
    assertEquals(rawAccountNumber, pan.getRawData());
    assertTrue(!pan.hasAccountNumber());
  }

  @Test
  public void pan_2()
  {
    final String rawAccountNumber = "\t\t";
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(rawAccountNumber);
    assertEquals(rawAccountNumber, pan.getRawData());
    assertTrue(!pan.hasAccountNumber());
  }

  @Test
  public void pan_3()
  {
    final String rawAccountNumber = "5266092201416173";
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(rawAccountNumber);
    assertEquals(rawAccountNumber, pan.getRawData());
    assertTrue("Should not pass Luhn check", !pan.passesLuhnCheck());
    check(rawAccountNumber, pan, rawAccountNumber);
  }

  @Test
  public void pan1()
  {
    final String rawAccountNumber = "5266092201416174";
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(rawAccountNumber);
    assertTrue("Does not pass Luhn check", pan.passesLuhnCheck());
    check(rawAccountNumber, pan, rawAccountNumber);
  }

  @Test
  public void pan2()
  {
    final String rawAccountNumber = "  5266-0922-0141-6174  ";
    final String accountNumber = "5266092201416174";
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(rawAccountNumber);
    assertTrue("Does not pass Luhn check", pan.passesLuhnCheck());
    check(rawAccountNumber, pan, accountNumber);
  }

  @Test
  public void pan3()
  {
    final String rawAccountNumber = "  5266-0922-0141-6174-7889  ";
    final String accountNumber = "52660922014161747889";
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(rawAccountNumber);
    assertTrue("Should not pass Luhn check", !pan.passesLuhnCheck());
    assertTrue("Should not pass Luhn check", pan.exceedsMaximumLength());
    check(rawAccountNumber, pan, accountNumber);
  }

  @Test
  public void pan4()
  {
    final String rawAccountNumber = "  5266-0922  ";
    final String accountNumber = "52660922";
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(rawAccountNumber);
    assertTrue("Should not pass Luhn check", !pan.passesLuhnCheck());
    check(rawAccountNumber, pan, accountNumber);
  }

  private void check(final String rawAccountNumber,
                     final PrimaryAccountNumber pan,
                     final String accountNumber)
  {
    assertEquals(rawAccountNumber, pan.getRawData());
    assertEquals(accountNumber, pan.getAccountNumber());
    assertEquals(right(accountNumber, 4), pan.getLastFourDigits());
    assertEquals(left(accountNumber, 6), pan.getIssuerIdentificationNumber());
    assertEquals(CardBrand.MasterCard, pan.getCardBrand());
    assertEquals(MajorIndustryIdentifier.mii_5,
                 pan.getMajorIndustryIdentifier());
  }

}
