/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2016, Sualeh Fatehi.
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


import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.right;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents a bank card number.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Bank_card_number">Bank
 *      card number</a>
 * @author Sualeh Fatehi
 */
public class AccountNumber
  extends BaseRawData
  implements RawData, Serializable
{

  private static final long serialVersionUID = -7012531091389412459L;

  private final char[] accountNumber;
  private final char[] issuerIdentificationNumber;
  private final char[] lastFourDigits;
  private final AccountNumberInfo accountNumberInfo;

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
   *        Raw primary account number.
   */
  public AccountNumber(final String rawAccountNumber)
  {
    super(rawAccountNumber);

    final String accountNumberString = parseAccountNumber(trimToEmpty(rawAccountNumber));
    accountNumber = accountNumberString.toCharArray();
    issuerIdentificationNumber = rightPad(left(accountNumberString, 6), 6, "0")
      .toCharArray();
    lastFourDigits = leftPad(right(accountNumberString, 4), 4, "0")
      .toCharArray();

    final boolean passesLuhnCheck = luhnCheck();
    final MajorIndustryIdentifier majorIndustryIdentifier = MajorIndustryIdentifier
      .from(accountNumberString);
    final CardBrand cardBrand = CardBrand.from(accountNumberString);
    final int accountNumberLength = accountNumberString.length();
    final boolean isLengthValid = Arrays.asList(13, 14, 15, 16, 19)
      .contains(accountNumberLength);
    final boolean isPrimaryAccountNumberValid = hasAccountNumber()
                                                && isLengthValid
                                                && passesLuhnCheck
                                                && cardBrand != CardBrand.Unknown;
    final boolean exceedsMaximumLength = accountNumberLength > 19;
    accountNumberInfo = new AccountNumberInfo(cardBrand,
                                              majorIndustryIdentifier,
                                              passesLuhnCheck,
                                              accountNumberLength,
                                              isLengthValid,
                                              isPrimaryAccountNumberValid,
                                              exceedsMaximumLength);
  }

  /**
   * Clears sensitive data from memory. Following recommendations from
   * the <a href=
   * "http://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx">Java
   * Cryptography Architecture (JCA) Reference Guide</a>
   */
  public void clear()
  {
    clearRawData();
    clearLastFourDigits();
    clearIssuerIdentificationNumber();

    if (accountNumber != null)
    {
      Arrays.fill(accountNumber, (char) 0);
    }
  }

  /**
   * Clears the Issuer Identification Number from memory. Following
   * recommendations from the <a href=
   * "http://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx">Java
   * Cryptography Architecture (JCA) Reference Guide</a> Also clears raw
   * data.
   */
  public void clearIssuerIdentificationNumber()
  {
    clearRawData();

    if (issuerIdentificationNumber != null)
    {
      Arrays.fill(issuerIdentificationNumber, (char) 0);
    }
    if (accountNumber != null)
    {
      final int fromIndex = 0;
      final int toIndex = Math.min(accountNumber.length, 6);
      Arrays.fill(accountNumber, fromIndex, toIndex, (char) 0);
    }
  }

  /**
   * Clears the last 4 digits of the primary account number from memory.
   * Following recommendations from the <a href=
   * "http://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx">Java
   * Cryptography Architecture (JCA) Reference Guide</a> Also clears raw
   * data.
   */
  public void clearLastFourDigits()
  {
    clearRawData();

    if (lastFourDigits != null)
    {
      Arrays.fill(lastFourDigits, (char) 0);
    }
    if (accountNumber != null)
    {
      final int fromIndex = Math.max(accountNumber.length - 4, 0);
      final int toIndex = accountNumber.length;
      Arrays.fill(accountNumber, fromIndex, toIndex, (char) 0);
    }
  }

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
    if (getClass() != obj.getClass())
    {
      return false;
    }
    final AccountNumber other = (AccountNumber) obj;
    if (!Arrays.equals(accountNumber, other.accountNumber))
    {
      return false;
    }
    if (accountNumberInfo == null)
    {
      if (other.accountNumberInfo != null)
      {
        return false;
      }
    }
    else if (!accountNumberInfo.equals(other.accountNumberInfo))
    {
      return false;
    }
    return true;
  }

  @Override
  public boolean exceedsMaximumLength()
  {
    return accountNumberInfo.exceedsMaximumLength();
  }

  /**
   * Gets the primary account number (PAN) of the bank card.
   *
   * @return Primary account number.
   */
  public String getAccountNumber()
  {
    if (hasAccountNumber())
    {
      return new String(accountNumber);
    }
    else
    {
      return null;
    }
  }

  public int getAccountNumberLength()
  {
    return accountNumberInfo.getAccountNumberLength();
  }

  public CardBrand getCardBrand()
  {
    return accountNumberInfo.getCardBrand();
  }

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
  public String getIssuerIdentificationNumber()
  {
    if (hasIssuerIdentificationNumber())
    {
      return new String(issuerIdentificationNumber);
    }
    else
    {
      return null;
    }
  }

  /**
   * The last 4 digits of the primary account number (PAN), for card
   * identification purposes.
   *
   * @return Last 4 digits of PAN
   */
  public String getLastFourDigits()
  {
    if (hasLastFourDigits())
    {
      return new String(lastFourDigits);
    }
    else
    {
      return null;
    }
  }

  public MajorIndustryIdentifier getMajorIndustryIdentifier()
  {
    return accountNumberInfo.getMajorIndustryIdentifier();
  }

  /**
   * Checks whether the primary account number for the card is
   * available.
   *
   * @return True if the primary account number for the card is
   *         available.
   */
  public boolean hasAccountNumber()
  {
    return accountNumber != null && accountNumber.length > 0
           && accountNumber[0] != 0
           && accountNumber[accountNumber.length - 1] != 0;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(accountNumber);
    result = prime * result
             + (accountNumberInfo == null? 0: accountNumberInfo.hashCode());
    return result;
  }

  /**
   * Checks whether the Issuer Identification Number for the card is
   * available.
   *
   * @return True if the Issuer Identification Number for the card is
   *         available.
   */
  public boolean hasIssuerIdentificationNumber()
  {
    return issuerIdentificationNumber != null
           && issuerIdentificationNumber.length > 0
           && issuerIdentificationNumber[0] != 0;
  }

  /**
   * Checks whether the last 4 digits of the primary account number for
   * the card are available.
   *
   * @return True if the last 4 digits of the primary account number for
   *         the card are available.
   */
  public boolean hasLastFourDigits()
  {
    return lastFourDigits != null && lastFourDigits.length > 0
           && lastFourDigits[0] != 0;
  }

  public boolean isLengthValid()
  {
    return accountNumberInfo.isLengthValid();
  }

  public boolean isPrimaryAccountNumberValid()
  {
    return accountNumberInfo.isPrimaryAccountNumberValid();
  }

  /**
   * Checks whether the primary account number passes the Luhn check.
   *
   * @return True if the primary account number passes the Luhn check.
   * @see <a href="http://en.wikipedia.org/wiki/Luhn_algorithm">Luhn
   *      Algorithm</a>
   */
  public boolean passesLuhnCheck()
  {
    return accountNumberInfo.passesLuhnCheck();
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    if (hasAccountNumber())
    {
      return getAccountNumber();
    }
    else if (hasLastFourDigits())
    {
      return String
        .format("%s-%s", accountNumberInfo.getCardBrand(), getLastFourDigits());
    }
    else
    {
      return accountNumberInfo.getCardBrand().toString();
    }
  }

  private boolean luhnCheck()
  {
    final int length = accountNumber.length;
    int sum = 0;
    boolean alternate = false;
    for (int i = length - 1; i >= 0; i--)
    {
      int digit = Character.digit(accountNumber[i], 10);
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

  private String parseAccountNumber(final String rawAccountNumber)
  {
    if (rawAccountNumber == null)
    {
      return "";
    }
    final StringBuilder builder = new StringBuilder();
    final int length = rawAccountNumber.length();
    for (int offset = 0; offset < length;)
    {
      final int codepoint = rawAccountNumber.codePointAt(offset);
      if (Character.isDigit(codepoint))
      {
        final int digit = Character.digit(codepoint, 10);
        builder.append(String.valueOf(digit));
      }
      offset += Character.charCount(codepoint);
    }
    return builder.toString();
  }

}
