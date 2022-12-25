/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2023, Sualeh Fatehi.
 *
 */
package us.fatehi.test.creditcardnumber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static us.fatehi.test.utility.AccountNumbersTestUtility.equivalent;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumbers;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

@TestInstance(Lifecycle.PER_CLASS)
public class AccountNumberSealedTest {

  private Key key;
  private Cipher cipher;

  @BeforeAll
  public void createKeys()
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    // Generate key
    final KeyGenerator kgen = KeyGenerator.getInstance("AES");
    kgen.init(128);
    key = kgen.generateKey();

    cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);
  }

  @Test
  public void roundTrip() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = AccountNumbers.sealedAccountNumber(rawAccountNumber, cipher);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.getAccountNumber(), is(nullValue()));

    final AccountNumber completeAccountNumber = AccountNumbers.completeAccountNumber(pan, key);
    assertThat("Should not pass Luhn check", !completeAccountNumber.passesLuhnCheck(), is(true));
    assertThat(completeAccountNumber.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(
        completeAccountNumber.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));
    assertThat(completeAccountNumber.hasAccountNumber(), is(true));
    assertThat(completeAccountNumber.getAccountNumber(), is("5266092201416173"));

    final AccountNumber secureAccountNumber = AccountNumbers.secureAccountNumber(pan);
    assertThat("Should not pass Luhn check", !secureAccountNumber.passesLuhnCheck(), is(true));
    assertThat(secureAccountNumber.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(secureAccountNumber.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));
    assertThat(secureAccountNumber.hasAccountNumber(), is(false));
    assertThat(secureAccountNumber.getAccountNumber(), is(nullValue()));
  }

  @Test
  public void sealedAccountNumber() {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = AccountNumbers.sealedAccountNumber(rawAccountNumber, cipher);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(false));
    assertThat(pan.hasAccountNumber(), is(false));
    assertThat(pan.getAccountNumber(), is(nullValue()));
    assertThat(pan.getRawData(), is(nullValue()));
    assertThat(pan.getLastFourDigits(), is(nullValue()));
    assertThat(pan.getIssuerIdentificationNumber(), is(nullValue()));

    assertThat(pan.exceedsMaximumLength(), is(false));
    assertThat(pan.isLengthValid(), is(true));
    assertThat(pan.isPrimaryAccountNumberValid(), is(false));
    assertThat(pan.getAccountNumberLength(), is(16));
    assertThat(pan.toString(), is("MasterCard"));

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

    final AccountNumber securePan1 = AccountNumbers.sealedAccountNumber(pan, cipher);
    assertThat(pan == securePan1, is(true));
    assertThat(equivalent(pan, securePan1), is(true));
  }

  @Test
  public void sealedAccountNumberAdditionalCases() {
    final String rawAccountNumber = "5266092201416173";

    final AccountNumber accountNumberLastFour =
        AccountNumbers.accountNumberLastFour(rawAccountNumber);
    assertThat(accountNumberLastFour.hasAccountNumber(), is(false));
    final AccountNumber securePan1 =
        AccountNumbers.sealedAccountNumber(accountNumberLastFour, cipher);
    assertThat(equivalent(accountNumberLastFour, securePan1), is(true));

    final AccountNumber accountNumberDisposed =
        AccountNumbers.completeAccountNumber(rawAccountNumber);
    accountNumberDisposed.dispose();
    assertThat(accountNumberDisposed.hasAccountNumber(), is(false));
    final AccountNumber securePan2 =
        AccountNumbers.sealedAccountNumber(accountNumberDisposed, cipher);
    assertThat(equivalent(accountNumberDisposed, securePan2), is(true));
  }

  @Test
  public void sealedAccountNumberException()
      throws NoSuchAlgorithmException, NoSuchPaddingException {
    final Cipher cipher = Cipher.getInstance("AES");
    final String rawAccountNumber = "5266092201416173";
    final Exception exception =
        assertThrows(
            RuntimeException.class,
            () -> AccountNumbers.sealedAccountNumber(rawAccountNumber, cipher));
    assertThat(exception.getMessage(), is("Cannot created sealed account number"));
    assertThat(exception.getCause().getClass().getSimpleName(), is("IllegalStateException"));
  }

  @Test
  public void toSealedAccountNumber() {

    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = AccountNumbers.completeAccountNumber(rawAccountNumber);
    assertThat("Should not pass Luhn check", !pan.passesLuhnCheck(), is(true));
    assertThat(pan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(pan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(pan.hasRawData(), is(true));
    assertThat(pan.hasAccountNumber(), is(true));
    assertThat(pan.getLastFourDigits(), is("6173"));
    assertThat(pan.getIssuerIdentificationNumber(), is("52660922"));

    final AccountNumber securePan = AccountNumbers.sealedAccountNumber(pan, cipher);
    assertThat("Should not pass Luhn check", !securePan.passesLuhnCheck(), is(true));
    assertThat(securePan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(securePan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    assertThat(securePan.hasRawData(), is(false));
    assertThat(securePan.hasAccountNumber(), is(false));
    assertThat(securePan.getLastFourDigits(), is(nullValue()));
    assertThat(securePan.getIssuerIdentificationNumber(), is(nullValue()));
  }

  @Test
  public void unsealAccountNumberException() throws NoSuchAlgorithmException {

    final String rawAccountNumber = "5266092201416173";
    final AccountNumber securePan = AccountNumbers.sealedAccountNumber(rawAccountNumber, cipher);
    assertThat("Should not pass Luhn check", !securePan.passesLuhnCheck(), is(true));
    assertThat(securePan.getCardBrand(), is(CardBrand.MasterCard));
    assertThat(securePan.getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));

    final KeyGenerator kgen = KeyGenerator.getInstance("DES");
    kgen.init(56);
    final Key key = kgen.generateKey();

    final AccountNumber completeAccountNumber =
        AccountNumbers.completeAccountNumber(securePan, key);
    assertThat(securePan == completeAccountNumber, is(true));
  }
}
