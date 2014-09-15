/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014, Sualeh Fatehi.
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
package us.fatehi.creditcardnumber.bankcard;


import java.io.Serializable;

public interface AccountNumber
  extends Serializable
{

  /**
   * @see us.fatehi.creditcardnumber.RawData#exceedsMaximumLength()
   */
  public abstract boolean exceedsMaximumLength();

  /**
   * Gets the primary account number of the bank card.
   *
   * @return Primary account number.
   */
  public abstract String getAccountNumber();

  /**
   * The length of the PAN.
   *
   * @return The length of the PAN
   */
  public abstract int getAccountNumberLength();

  /**
   * Gets the the card brand.
   *
   * @return Card brand.
   */
  public abstract CardBrand getCardBrand();

  /**
   * The first six digits of the PAN are taken from the IIN, or Issuer
   * Identification Number, belonging to the issuing bank (IINs were
   * previously known as BIN — Bank Identification Numbers — so you may
   * see references to that terminology in some documents). These six
   * digits are subject to an international standard, ISO/IEC 7812, and
   * can be used to determine the type of card from the number.
   *
   * @return IIN, or Issuer Identification Number
   */
  public abstract String getIssuerIdentificationNumber();

  /**
   * The last 4 digits of the PAN, for card identification purposes.
   *
   * @return Last 4 digits of PAN
   */
  public abstract String getLastFourDigits();

  /**
   * The first digit of a credit card number is the Major Industry
   * Identifier (MII) (see ISO/IEC 7812), which represents the category
   * of entity which issued the card.
   *
   * @return MII.
   */
  public abstract MajorIndustryIdentifier getMajorIndustryIdentifier();

  /**
   * Checks whether the primary account number for the card is
   * available.
   *
   * @return True if the primary account number for the card is
   *         available.
   */
  public abstract boolean hasPrimaryAccountNumber();

  /**
   * Checks whether the primary account number passes the Luhn check.
   *
   * @return True if the primary account number passes the Luhn check.
   */
  public abstract boolean isPassesLuhnCheck();

}
