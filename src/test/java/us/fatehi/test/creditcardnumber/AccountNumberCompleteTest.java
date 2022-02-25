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
import static org.hamcrest.Matchers.containsString;
import static us.fatehi.creditcardnumber.AccountNumbers.completeAccountNumber;
import static us.fatehi.test.utility.AccountNumbersTestUtility.equivalent;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumberSecure;
import us.fatehi.creditcardnumber.AccountNumbers;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

public class AccountNumberCompleteTest {

  private static final int IIN_LEN = 8;

  @Test
  public void completeAccountNumberWithLast4() throws NoSuchAlgorithmException {
    final KeyGenerator kgen = KeyGenerator.getInstance("DES");
    kgen.init(56);
    final Key key = kgen.generateKey();

    final String rawAccountNumber = "52660922014161747889";
    final AccountNumber accountNumberLastFour =
        AccountNumbers.accountNumberLastFour(rawAccountNumber);

    final AccountNumber pan = completeAccountNumber(accountNumberLastFour, key);
    assertThat(accountNumberLastFour == pan, is(true));
  }

  @Test
  public void pan_1() {
    final String rawAccountNumber = null;
    final AccountNumber pan = completeAccountNumber(rawAccountNumber);
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.isPrimaryAccountNumberValid(), is(false));
    assertThat(pan.getAccountNumberLength(), is(0));
  }

  @Test
  public void pan_2() {
    final String rawAccountNumber = "\t\t";
    final AccountNumber pan = completeAccountNumber(rawAccountNumber);
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.isPrimaryAccountNumberValid(), is(false));
    assertThat(pan.getAccountNumberLength(), is(0));
  }

  @Test
  public void pan_3() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = completeAccountNumber(rawAccountNumber);
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(!pan.passesLuhnCheck(), is(true));
    assertThat(pan.getAccountNumberLength(), is(16));
    check(rawAccountNumber, pan, rawAccountNumber);
    assertThat(pan.isPrimaryAccountNumberValid(), is(false));
  }

  @Test
  public void pan1() {
    final String rawAccountNumber = "5266092201416174";
    final AccountNumber pan = completeAccountNumber(rawAccountNumber);
    assertThat(pan.passesLuhnCheck(), is(true));
    assertThat(pan.isLengthValid(), is(true));
    assertThat(pan.isPrimaryAccountNumberValid(), is(true));
    assertThat(pan.getAccountNumberLength(), is(16));
    check(rawAccountNumber, pan, rawAccountNumber);

    final AccountNumber securePan1 = pan.toSecureAccountNumber();
    assertThat(pan != securePan1, is(true));
    assertThat(equivalent(pan, securePan1), is(true));

    final AccountNumber securePan2 = new AccountNumberSecure(pan);
    assertThat(pan != securePan2, is(true));
    assertThat(equivalent(pan, securePan2), is(true));
  }

  @Test
  public void pan2() {
    final String rawAccountNumber = "  5266-0922-0141-6174  ";
    final String accountNumber = "5266092201416174";
    final AccountNumber pan = completeAccountNumber(rawAccountNumber);
    assertThat(pan.passesLuhnCheck(), is(true));
    assertThat(pan.isLengthValid(), is(true));
    assertThat(pan.isPrimaryAccountNumberValid(), is(true));
    assertThat(pan.getAccountNumberLength(), is(16));
    check(rawAccountNumber, pan, accountNumber);

    assertThat(pan.toString(), is(accountNumber));
    pan.dispose();
    assertThat(pan.toString(), containsString("MasterCard"));
  }

  @Test
  public void pan3() {
    final String rawAccountNumber = "  5266-0922-0141-6174-7889  ";
    final String accountNumber = "52660922014161747889";
    final AccountNumber pan = completeAccountNumber(rawAccountNumber);
    assertThat(!pan.passesLuhnCheck(), is(true));
    assertThat(pan.exceedsMaximumLength(), is(true));
    assertThat(pan.isLengthValid(), is(false));
    assertThat(pan.isPrimaryAccountNumberValid(), is(false));
    assertThat(pan.getAccountNumberLength(), is(20));
    check(rawAccountNumber, pan, accountNumber);
  }

  @Test
  public void pan4() {
    final String rawAccountNumber = "  5266-0922  ";
    final String accountNumber = "52660922";
    final AccountNumber pan = completeAccountNumber(rawAccountNumber);
    assertThat(!pan.passesLuhnCheck(), is(true));
    assertThat(pan.isLengthValid(), is(false));
    assertThat(pan.isPrimaryAccountNumberValid(), is(false));
    assertThat(pan.getAccountNumberLength(), is(8));
    check(rawAccountNumber, pan, accountNumber);
  }

  private void check(
      final String rawAccountNumber, final AccountNumber pan, final String accountNumber) {
    assertThat(pan.getRawData(), is(rawAccountNumber));
    assertThat(pan.getAccountNumber(), is(accountNumber));
    assertThat(pan.getLastFourDigits(), is(right(accountNumber, 4)));
    assertThat(pan.getIssuerIdentificationNumber(), is(left(accountNumber, IIN_LEN)));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));
    // Allow look-ahead typing and try to identify the card brand by the first four digits of the
    // card number
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
  }
}
