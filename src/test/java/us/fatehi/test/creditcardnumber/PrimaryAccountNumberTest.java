/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.test.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.right;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static us.fatehi.creditcardnumber.AccountNumbers.accountNumber;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

public class PrimaryAccountNumberTest {

  private static final int IIN_LEN = 8;

  @Test
  public void pan_1() {
    final String rawAccountNumber = null;
    final AccountNumber pan = accountNumber(rawAccountNumber);
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(pan.hasAccountNumber(), is(false));
  }

  @Test
  public void pan_2() {
    final String rawAccountNumber = "\t\t";
    final AccountNumber pan = accountNumber(rawAccountNumber);
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(pan.hasAccountNumber(), is(false));
  }

  @Test
  public void pan_3() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = accountNumber(rawAccountNumber);
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(!pan.passesLuhnCheck(), is(true));
    check(rawAccountNumber, pan, rawAccountNumber);
  }

  @Test
  public void pan1() {
    final String rawAccountNumber = "5266092201416174";
    final AccountNumber pan = accountNumber(rawAccountNumber);
    assertThat(pan.passesLuhnCheck(), is(true));
    check(rawAccountNumber, pan, rawAccountNumber);
  }

  @Test
  public void pan2() {
    final String rawAccountNumber = "  5266-0922-0141-6174  ";
    final String accountNumber = "5266092201416174";
    final AccountNumber pan = accountNumber(rawAccountNumber);
    assertThat(pan.passesLuhnCheck(), is(true));
    check(rawAccountNumber, pan, accountNumber);
  }

  @Test
  public void pan3() {
    final String rawAccountNumber = "  5266-0922-0141-6174-7889  ";
    final String accountNumber = "52660922014161747889";
    final AccountNumber pan = accountNumber(rawAccountNumber);
    assertThat(!pan.passesLuhnCheck(), is(true));
    assertThat(pan.exceedsMaximumLength(), is(true));
    check(rawAccountNumber, pan, accountNumber);
  }

  @Test
  public void pan4() {
    final String rawAccountNumber = "  5266-0922  ";
    final String accountNumber = "52660922";
    final AccountNumber pan = accountNumber(rawAccountNumber);
    assertThat(!pan.passesLuhnCheck(), is(true));
    check(rawAccountNumber, pan, accountNumber);
  }

  private void check(
      final String rawAccountNumber, final AccountNumber pan, final String accountNumber) {
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(pan.getAccountNumber(), is(accountNumber));
    assertThat(pan.getLastFourDigits(), is(right(accountNumber, 4)));
    assertThat(pan.getIssuerIdentificationNumber(), is(left(accountNumber, IIN_LEN)));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));
  }
}
