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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;
import us.fatehi.creditcardnumber.AccountNumber;

public class SecureAccountNumberTest
{

  @Test
  public void pan_1()
  {
    final String rawAccountNumber = null;
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue(!pan.hasAccountNumber());
    pan.clearAccountNumber();
    assertTrue(!pan.hasAccountNumber());
  }

  @Test
  public void pan_2()
  {
    final String rawAccountNumber = "\t\t";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue(!pan.hasAccountNumber());
    pan.clearAccountNumber();
    assertTrue(!pan.hasAccountNumber());
  }

  @Test
  public void pan_3()
  {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue(pan.hasAccountNumber());
    pan.clearAccountNumber();
    assertTrue(!pan.hasAccountNumber());
    assertTrue("Should not pass Luhn check", !pan.passesLuhnCheck());
    assertEquals("6173", pan.getLastFourDigits());
    assertEquals("526609", pan.getIssuerIdentificationNumber());
    assertEquals(CardBrand.MasterCard, pan.getCardBrand());
    assertEquals(MajorIndustryIdentifier.mii_5,
                 pan.getMajorIndustryIdentifier());
  }

}
