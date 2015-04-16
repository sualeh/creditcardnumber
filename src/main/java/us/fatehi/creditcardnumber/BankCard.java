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
import java.util.Date;

/**
 * Represents a bank card, and contains information about the card
 * number, cardholder's name, expiration date, and service code.
 */
public final class BankCard
  implements Serializable
{

  private static final long serialVersionUID = 6253084852668206154L;

  private final PrimaryAccountNumber pan;
  private final Name name;
  private final ExpirationDate expirationDate;
  private final ServiceCode serviceCode;

  /**
   * No bank card.
   */
  public BankCard()
  {
    this(null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan
   *        Primary account number
   */
  public BankCard(final PrimaryAccountNumber pan)
  {
    this(pan, null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan
   *        Primary account number
   * @param expirationDate
   *        Card expiration date
   */
  public BankCard(final PrimaryAccountNumber pan,
                  final ExpirationDate expirationDate)
  {
    this(pan, expirationDate, null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan
   *        Primary account number
   * @param expirationDate
   *        Card expiration date
   * @param name
   *        Cardholder name
   */
  public BankCard(final PrimaryAccountNumber pan,
                  final ExpirationDate expirationDate,
                  final Name name)
  {
    this(pan, expirationDate, name, null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan
   *        Primary account number
   * @param expirationDate
   *        Card expiration date
   * @param name
   *        Cardholder name
   */
  public BankCard(final PrimaryAccountNumber pan,
                  final ExpirationDate expirationDate,
                  final Name name,
                  final ServiceCode serviceCode)
  {
    if (pan != null)
    {
      this.pan = pan;
    }
    else
    {
      this.pan = new AccountNumber();
    }

    if (name != null)
    {
      this.name = name;
    }
    else
    {
      this.name = new Name();
    }

    if (expirationDate != null)
    {
      this.expirationDate = expirationDate;
    }
    else
    {
      this.expirationDate = new ExpirationDate();
    }

    if (serviceCode != null)
    {
      this.serviceCode = serviceCode;
    }
    else
    {
      this.serviceCode = new ServiceCode();
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
    if (!(obj instanceof BankCard))
    {
      return false;
    }
    final BankCard other = (BankCard) obj;
    if (expirationDate == null)
    {
      if (other.expirationDate != null)
      {
        return false;
      }
    }
    else if (!expirationDate.equals(other.expirationDate))
    {
      return false;
    }
    if (name == null)
    {
      if (other.name != null)
      {
        return false;
      }
    }
    else if (!name.equals(other.name))
    {
      return false;
    }
    if (pan == null)
    {
      if (other.pan != null)
      {
        return false;
      }
    }
    else if (!pan.equals(other.pan))
    {
      return false;
    }
    return true;
  }

  /**
   * Gets the primary account number for the card, if available.
   *
   * @return Primary account number. Null if not available.
   */
  public String getAccountNumber()
  {
    return pan.getAccountNumber();
  }

  /**
   * @see us.fatehi.creditcardnumber.Name#getFullName()
   */
  public String getCardHolderName()
  {
    return name.getFullName();
  }

  /**
   * Gets the card expiration date.
   *
   * @return Card expiration date.
   */
  public ExpirationDate getExpirationDate()
  {
    return expirationDate;
  }

  /**
   * @see us.fatehi.creditcardnumber.ExpirationDate#getExpirationDateAsDate()
   */
  public Date getExpirationDateAsDate()
  {
    return expirationDate.getExpirationDateAsDate();
  }

  /**
   * @see us.fatehi.creditcardnumber.ExpirationDate#getExpirationDateAsString()
   */
  public String getExpirationDateAsString()
  {
    return expirationDate.getExpirationDateAsString();
  }

  /**
   * Gets the cardholder's name.
   *
   * @return Cardholder's name.
   */
  public Name getName()
  {
    return name;
  }

  /**
   * Gets the primary account number for the card.
   *
   * @return Primary account number.
   */
  public PrimaryAccountNumber getPrimaryAccountNumber()
  {
    return pan;
  }

  /**
   * @return the serviceCode
   */
  public ServiceCode getServiceCode()
  {
    return serviceCode;
  }

  /**
   * Checks whether the card expiration date is available.
   *
   * @return True if the card expiration date is available.
   */
  public boolean hasExpirationDate()
  {
    return expirationDate != null && expirationDate.hasExpirationDate();
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
             + (expirationDate == null? 0: expirationDate.hashCode());
    result = prime * result + (name == null? 0: name.hashCode());
    result = prime * result + (pan == null? 0: pan.hashCode());
    return result;
  }

  /**
   * Checks whether the cardholder's name is available.
   *
   * @return True if the cardholder's name is available.
   */
  public boolean hasName()
  {
    return name != null && name.hasName();
  }

  /**
   * Checks whether the primary account number for the card is
   * available.
   *
   * @return True if the primary account number for the card is
   *         available.
   */
  public boolean hasPrimaryAccountNumber()
  {
    return pan != null && pan.hasPrimaryAccountNumber();
  }

  public boolean hasServiceCode()
  {
    return serviceCode != null && serviceCode.hasServiceCode();
  }

  /**
   * Whether the card has expired.
   *
   * @return True if the the card has expired.
   */
  public boolean isExpired()
  {
    return expirationDate.isExpired();
  }

  @Override
  public String toString()
  {
    final String NEWLINE = System.getProperty("line.separator");
    final StringBuilder buffer = new StringBuilder();

    buffer.append("Bank Card Information: ").append(NEWLINE);
    if (hasPrimaryAccountNumber())
    {
      buffer.append("  Raw Account Number: ");
      buffer.append(pan.getRawData()).append(NEWLINE);
      buffer.append("  Primary Account Number: ");
      buffer.append(pan).append(NEWLINE);
      buffer.append("  Primary Account Number (Secure): ");
      buffer.append(new AccountNumberInfo(pan)).append(NEWLINE);
      buffer.append("    Major Industry Identifier: ");
      buffer.append(pan.getMajorIndustryIdentifier()).append(NEWLINE);
      buffer.append("    Issuer Identification Number: ");
      buffer.append(pan.getIssuerIdentificationNumber()).append(NEWLINE);
      buffer.append("    Card Brand: ");
      buffer.append(pan.getCardBrand()).append(NEWLINE);
      buffer.append("    Last Four Digits: ");
      buffer.append(pan.getLastFourDigits()).append(NEWLINE);
      buffer.append("    Passes Luhn Check? ");
      buffer.append(pan.passesLuhnCheck()? "Yes": "No").append(NEWLINE);
      buffer.append("    Is Primary Account Number Valid? ");
      buffer.append(pan.isPrimaryAccountNumberValid()? "Yes": "No")
        .append(NEWLINE);
    }
    if (hasExpirationDate())
    {
      buffer.append("  Expiration Date: ");
      buffer.append(getExpirationDateAsString()).append(NEWLINE);
      buffer.append("    Is Expired: ");
      buffer.append(expirationDate.isExpired()? "Yes": "No").append(NEWLINE);
    }
    if (hasName())
    {
      buffer.append("  Name: ");
      buffer.append(getName()).append(NEWLINE);
    }
    if (hasServiceCode())
    {
      final ServiceCode serviceCode = getServiceCode();
      buffer.append("  Service Code: ");
      buffer.append(NEWLINE);
      buffer.append("    ");
      buffer.append(serviceCode.getServiceCode1()).append(NEWLINE);
      buffer.append("    ");
      buffer.append(serviceCode.getServiceCode2()).append(NEWLINE);
      buffer.append("    ");
      buffer.append(serviceCode.getServiceCode3()).append(NEWLINE);
    }

    return buffer.toString();
  }

}
