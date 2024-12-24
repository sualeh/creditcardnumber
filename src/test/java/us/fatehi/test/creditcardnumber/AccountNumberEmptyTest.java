/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2025, Sualeh Fatehi.
 *
 */
package us.fatehi.test.creditcardnumber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static us.fatehi.creditcardnumber.AccountNumbers.completeAccountNumber;
import static us.fatehi.creditcardnumber.AccountNumbers.accountNumberLastFour;
import static us.fatehi.creditcardnumber.AccountNumbers.emptyAccountNumber;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

public class AccountNumberEmptyTest {

  @Test
  public void pan1() {
    final AccountNumber pan = emptyAccountNumber();
    validateEmptyCard(pan);
  }

  @Test
  public void pan2() {
    final AccountNumber pan = completeAccountNumber(null);
    validateEmptyCard(pan);
  }

  @Test
  public void pan3() {
    final AccountNumber pan = accountNumberLastFour((String) null);
    validateEmptyCard(pan);
  }

  @Test
  public void pan4() {
    final AccountNumber pan = accountNumberLastFour((AccountNumber) null);
    validateEmptyCard(pan);
  }

  private void validateEmptyCard(final AccountNumber pan) {
    assertThat(pan.exceedsMaximumLength(), is(false));
    assertThat(pan.passesLuhnCheck(), is(false));
    assertThat(pan.isLengthValid(), is(false));
    assertThat(pan.isPrimaryAccountNumberValid(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.getAccountNumberLength(), is(0));
    assertThat(pan.getRawData(), is(nullValue()));
    assertThat(pan.getAccountNumber(), is(nullValue()));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));
    assertThat(pan.getCardBrand(), is(CardBrand.Unknown));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.unknown));
    assertThat(pan.toString(), is(""));

    pan.disposeRawData();
    assertThat(pan.hasAccountNumber(), is(false));

    pan.dispose();
    assertThat(pan.hasAccountNumber(), is(false));

    final AccountNumber secureAccountNumber = pan.toSecureAccountNumber();
    assertThat(pan == secureAccountNumber, is(true));
  }
}
