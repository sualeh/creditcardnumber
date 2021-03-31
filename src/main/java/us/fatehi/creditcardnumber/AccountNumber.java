/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import java.io.Serializable;

public interface AccountNumber extends RawData, Serializable {

  @Override
  boolean exceedsMaximumLength();

  /**
   * Gets the primary account number (PAN) of the bank card.
   *
   * @return Primary account number.
   */
  String getAccountNumber();

  int getAccountNumberLength();

  CardBrand getCardBrand();

  /**
   * The first six digits of the PAN are taken from the IIN, or Issuer Identification Number,
   * belonging to the issuing bank (IINs were previously known as BIN (Bank Identification Numbers)
   * so you may see references to that terminology in some documents). These six digits are subject
   * to an international standard, ISO/IEC 7812, and can be used to determine the type of card from
   * the number.
   *
   * @return IIN, or Issuer Identification Number
   */
  String getIssuerIdentificationNumber();

  /**
   * The last 4 digits of the primary account number (PAN), for card identification purposes.
   *
   * @return Last 4 digits of PAN
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
   * Checks whether the Issuer Identification Number for the card is available.
   *
   * @return True if the Issuer Identification Number for the card is available.
   */
  boolean hasIssuerIdentificationNumber();

  /**
   * Checks whether the last 4 digits of the primary account number for the card are available.
   *
   * @return True if the last 4 digits of the primary account number for the card are available.
   */
  boolean hasLastFourDigits();

  boolean isLengthValid();

  boolean isPrimaryAccountNumberValid();

  /**
   * Checks whether the primary account number passes the Luhn check.
   *
   * @return True if the primary account number passes the Luhn check. See <a
   *     href="http://en.wikipedia.org/wiki/Luhn_algorithm">Luhn Algorithm</a>
   */
  boolean passesLuhnCheck();

  AccountNumber toSecureAccountNumber();
}
