/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2015, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */
package us.fatehi.creditcardnumber;


import java.io.Serializable;

/**
 * Represents a bank card number.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Bank_card_number">Bank
 *      card number</a>
 * @author Sualeh Fatehi
 */
public interface PrimaryAccountNumber
  extends RawData, Serializable
{

  /**
   * Gets the primary account number (PAN) of the bank card.
   *
   * @return Primary account number.
   */
  String getAccountNumber();

  /**
   * The length of the PAN.
   *
   * @return The length of the PAN
   */
  int getAccountNumberLength();

  /**
   * Gets the the card brand.
   *
   * @return Card brand.
   */
  CardBrand getCardBrand();

  /**
   * The first six digits of the PAN are taken from the IIN, or Issuer
   * Identification Number, belonging to the issuing bank (IINs were
   * previously known as BIN (Bank Identification Numbers) so you may
   * see references to that terminology in some documents). These six
   * digits are subject to an international standard, ISO/IEC 7812, and
   * can be used to determine the type of card from the number.
   *
   * @return IIN, or Issuer Identification Number
   */
  String getIssuerIdentificationNumber();

  /**
   * The last 4 digits of the primary account number (PAN), for card
   * identification purposes.
   *
   * @return Last 4 digits of PAN
   */
  String getLastFourDigits();

  /**
   * The first digit of a credit card number is the Major Industry
   * Identifier (MII) (see ISO/IEC 7812), which represents the category
   * of entity which issued the card.
   *
   * @return MII.
   */
  MajorIndustryIdentifier getMajorIndustryIdentifier();

  /**
   * Checks whether the primary account number for the card is
   * available.
   *
   * @return True if the primary account number for the card is
   *         available.
   */
  boolean hasPrimaryAccountNumber();

  /**
   * Whether the account number has a valid length.
   */
  boolean isLengthValid();

  /**
   * Whether the primary account number (PAN) is valid.
   *
   * @return True if the primary account number (PAN) is valid
   */
  boolean isPrimaryAccountNumberValid();

  /**
   * Checks whether the primary account number passes the Luhn check.
   *
   * @return True if the primary account number passes the Luhn check.
   * @see <a href="http://en.wikipedia.org/wiki/Luhn_algorithm">Luhn
   *      Algorithm</a>
   */
  boolean passesLuhnCheck();

}
