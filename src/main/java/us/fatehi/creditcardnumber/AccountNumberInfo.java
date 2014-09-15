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
package us.fatehi.creditcardnumber;


public final class AccountNumberInfo
  implements PrimaryAccountNumber
{

  private static final long serialVersionUID = 2002490292247684624L;

  private final MajorIndustryIdentifier majorIndustryIdentifier;
  private final String issuerIdentificationNumber;
  private final String lastFourDigits;
  private final CardBrand cardBrand;
  private final boolean passesLuhnCheck;
  private final int accountNumberLength;
  private final boolean exceedsMaximumLength;

  public AccountNumberInfo(final PrimaryAccountNumber accountNumber)
  {
    if (accountNumber != null)
    {
      majorIndustryIdentifier = accountNumber.getMajorIndustryIdentifier();
      issuerIdentificationNumber = accountNumber
        .getIssuerIdentificationNumber();
      lastFourDigits = accountNumber.getLastFourDigits();
      cardBrand = accountNumber.getCardBrand();
      passesLuhnCheck = accountNumber.passesLuhnCheck();
      accountNumberLength = accountNumber.getAccountNumberLength();
      exceedsMaximumLength = accountNumber.exceedsMaximumLength();
    }
    else
    {
      majorIndustryIdentifier = MajorIndustryIdentifier.unknown;
      issuerIdentificationNumber = "";
      lastFourDigits = "";
      cardBrand = CardBrand.unknown;
      passesLuhnCheck = false;
      accountNumberLength = -1;
      exceedsMaximumLength = false;
    }
  }

  /**
   * @see us.fatehi.creditcardnumber.PrimaryAccountNumber#exceedsMaximumLength()
   */
  @Override
  public boolean exceedsMaximumLength()
  {
    return exceedsMaximumLength;
  }

  /**
   * @see us.fatehi.creditcardnumber.PrimaryAccountNumber#getAccountNumber()
   */
  @Override
  public String getAccountNumber()
  {
    throw new IllegalAccessError("Account number is not available");
  }

  /**
   * @see us.fatehi.creditcardnumber.PrimaryAccountNumber#getAccountNumberLength()
   */
  @Override
  public int getAccountNumberLength()
  {
    return accountNumberLength;
  }

  /**
   * @see us.fatehi.creditcardnumber.PrimaryAccountNumber#getCardBrand()
   */
  @Override
  public CardBrand getCardBrand()
  {
    return cardBrand;
  }

  /**
   * @see us.fatehi.creditcardnumber.PrimaryAccountNumber#getIssuerIdentificationNumber()
   */
  @Override
  public String getIssuerIdentificationNumber()
  {
    return issuerIdentificationNumber;
  }

  /**
   * @see us.fatehi.creditcardnumber.PrimaryAccountNumber#getLastFourDigits()
   */
  @Override
  public String getLastFourDigits()
  {
    return lastFourDigits;
  }

  /**
   * @see us.fatehi.creditcardnumber.PrimaryAccountNumber#getMajorIndustryIdentifier()
   */
  @Override
  public MajorIndustryIdentifier getMajorIndustryIdentifier()
  {
    return majorIndustryIdentifier;
  }

  /**
   * @see us.fatehi.creditcardnumber.PrimaryAccountNumber#hasPrimaryAccountNumber()
   */
  @Override
  public boolean hasPrimaryAccountNumber()
  {
    return false;
  }

  /**
   * @see us.fatehi.creditcardnumber.PrimaryAccountNumber#passesLuhnCheck()
   */
  @Override
  public boolean passesLuhnCheck()
  {
    return passesLuhnCheck;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return String.format("%s-%s", cardBrand, lastFourDigits);
  }

}
