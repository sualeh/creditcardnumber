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

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

public class SecureAccountNumberTest
{

  @Test
  public void pan_a()
  {
    final String rawAccountNumber = null;
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue(!pan.hasAccountNumber());
    pan.clear();
    assertTrue(!pan.hasAccountNumber());
  }

  @Test
  public void pan_b()
  {
    final String rawAccountNumber = "\t\t";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue(!pan.hasAccountNumber());
    pan.clear();
    assertTrue(!pan.hasAccountNumber());
  }

  @Test
  public void pan_1()
  {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue("Should not pass Luhn check", !pan.passesLuhnCheck());
    assertEquals(CardBrand.MasterCard, pan.getCardBrand());
    assertEquals(MajorIndustryIdentifier.mii_5,
                 pan.getMajorIndustryIdentifier());

    assertTrue(pan.hasRawData());
    assertTrue(pan.hasAccountNumber());
    assertTrue(pan.hasLastFourDigits());
    assertTrue(pan.hasIssuerIdentificationNumber());
    assertEquals("5266092201416173", pan.getAccountNumber());
    assertEquals("6173", pan.getLastFourDigits());
    assertEquals("526609", pan.getIssuerIdentificationNumber());

    pan.clear();

    assertTrue(!pan.hasRawData());
    assertTrue(!pan.hasAccountNumber());
    assertTrue(!pan.hasLastFourDigits());
    assertTrue(!pan.hasIssuerIdentificationNumber());
    assertEquals(null, pan.getAccountNumber());
    assertEquals(null, pan.getLastFourDigits());
    assertEquals(null, pan.getIssuerIdentificationNumber());
  }

  @Test
  public void pan_2()
  {
    final String rawAccountNumber = "573";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue("Should not pass Luhn check", !pan.passesLuhnCheck());
    assertEquals(CardBrand.Unknown, pan.getCardBrand());
    assertEquals(MajorIndustryIdentifier.mii_5,
                 pan.getMajorIndustryIdentifier());

    assertTrue(pan.hasRawData());
    assertTrue(pan.hasAccountNumber());
    assertTrue(pan.hasLastFourDigits());
    assertTrue(pan.hasIssuerIdentificationNumber());
    assertEquals("573", pan.getAccountNumber());
    assertEquals("0573", pan.getLastFourDigits());
    assertEquals("573000", pan.getIssuerIdentificationNumber());

    pan.clear();

    assertTrue(!pan.hasRawData());
    assertTrue(!pan.hasAccountNumber());
    assertTrue(!pan.hasLastFourDigits());
    assertTrue(!pan.hasIssuerIdentificationNumber());
    assertEquals(null, pan.getAccountNumber());
    assertEquals(null, pan.getLastFourDigits());
    assertEquals(null, pan.getIssuerIdentificationNumber());
  }

  @Test
  public void lastFourDigits_1()
  {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue("Should not pass Luhn check", !pan.passesLuhnCheck());
    assertEquals(CardBrand.MasterCard, pan.getCardBrand());
    assertEquals(MajorIndustryIdentifier.mii_5,
                 pan.getMajorIndustryIdentifier());

    assertTrue(pan.hasRawData());
    assertTrue(pan.hasAccountNumber());
    assertTrue(pan.hasLastFourDigits());
    assertTrue(pan.hasIssuerIdentificationNumber());
    assertEquals("6173", pan.getLastFourDigits());
    assertEquals("526609", pan.getIssuerIdentificationNumber());

    pan.clearLastFourDigits();

    assertTrue(!pan.hasRawData());
    assertTrue(!pan.hasAccountNumber());
    assertTrue(!pan.hasLastFourDigits());
    assertTrue(pan.hasIssuerIdentificationNumber());
    assertEquals(null, pan.getLastFourDigits());
    assertEquals("526609", pan.getIssuerIdentificationNumber());
  }

  @Test
  public void lastFourDigits_2()
  {
    final String rawAccountNumber = "573";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue("Should not pass Luhn check", !pan.passesLuhnCheck());
    assertEquals(CardBrand.Unknown, pan.getCardBrand());
    assertEquals(MajorIndustryIdentifier.mii_5,
                 pan.getMajorIndustryIdentifier());

    assertTrue(pan.hasRawData());
    assertTrue(pan.hasAccountNumber());
    assertTrue(pan.hasLastFourDigits());
    assertTrue(pan.hasIssuerIdentificationNumber());
    assertEquals("0573", pan.getLastFourDigits());
    assertEquals("573000", pan.getIssuerIdentificationNumber());

    pan.clearLastFourDigits();

    assertTrue(!pan.hasRawData());
    assertTrue(!pan.hasAccountNumber());
    assertTrue(!pan.hasLastFourDigits());
    assertTrue(pan.hasIssuerIdentificationNumber());
    assertEquals(null, pan.getLastFourDigits());
    assertEquals("573000", pan.getIssuerIdentificationNumber());
  }

  @Test
  public void iin_1()
  {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue("Should not pass Luhn check", !pan.passesLuhnCheck());
    assertEquals(CardBrand.MasterCard, pan.getCardBrand());
    assertEquals(MajorIndustryIdentifier.mii_5,
                 pan.getMajorIndustryIdentifier());

    assertTrue(pan.hasRawData());
    assertTrue(pan.hasAccountNumber());
    assertTrue(pan.hasLastFourDigits());
    assertTrue(pan.hasIssuerIdentificationNumber());
    assertEquals("6173", pan.getLastFourDigits());
    assertEquals("526609", pan.getIssuerIdentificationNumber());

    pan.clearIssuerIdentificationNumber();

    assertTrue(!pan.hasRawData());
    assertTrue(!pan.hasAccountNumber());
    assertTrue(pan.hasLastFourDigits());
    assertTrue(!pan.hasIssuerIdentificationNumber());
    assertEquals("6173", pan.getLastFourDigits());
    assertEquals(null, pan.getIssuerIdentificationNumber());
  }

  @Test
  public void iin_2()
  {
    final String rawAccountNumber = "573";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertTrue("Should not pass Luhn check", !pan.passesLuhnCheck());
    assertEquals(CardBrand.Unknown, pan.getCardBrand());
    assertEquals(MajorIndustryIdentifier.mii_5,
                 pan.getMajorIndustryIdentifier());

    assertTrue(pan.hasRawData());
    assertTrue(pan.hasAccountNumber());
    assertTrue(pan.hasLastFourDigits());
    assertTrue(pan.hasIssuerIdentificationNumber());
    assertEquals("0573", pan.getLastFourDigits());
    assertEquals("573000", pan.getIssuerIdentificationNumber());

    pan.clearIssuerIdentificationNumber();

    assertTrue(!pan.hasRawData());
    assertTrue(!pan.hasAccountNumber());
    assertTrue(pan.hasLastFourDigits());
    assertTrue(!pan.hasIssuerIdentificationNumber());
    assertEquals("0573", pan.getLastFourDigits());
    assertEquals(null, pan.getIssuerIdentificationNumber());
  }

}
