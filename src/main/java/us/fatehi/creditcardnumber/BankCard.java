/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static us.fatehi.creditcardnumber.AccountNumbers.emptyAccountNumber;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a bank card, and contains information about the card number, cardholder's name,
 * expiration date, and service code.
 */
public final class BankCard implements Serializable {

  private static final long serialVersionUID = 6253084852668206154L;

  private final AccountNumber pan;
  private final Name name;
  private final ExpirationDate expirationDate;
  private final ServiceCode serviceCode;

  /** No bank card. */
  public BankCard() {
    this(null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan Primary account number
   */
  public BankCard(final AccountNumber pan) {
    this(pan, null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan Primary account number
   * @param expirationDate Card expiration date
   */
  public BankCard(final AccountNumber pan, final ExpirationDate expirationDate) {
    this(pan, expirationDate, null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan Primary account number
   * @param expirationDate Card expiration date
   * @param name Cardholder name
   */
  public BankCard(final AccountNumber pan, final ExpirationDate expirationDate, final Name name) {
    this(pan, expirationDate, name, null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan Primary account number
   * @param expirationDate Card expiration date
   * @param name Cardholder name
   * @param serviceCode Card service code
   */
  public BankCard(
      final AccountNumber pan,
      final ExpirationDate expirationDate,
      final Name name,
      final ServiceCode serviceCode) {
    if (pan != null) {
      this.pan = pan;
    } else {
      this.pan = emptyAccountNumber();
    }

    if (name != null) {
      this.name = name;
    } else {
      this.name = new Name();
    }

    if (expirationDate != null) {
      this.expirationDate = expirationDate;
    } else {
      this.expirationDate = new ExpirationDate();
    }

    if (serviceCode != null) {
      this.serviceCode = serviceCode;
    } else {
      this.serviceCode = new ServiceCode();
    }
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof BankCard)) {
      return false;
    }
    final BankCard other = (BankCard) obj;
    if (!Objects.equals(expirationDate, other.expirationDate)) {
      return false;
    }
    if (!Objects.equals(name, other.name)) {
      return false;
    }
    if (!Objects.equals(pan, other.pan)) {
      return false;
    }
    return true;
  }

  /**
   * Gets the primary account number for the card, if available.
   *
   * @return Primary account number. Null if not available.
   */
  public String getAccountNumber() {
    return pan.getAccountNumber();
  }

  /**
   * See us.fatehi.creditcardnumber.Name#getFullName()
   *
   * @return Card holder's full name
   */
  public String getCardHolderName() {
    return name.getFullName();
  }

  /**
   * Gets the card expiration date.
   *
   * @return Card expiration date.
   */
  public ExpirationDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Gets the cardholder's name.
   *
   * @return Cardholder's name.
   */
  public Name getName() {
    return name;
  }

  /**
   * Gets the primary account number for the card.
   *
   * @return Primary account number.
   */
  public AccountNumber getPrimaryAccountNumber() {
    return pan;
  }

  /** @return the serviceCode */
  public ServiceCode getServiceCode() {
    return serviceCode;
  }

  /**
   * Checks whether the card expiration date is available.
   *
   * @return True if the card expiration date is available.
   */
  public boolean hasExpirationDate() {
    return expirationDate != null && expirationDate.hasExpirationDate();
  }

  /** See java.lang.Object#hashCode() */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (expirationDate == null ? 0 : expirationDate.hashCode());
    result = prime * result + (name == null ? 0 : name.hashCode());
    result = prime * result + (pan == null ? 0 : pan.hashCode());
    return result;
  }

  /**
   * Checks whether the cardholder's name is available.
   *
   * @return True if the cardholder's name is available.
   */
  public boolean hasName() {
    return name != null && name.hasName();
  }

  /**
   * Checks whether the primary account number for the card is available.
   *
   * @return True if the primary account number for the card is available.
   */
  public boolean hasPrimaryAccountNumber() {
    return pan != null && pan.hasAccountNumber();
  }

  public boolean hasServiceCode() {
    return serviceCode != null && serviceCode.hasServiceCode();
  }

  /**
   * Whether the card has expired.
   *
   * @return True if the the card has expired.
   */
  public boolean isExpired() {
    return expirationDate.isExpired();
  }

  @Override
  public String toString() {
    final String NEWLINE = System.getProperty("line.separator");
    final StringBuilder buffer = new StringBuilder();

    buffer.append("Bank Card Information: ").append(NEWLINE);
    if (hasPrimaryAccountNumber()) {
      buffer.append("  Raw Account Number: ");
      buffer.append(pan.getRawData()).append(NEWLINE);
      buffer.append("  Primary Account Number: ");
      buffer.append(pan).append(NEWLINE);
      buffer.append("    Major Industry Identifier: ");
      buffer.append(pan.getMajorIndustryIdentifier()).append(NEWLINE);
      buffer.append("    Issuer Identification Number: ");
      buffer.append(pan.getIssuerIdentificationNumber()).append(NEWLINE);
      buffer.append("    Card Brand: ");
      buffer.append(pan.getCardBrand()).append(NEWLINE);
      buffer.append("    Last Four Digits: ");
      buffer.append(pan.getLastFourDigits()).append(NEWLINE);
      buffer.append("    Passes Luhn Check? ");
      buffer.append(pan.passesLuhnCheck() ? "Yes" : "No").append(NEWLINE);
      buffer.append("    Is Primary Account Number Valid? ");
      buffer.append(pan.isPrimaryAccountNumberValid() ? "Yes" : "No").append(NEWLINE);
    }
    if (hasExpirationDate()) {
      buffer.append("  Expiration Date: ");
      buffer.append(expirationDate).append(NEWLINE);
      buffer.append("    Is Expired? ");
      buffer.append(expirationDate.isExpired() ? "Yes" : "No").append(NEWLINE);
    }
    if (hasName()) {
      buffer.append("  Name: ");
      buffer.append(getName()).append(NEWLINE);
    }
    if (hasServiceCode()) {
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
