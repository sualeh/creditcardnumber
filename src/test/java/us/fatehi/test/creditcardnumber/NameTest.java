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

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.Name;

public class NameTest {

  @Test
  public void name_1() {
    final String rawName = null;
    final Name name = new Name(rawName);
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.hasName(), is(false));
  }

  @Test
  public void name_2() {
    final String rawName = "\t\t";
    final Name name = new Name(rawName);
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.hasName(), is(false));
  }

  @Test
  public void name_3() {
    final String rawName = " /                        ";
    final Name name = new Name(rawName);
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.hasName(), is(false));
  }

  @Test
  public void name1() {
    final String rawName = "SUALEH";
    final Name name = new Name(rawName);
    assertThat(name.hasName(), is(true));
    assertThat(name.getRawData(), is("SUALEH"));
    assertThat(name.getFirstName(), is(""));
    assertThat(name.getLastName(), is("Sualeh"));
    assertThat(name.getFullName(), is("Sualeh"));
  }

  @Test
  public void name2() {
    final String rawName = "FATEHI/SUALEH";
    final Name name = new Name(rawName);
    assertThat(name.hasName(), is(true));
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.getFirstName(), is("Sualeh"));
    assertThat(name.getLastName(), is("Fatehi"));
    assertThat(name.getFullName(), is("Sualeh Fatehi"));
  }

  @Test
  public void name3() {
    final String rawName = "FATEHI/SUALEH             ";
    final Name name = new Name(rawName);
    assertThat(name.hasName(), is(true));
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.getFirstName(), is("Sualeh"));
    assertThat(name.getLastName(), is("Fatehi"));
    assertThat(name.getFullName(), is("Sualeh Fatehi"));
  }

  @Test
  public void name4() {
    final String rawName = "MICHAELS OPEN VALUE CARD  ";
    final Name name = new Name(rawName);
    assertThat(name.hasName(), is(true));
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.getFirstName(), is(""));
    assertThat(name.getLastName(), is("Michaels Open Value Card"));
    assertThat(name.getFullName(), is("Michaels Open Value Card"));
  }
}
