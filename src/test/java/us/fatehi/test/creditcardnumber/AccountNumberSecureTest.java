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
import static us.fatehi.test.utility.AccountNumbersTestUtility.equivalent;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumberSecure;
import us.fatehi.creditcardnumber.AccountNumbers;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

public class AccountNumberSecureTest {

  @Test
  public void secureAccountNumber() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = AccountNumbers.secureAccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.getAccountNumber(), is(nullValue()));
    assertThat(pan.getRawData(), is(nullValue()));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));

    pan.disposeRawData();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));

    pan.dispose();

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));

    final AccountNumber securePan1 = pan.toSecureAccountNumber();
    assertThat(pan == securePan1, is(true));
    assertThat(equivalent(pan, securePan1), is(true));

    final AccountNumber securePan2 = new AccountNumberSecure(pan);
    assertThat(pan != securePan2, is(true));
    assertThat(equivalent(pan, securePan2), is(true));
  }

  @Test
  public void toSecureAccountNumber() {

    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = AccountNumbers.accountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is("52660922"));

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
