/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static us.fatehi.creditcardnumber.AccountNumbers.parseAccountNumber;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SealedObject;

final class AccountNumberSealed extends BaseRawData implements AccountNumber {

  private static final long serialVersionUID = -7012531091389412459L;

  private final SealedObject accountNumber;
  private final AccountNumber panSecure;

  AccountNumberSealed(final String rawAccountNumber, final Cipher cipher) {
    super(requireNonNull(rawAccountNumber, "No raw account number provided"));

    requireNonNull(cipher, "No cipher provided");

    final String accountNumberString = parseAccountNumber(trimToEmpty(rawAccountNumber));
    try {
      accountNumber = new SealedObject(accountNumberString, cipher);
    } catch (IllegalBlockSizeException | IOException e) {
      throw new UnsupportedOperationException("Cannot created sealed account number", e);
    }

    panSecure = AccountNumbers.secureAccountNumber(rawAccountNumber);
  }

  @Override
  public void dispose() {
    super.disposeRawData();
    // Note: Do not dispose off the sealed account data
  }

  @Override
  public boolean exceedsMaximumLength() {
    return panSecure.exceedsMaximumLength();
  }

  @Override
  public String getAccountNumber() {
    return null;
  }

  @Override
  public int getAccountNumberLength() {
    return panSecure.getAccountNumberLength();
  }

  @Override
  public CardBrand getCardBrand() {
    return panSecure.getCardBrand();
  }

  @Override
  public String getIssuerIdentificationNumber() {
    return null;
  }

  @Override
  public String getLastFourDigits() {
    return null;
  }

  @Override
  public MajorIndustryIdentifier getMajorIndustryIdentifier() {
    return panSecure.getMajorIndustryIdentifier();
  }

  @Override
  public boolean hasAccountNumber() {
    return false;
  }

  @Override
  public boolean isLengthValid() {
    return panSecure.isLengthValid();
  }

  @Override
  public boolean isPrimaryAccountNumberValid() {
    return panSecure.isPrimaryAccountNumberValid();
  }

  @Override
  public boolean passesLuhnCheck() {
    return panSecure.passesLuhnCheck();
  }

  @Override
  public AccountNumber toSecureAccountNumber() {
    return panSecure;
  }

  @Override
  public String toString() {
    if (hasAccountNumber()) {
      return getAccountNumber();
    } else {
      return panSecure.toString();
    }
  }

  AccountNumber toCompleteAccountNumber(final Key key) {
    try {
      final String accountNumberString = accountNumber.getObject(key).toString();
      return new AccountNumberComplete(accountNumberString);
    } catch (InvalidKeyException
        | ClassNotFoundException
        | NoSuchAlgorithmException
        | IOException e) {
      throw new RuntimeException("Could not get complete account number", e);
    }
  }
}
