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


import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.length;
import static org.apache.commons.lang3.StringUtils.right;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * Parses and represents the primary account number of the bank card.
 */
public class AccountNumber
  extends BaseRawData
  implements PrimaryAccountNumber
{

  private static final long serialVersionUID = -7012531091389412459L;

  private final String accountNumber;
  private final CardBrand cardBrand;
  private final MajorIndustryIdentifier majorIndustryIdentifier;
  private final boolean passesLuhnCheck;

  /**
   * No primary account number of the bank card.
   */
  public AccountNumber()
  {
    this(null);
  }

  /**
   * Parses the primary account number of the bank card. Can accept card
   * numbers with spaces or dashes.
   *
   * @param rawAccountNumber
   *        Raw primary account number from the magnetic track data.
   */
  public AccountNumber(final String rawAccountNumber)
  {
    super(rawAccountNumber);

    accountNumber = non_digit.matcher(trimToEmpty(rawAccountNumber))
      .replaceAll("");
    passesLuhnCheck = luhnCheck();
    majorIndustryIdentifier = MajorIndustryIdentifier.from(accountNumber);
    cardBrand = CardBrand.from(accountNumber);
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (!(obj instanceof AccountNumber))
    {
      return false;
    }
    final AccountNumber other = (AccountNumber) obj;
    if (accountNumber == null)
    {
      if (other.accountNumber != null)
      {
        return false;
      }
    }
    else if (!accountNumber.equals(other.accountNumber))
    {
      return false;
    }
    return true;
  }

  /*
   * (non-Javadoc)
   * @see
   * us.fatehi.creditcardnumber.bankcard.AccountNumber#exceedsMaximumLength
   * ()
   */
  @Override
  public boolean exceedsMaximumLength()
  {
    return trimToEmpty(getRawData()).length() > 19;
  }

  /*
   * (non-Javadoc)
   * @see
   * us.fatehi.creditcardnumber.bankcard.AccountNumber#getAccountNumber
   * ()
   */
  @Override
  public String getAccountNumber()
  {
    return accountNumber;
  }

  /*
   * (non-Javadoc)
   * @see us.fatehi.creditcardnumber.bankcard.AccountNumber#
   * getAccountNumberLength()
   */
  @Override
  public int getAccountNumberLength()
  {
    return length(accountNumber);
  }

  /*
   * (non-Javadoc)
   * @see
   * us.fatehi.creditcardnumber.bankcard.AccountNumber#getCardBrand()
   */
  @Override
  public CardBrand getCardBrand()
  {
    return cardBrand;
  }

  /*
   * (non-Javadoc)
   * @see us.fatehi.creditcardnumber.bankcard.AccountNumber#
   * getIssuerIdentificationNumber()
   */
  @Override
  public String getIssuerIdentificationNumber()
  {
    return left(accountNumber, 6);
  }

  /*
   * (non-Javadoc)
   * @see
   * us.fatehi.creditcardnumber.bankcard.AccountNumber#getLastFourDigits
   * ()
   */
  @Override
  public String getLastFourDigits()
  {
    return right(accountNumber, 4);
  }

  /*
   * (non-Javadoc)
   * @see us.fatehi.creditcardnumber.bankcard.AccountNumber#
   * getMajorIndustryIdentifier()
   */
  @Override
  public MajorIndustryIdentifier getMajorIndustryIdentifier()
  {
    return majorIndustryIdentifier;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result
             + (accountNumber == null? 0: accountNumber.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * @see us.fatehi.creditcardnumber.bankcard.AccountNumber#
   * hasPrimaryAccountNumber()
   */
  @Override
  public boolean hasPrimaryAccountNumber()
  {
    return !isBlank(accountNumber);
  }

  /**
   * @see us.fatehi.creditcardnumber.PrimaryAccountNumber#isPassesLuhnCheck
   *      ()
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
    return accountNumber;
  }

  private boolean luhnCheck()
  {

    final int length = accountNumber.length();
    int sum = 0;
    boolean alternate = false;
    for (int i = length - 1; i >= 0; i--)
    {
      int digit = Character.digit(accountNumber.charAt(i), 10);
      if (alternate)
      {
        digit = digit * 2;
        digit = digit > 9? digit - 9: digit;
      }
      sum = sum + digit;
      alternate = !alternate;
    }
    final boolean passesLuhnCheck = sum % 10 == 0;
    return passesLuhnCheck;
  }

}
