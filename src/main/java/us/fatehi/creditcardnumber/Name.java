/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2024, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static org.apache.commons.text.WordUtils.capitalizeFully;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/** Parses and represents the cardholder's name. */
public final class Name extends BaseRawData implements Serializable {

  private static final long serialVersionUID = 6735627336462134892L;

  private final String firstName;
  private final String lastName;

  /** No name. */
  public Name() {
    this(null);
  }

  /**
   * Name from raw magnetic track data.
   *
   * @param rawName Raw magnetic track data for name.
   */
  public Name(final String rawName) {
    super(rawName);

    final String[] splitName = trimToEmpty(rawName).split("/");
    firstName = name(splitName, 1);
    lastName = name(splitName, 0);
  }

  /**
   * Name from first and last.
   *
   * @param firstName First name.
   * @param lastName Last name.
   */
  public Name(final String firstName, final String lastName) {
    super(null);
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /** See java.lang.Object#equals(java.lang.Object) */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Name)) {
      return false;
    }
    final Name other = (Name) obj;
    if (!StringUtils.equalsIgnoreCase(firstName, other.firstName)) {
      return false;
    }
    if (!StringUtils.equalsIgnoreCase(lastName, other.lastName)) {
      return false;
    }
    return true;
  }

  /** See us.fatehi.creditcardnumber.RawData#exceedsMaximumLength() */
  @Override
  public boolean exceedsMaximumLength() {
    return trimToEmpty(getRawData()).length() > 26;
  }

  /**
   * Gets the first name.
   *
   * @return First name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets the full name.
   *
   * @return full name.
   */
  public String getFullName() {
    final StringBuilder buffer = new StringBuilder();
    if (hasFirstName()) {
      buffer.append(trimToEmpty(firstName));
    }
    if (hasFullName()) {
      buffer.append(" ");
    }
    if (hasLastName()) {
      buffer.append(trimToEmpty(lastName));
    }
    return buffer.toString();
  }

  /**
   * Gets the last name.
   *
   * @return Last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Checks whether the first name is available.
   *
   * @return True if the first name is available.
   */
  public boolean hasFirstName() {
    return !isBlank(firstName);
  }

  /**
   * Checks whether the full name (first and last) is available.
   *
   * @return True if the full name is available.
   */
  public boolean hasFullName() {
    return hasFirstName() && hasLastName();
  }

  /** See java.lang.Object#hashCode() */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (firstName == null ? 0 : firstName.toUpperCase().hashCode());
    return prime * result + (lastName == null ? 0 : lastName.toUpperCase().hashCode());
  }

  /**
   * Checks whether the last name is available.
   *
   * @return True if the last name is available.
   */
  public boolean hasLastName() {
    return !isBlank(lastName);
  }

  /**
   * Checks whether the name (either first or last) is available.
   *
   * @return True if the name is available.
   */
  public boolean hasName() {
    return hasFirstName() || hasLastName();
  }

  /** See java.lang.Object#toString() */
  @Override
  public String toString() {
    return getFullName();
  }

  private String name(final String[] splitName, final int position) {
    String name;
    if (splitName.length > position) {
      name = capitalizeFully(trimToEmpty(splitName[position]), new char[] {'.', '\'', ' '});
    } else {
      name = "";
    }

    return name;
  }
}
