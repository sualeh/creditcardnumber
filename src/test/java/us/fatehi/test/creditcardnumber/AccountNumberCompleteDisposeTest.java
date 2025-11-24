/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2026, Sualeh Fatehi.
 *
 */
package us.fatehi.test.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.left;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumbers;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

public class AccountNumberCompleteDisposeTest {

  private static final int IIN_LEN = 8;

  @Test
  public void dispose() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = AccountNumbers.completeAccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is(left(rawAccountNumber, IIN_LEN)));

    pan.dispose();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));
  }

  @Test
  public void disposeRawData() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = AccountNumbers.completeAccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is(left(rawAccountNumber, IIN_LEN)));

    pan.disposeRawData();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is(left(rawAccountNumber, IIN_LEN)));

    pan.dispose();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));
  }

  @Test
  public void pan_1() {
    final String rawAccountNumber = "573";
    final AccountNumber pan = AccountNumbers.completeAccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.Unknown));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.getAccountNumber(), is("573"));
    assertThat(pan.getLastFourDigits(), is("0573"));
    assertThat(pan.getIssuerIdentificationNumber(), is("57300000"));

    pan.dispose();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.getAccountNumber(), is(nullValue()));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));
  }

  @Test
  public void pan_a() {
    final String rawAccountNumber = null;
    final AccountNumber pan = AccountNumbers.completeAccountNumber(rawAccountNumber);
    assertThat(pan.hasAccountNumber(), is(false));
    pan.disposeRawData();
    assertThat(pan.hasAccountNumber(), is(false));
  }

  @Test
  public void pan_b() {
    final String rawAccountNumber = "\t\t";
    final AccountNumber pan = AccountNumbers.completeAccountNumber(rawAccountNumber);
    assertThat(pan.hasAccountNumber(), is(false));
    pan.disposeRawData();
    assertThat(pan.hasAccountNumber(), is(false));
  }
}
