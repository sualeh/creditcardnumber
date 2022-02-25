/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import java.io.Serializable;

/**
 * Represents a bank card number.
 *
 * <p>See <a href="http://en.wikipedia.org/wiki/Bank_card_number">Bank card number</a>
 */
public interface AccountNumber extends RawData, Serializable {

  /**
   * Wipes raw data for security reasons. Following recommendations from the <a href=
   * "http://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx">Java
   * Cryptography Architecture (JCA) Reference Guide</a>
   */
  void dispose();

  /**
   * Gets the primary account number (PAN) of the bank card.
   *
   * @return Primary account number - or null if the account number is disposed
   */
  String getAccountNumber();

  /**
   * Length of the account number.
   *
   * @return Account number length - or null if the account number is disposed
   */
  int getAccountNumberLength();

  CardBrand getCardBrand();

  /**
   * The first eight digits of the PAN are taken from the IIN, or Issuer Identification Number,
   * belonging to the issuing bank (IINs were previously known as BIN (Bank Identification Numbers)
   * so you may see references to that terminology in some documents). These eight digits are
   * subject to an international standard, ISO/IEC 7812, and can be used to determine the type of
   * card from the number.
   *
   * @return IIN, or Issuer Identification Number - or null if the account number is disposed
   */
  String getIssuerIdentificationNumber();

  /**
   * The last 4 digits of the primary account number (PAN), for card identification purposes.
   *
   * @return Last 4 digits of PAN - or null if the account number is disposed
   */
  String getLastFourDigits();

  MajorIndustryIdentifier getMajorIndustryIdentifier();

  /**
   * Checks whether the primary account number for the card is available.
   *
   * @return True if the primary account number for the card is available.
   */
  boolean hasAccountNumber();

  /**
   * Is the account number length is valid.
   *
   * @return True if valid
   */
  boolean isLengthValid();

  boolean isPrimaryAccountNumberValid();

  /**
   * Checks whether the primary account number passes the Luhn check.
   *
   * @return True if the primary account number passes the Luhn check. See <a
   *     href="http://en.wikipedia.org/wiki/Luhn_algorithm">Luhn Algorithm</a>
   */
  boolean passesLuhnCheck();

  /**
   * Return a secure account number, with only metadata stored in memory.
   *
   * @return Secure account number
   */
  AccountNumber toSecureAccountNumber();
}
