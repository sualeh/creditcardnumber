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
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumbers;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

public class AccountNumberLastFourTest {

  @Test
  public void accountNumberLastFour() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = AccountNumbers.accountNumberLastFour(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));
    assertThat(pan.getLastFourDigits(), is("6173"));

    assertThat(pan.exceedsMaximumLength(), is(false));
    assertThat(pan.isLengthValid(), is(true));
    assertThat(pan.isPrimaryAccountNumberValid(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.getAccountNumberLength(), is(16));
    assertThat(pan.getRawData(), is(nullValue()));
    assertThat(pan.getAccountNumber(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));
    assertThat(pan.toString(), is("MasterCard-6173"));

    pan.disposeRawData();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.getAccountNumber(), is(nullValue()));
    assertThat(pan.getRawData(), is(nullValue()));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));

    pan.dispose();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.getAccountNumber(), is(nullValue()));
    assertThat(pan.getRawData(), is(nullValue()));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));
  }

  @Test
  public void toSecureAccountNumber() {

    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = AccountNumbers.accountNumberLastFour(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));
    assertThat(pan.getLastFourDigits(), is("6173"));

    final AccountNumber securePan = pan.toSecureAccountNumber();
    assertThat("Should not pass Luhn check", !securePan.passesLuhnCheck(), is(true));
    assertThat(securePan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(securePan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(securePan.hasRawData(), is(false));
    assertThat(securePan.hasAccountNumber(), is(false));
    assertThat(securePan.getLastFourDigits(), is(nullValue()));
    assertThat(securePan.getIssuerIdentificationNumber(), is(nullValue()));
  }
}
