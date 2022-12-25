/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2023, Sualeh Fatehi.
 *
 */
package us.fatehi.test.creditcardnumber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import us.fatehi.creditcardnumber.ExpirationDate;

public class ExpirationDateTest {

  @Test
  @DisplayName("Raw string is null")
  public void expirationDate_1() {
    final String rawExpirationDate = null;
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasRawData(), is(false));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
  }

  @Test
  @DisplayName("Raw string is blank")
  public void expirationDate_2() {
    final String rawExpirationDate = "\t\t";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasRawData(), is(true));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
  }

  @Test
  @DisplayName("Raw string is alphabetic")
  public void expirationDate_3() {
    final String rawExpirationDate = "AQW";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasRawData(), is(true));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
  }

  @Test
  @DisplayName("Raw string is numeric, but too short")
  public void expirationDate_4() {
    final String rawExpirationDate = "11";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasRawData(), is(true));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
  }

  @Test
  @DisplayName("Raw string is numeric, but invalid")
  public void expirationDate_5() {
    final String rawExpirationDate = "8888";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasRawData(), is(true));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
  }

  @Test
  @DisplayName("Raw string is numeric, but too long")
  public void expirationDate_6() {
    final String rawExpirationDate = "121212";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasRawData(), is(true));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(true));
  }

  @Test
  @DisplayName("Raw string is numeric, but month is out of range")
  public void expirationDate_7() {
    final String rawExpirationDate = "1313";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasRawData(), is(true));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
  }

  @Test
  @DisplayName("Constructor with raw string")
  public void expirationDate1() {
    final String rawExpirationDate = "1212";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.hasRawData(), is(true));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.toString(), is("2012-12"));
    assertThat(expirationDate.hasExpirationDate(), is(true));
    assertThat(expirationDate.getExpirationDate(), is(YearMonth.of(2012, 12)));
  }

  @Test
  @DisplayName("Constructor with java.util.Date")
  public void expirationDate2() {
    final LocalDate localDate = LocalDate.of(2012, 12, 12);
    final Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    final ExpirationDate expirationDate = new ExpirationDate(date);
    assertThat(expirationDate.hasRawData(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
    assertThat(expirationDate.getRawData(), is(nullValue()));
    assertThat(expirationDate.toString(), is("2012-12"));
    assertThat(expirationDate.hasExpirationDate(), is(true));
    assertThat(expirationDate.getExpirationDate(), is(YearMonth.of(2012, 12)));
  }

  @Test
  @DisplayName("Constructor with null date argument")
  public void expirationDate3() {
    final ExpirationDate expirationDate = new ExpirationDate((Date) null);
    assertThat(expirationDate.hasRawData(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
    assertThat(expirationDate.getRawData(), is(nullValue()));
    assertThat(expirationDate.toString(), is(""));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.getExpirationDate(), is(nullValue()));
  }

  @Test
  @DisplayName("Constructor with null string argument")
  // Note: repeated test - also tested with the raw string argument tests
  public void expirationDate4() {
    final ExpirationDate expirationDate = new ExpirationDate((String) null);
    assertThat(expirationDate.hasRawData(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
    assertThat(expirationDate.getRawData(), is(nullValue()));
    assertThat(expirationDate.toString(), is(""));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.getExpirationDate(), is(nullValue()));
  }

  @Test
  @DisplayName("Constructor with integer year and month")
  public void expirationDate5() {
    final ExpirationDate expirationDate = new ExpirationDate(2012, 12);
    assertThat(expirationDate.hasRawData(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
    assertThat(expirationDate.getRawData(), is(nullValue()));
    assertThat(expirationDate.toString(), is("2012-12"));
    assertThat(expirationDate.hasExpirationDate(), is(true));
    assertThat(expirationDate.getExpirationDate(), is(YearMonth.of(2012, 12)));
  }

  @Test
  @DisplayName("Constructor with out of bounds year and month")
  public void expirationDate6() {
    final ExpirationDate expirationDate = new ExpirationDate(13, 13);
    assertThat(expirationDate.hasRawData(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
    assertThat(expirationDate.getRawData(), is(nullValue()));
    assertThat(expirationDate.toString(), is(""));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.getExpirationDate(), is(nullValue()));
  }

  @Test
  @DisplayName("Constructor with no arguments")
  public void expirationDate7() {
    final ExpirationDate expirationDate = new ExpirationDate();
    assertThat(expirationDate.hasRawData(), is(false));
    assertThat(expirationDate.exceedsMaximumLength(), is(false));
    assertThat(expirationDate.getRawData(), is(nullValue()));
    assertThat(expirationDate.toString(), is(""));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.getExpirationDate(), is(nullValue()));
  }

  @Test
  @DisplayName("Equals test")
  public void expirationDateEquals() {
    EqualsVerifier.forClass(ExpirationDate.class).withIgnoredFields("rawData").verify();
  }
}
