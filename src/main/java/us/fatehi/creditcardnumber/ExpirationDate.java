/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static us.fatehi.creditcardnumber.Utility.non_digit;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/** Parses and represents a card expiration date. */
public final class ExpirationDate extends BaseRawData implements Serializable {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMM");

  private static final long serialVersionUID = 422773685360335298L;

  private final YearMonth expirationDate;

  /** No expiration date. */
  public ExpirationDate() {
    this((String) null);
  }

  /**
   * Expiration date from date. (Deprecated, since it uses a deprecated API. Use other constructors
   * instead.)
   *
   * @param date Date
   * @deprecated Use {@link ExpirationDate#ExpirationDate(int, int)}
   */
  @Deprecated
  public ExpirationDate(final Date date) {
    super(null);
    if (date != null) {
      expirationDate = YearMonth.of(date.getYear() + 1900, date.getMonth() + 1);
    } else {
      expirationDate = null;
    }
  }

  /**
   * Expiration date from year and month.
   *
   * @param year Year
   * @param month Month
   */
  public ExpirationDate(final int year, final int month) {
    super(null);
    YearMonth expirationDate;
    try {
      expirationDate = YearMonth.of(year, month);
    } catch (final Exception e) {
      expirationDate = null;
    }
    this.expirationDate = expirationDate;
  }

  /**
   * Expiration date parsed from raw track data.
   *
   * @param rawExpirationDate Raw track data for expiration date.
   */
  public ExpirationDate(final String rawExpirationDate) {
    super(rawExpirationDate);
    final String expirationDateString =
        non_digit.matcher(trimToEmpty(rawExpirationDate)).replaceAll("");
    YearMonth expirationDate;
    try {
      expirationDate = YearMonth.parse(expirationDateString, formatter);
    } catch (final Exception e) {
      expirationDate = null;
    }
    this.expirationDate = expirationDate;
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
    if (!(obj instanceof ExpirationDate)) {
      return false;
    }
    final ExpirationDate other = (ExpirationDate) obj;
    if (!Objects.equals(expirationDate, other.expirationDate)) {
      return false;
    }
    return true;
  }

  /** See us.fatehi.creditcardnumber.RawData#exceedsMaximumLength() */
  @Override
  public boolean exceedsMaximumLength() {
    return trimToEmpty(getRawData()).length() > 4;
  }

  /**
   * Gets the card expiration date. Returns null if no date is available.
   *
   * @return Card expiration date.
   */
  public YearMonth getExpirationDate() {
    return expirationDate;
  }

  /**
   * Checks whether the card expiration date is available.
   *
   * @return True if the card expiration date is available.
   */
  public boolean hasExpirationDate() {
    return expirationDate != null;
  }

  /** See java.lang.Object#hashCode() */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (expirationDate == null ? 0 : expirationDate.hashCode());
    return result;
  }

  /**
   * Whether the card has expired.
   *
   * @return True if the the card has expired.
   */
  public boolean isExpired() {
    if (!hasExpirationDate()) {
      return true;
    } else {
      return expirationDate.atEndOfMonth().isBefore(LocalDate.now());
    }
  }

  /** See java.lang.Object#toString() */
  @Override
  public String toString() {
    if (hasExpirationDate()) {
      return expirationDate.toString();
    } else {
      return "";
    }
  }
}
