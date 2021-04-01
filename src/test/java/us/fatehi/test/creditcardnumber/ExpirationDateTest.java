/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.test.creditcardnumber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.ExpirationDate;

public class ExpirationDateTest {

  @Test
  public void expirationDate_1() {
    final String rawExpirationDate = null;
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasExpirationDate(), is(false));
  }

  @Test
  public void expirationDate_2() {
    final String rawExpirationDate = "\t\t";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasExpirationDate(), is(false));
  }

  @Test
  public void expirationDate_3() {
    final String rawExpirationDate = "AQW";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasExpirationDate(), is(false));
  }

  @Test
  public void expirationDate_4() {
    final String rawExpirationDate = "11";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasExpirationDate(), is(false));
  }

  @Test
  public void expirationDate_5() {
    final String rawExpirationDate = "8888";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasExpirationDate(), is(false));
  }

  @Test
  public void expirationDate_6() {
    final String rawExpirationDate = "121212";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasExpirationDate(), is(false));
  }

  @Test
  public void expirationDate_7() {
    final String rawExpirationDate = "1313";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.hasExpirationDate(), is(false));
  }

  @Test
  public void expirationDate1() {
    final String rawExpirationDate = "1212";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertThat(expirationDate.getRawData(), is(rawExpirationDate));
    assertThat(expirationDate.toString(), is("2012-12"));
    assertThat(expirationDate.hasExpirationDate(), is(true));
    assertThat(expirationDate.getExpirationDate(), is(YearMonth.of(2012, 12)));
  }
}
