/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2026, Sualeh Fatehi.
 *
 */
package us.fatehi.test.creditcardnumber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.ExpirationDate;

public class IsExpiredTest {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMM");

  @Test
  public void isExpired1() {
    final YearMonth expirationDateNextMonth = YearMonth.now().plus(1, ChronoUnit.MONTHS);
    check(expirationDateNextMonth, false);
  }

  @Test
  public void isExpired2() {
    final YearMonth expirationDateThisMonth = YearMonth.now();
    check(expirationDateThisMonth, false);
  }

  @Test
  public void isExpired3() {
    final YearMonth expirationDateLastMonth = YearMonth.now().minus(1, ChronoUnit.MONTHS);
    check(expirationDateLastMonth, true);
  }

  @Test
  public void isExpired4() {
    final ExpirationDate expirationDate = new ExpirationDate();
    assertThat(expirationDate.hasRawData(), is(false));
    assertThat(expirationDate.hasExpirationDate(), is(false));
    assertThat(expirationDate.isExpired(), is(true));
    assertThat(expirationDate.getExpirationDate(), is(nullValue()));
  }

  private void check(final YearMonth expectedExpirationDate, final boolean expired) {
    final String rawExpirationDate = formatter.format(expectedExpirationDate);
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasExpirationDate(), is(true));
    assertThat(expirationDate.isExpired(), is(expired));
    assertThat(expirationDate.getExpirationDate(), is(expectedExpirationDate));
  }
}
