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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

public class PrimaryAccountNumberTest
{

  @Test
  public void pan_1()
  {
    final String rawAccountNumber = null;
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(pan.hasAccountNumber(), is(false));
  }

  @Test
  public void pan_2()
  {
    final String rawAccountNumber = "\t\t";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(pan.hasAccountNumber(), is(false));
  }

  @Test
  public void pan_3()
  {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(!pan.passesLuhnCheck(), is(true));
    check(rawAccountNumber, pan, rawAccountNumber);
  }

  @Test
  public void pan1()
  {
    final String rawAccountNumber = "5266092201416174";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat(pan.passesLuhnCheck(), is(true));
    check(rawAccountNumber, pan, rawAccountNumber);
  }

  @Test
  public void pan2()
  {
    final String rawAccountNumber = "  5266-0922-0141-6174  ";
    final String accountNumber = "5266092201416174";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat(pan.passesLuhnCheck(), is(true));
    check(rawAccountNumber, pan, accountNumber);
  }

  @Test
  public void pan3()
  {
    final String rawAccountNumber = "  5266-0922-0141-6174-7889  ";
    final String accountNumber = "52660922014161747889";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat(!pan.passesLuhnCheck(), is(true));
    assertThat(pan.exceedsMaximumLength(), is(true));
    check(rawAccountNumber, pan, accountNumber);
  }

  @Test
  public void pan4()
  {
    final String rawAccountNumber = "  5266-0922  ";
    final String accountNumber = "52660922";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat(!pan.passesLuhnCheck(), is(true));
    check(rawAccountNumber, pan, accountNumber);
  }

  private void check(final String rawAccountNumber,
                     final AccountNumber pan,
                     final String accountNumber)
  {
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(pan.getAccountNumber(), is(accountNumber));
    assertThat(pan.getLastFourDigits(), is(right(accountNumber, 4)));
    assertThat(pan.getIssuerIdentificationNumber(), is(left(accountNumber, 6)));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(),
               is(MajorIndustryIdentifier.mii_5));
  }

}
