/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.test.creditcardnumber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

public class SecureAccountNumberTest {

  @Test
  public void iin_1() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.hasLastFourDigits(), is(true));
    assertThat(pan.hasIssuerIdentificationNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is("526609"));

    pan.disposeIssuerIdentificationNumber();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.hasLastFourDigits(), is(true));
    assertThat(pan.hasIssuerIdentificationNumber(), is(false));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));
  }

  @Test
  public void iin_2() {
    final String rawAccountNumber = "573";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.Unknown));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.hasLastFourDigits(), is(true));
    assertThat(pan.hasIssuerIdentificationNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is("0573"));
    assertThat(pan.getIssuerIdentificationNumber(), is("573000"));

    pan.disposeIssuerIdentificationNumber();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.hasLastFourDigits(), is(true));
    assertThat(pan.hasIssuerIdentificationNumber(), is(false));
    assertThat(pan.getLastFourDigits(), is("0573"));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));
  }

  @Test
  public void lastFourDigits_1() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.hasLastFourDigits(), is(true));
    assertThat(pan.hasIssuerIdentificationNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is("526609"));

    pan.disposeLastFourDigits();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.hasLastFourDigits(), is(false));
    assertThat(pan.hasIssuerIdentificationNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is("526609"));
  }

  @Test
  public void lastFourDigits_2() {
    final String rawAccountNumber = "573";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.Unknown));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.hasLastFourDigits(), is(true));
    assertThat(pan.hasIssuerIdentificationNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is("0573"));
    assertThat(pan.getIssuerIdentificationNumber(), is("573000"));

    pan.disposeLastFourDigits();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.hasLastFourDigits(), is(false));
    assertThat(pan.hasIssuerIdentificationNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is("573000"));
  }

  @Test
  public void pan_1() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.hasLastFourDigits(), is(true));
    assertThat(pan.hasIssuerIdentificationNumber(), is(true));
    assertThat(pan.getAccountNumber(), is("5266092201416173"));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is("526609"));

    pan.dispose();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.hasLastFourDigits(), is(false));
    assertThat(pan.hasIssuerIdentificationNumber(), is(false));
    assertThat(pan.getAccountNumber(), is(nullValue()));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));
  }

  @Test
  public void pan_2() {
    final String rawAccountNumber = "573";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.Unknown));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.hasLastFourDigits(), is(true));
    assertThat(pan.hasIssuerIdentificationNumber(), is(true));
    assertThat(pan.getAccountNumber(), is("573"));
    assertThat(pan.getLastFourDigits(), is("0573"));
    assertThat(pan.getIssuerIdentificationNumber(), is("573000"));

    pan.dispose();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.hasLastFourDigits(), is(false));
    assertThat(pan.hasIssuerIdentificationNumber(), is(false));
    assertThat(pan.getAccountNumber(), is(nullValue()));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));
  }

  @Test
  public void pan_a() {
    final String rawAccountNumber = null;
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat(pan.hasAccountNumber(), is(false));
    pan.dispose();
    assertThat(pan.hasAccountNumber(), is(false));
  }

  @Test
  public void pan_b() {
    final String rawAccountNumber = "\t\t";
    final AccountNumber pan = new AccountNumber(rawAccountNumber);
    assertThat(pan.hasAccountNumber(), is(false));
    pan.dispose();
    assertThat(pan.hasAccountNumber(), is(false));
  }
}
