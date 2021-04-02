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
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.Name;

public class NameTest {

  @Test
  public void emptyName1() {
    final Name name = new Name();
    assertThat(name.getRawData(), is(nullValue()));
    assertThat(name.hasRawData(), is(false));
    assertThat(name.hasName(), is(false));
    assertThat(name.hasFirstName(), is(false));
    assertThat(name.hasLastName(), is(false));
    assertThat(name.getFullName(), is(""));
    assertThat(name.toString(), is(""));
    assertThat(name.exceedsMaximumLength(), is(false));
  }

  @Test
  public void emptyName2() {
    final Name name = new Name(null, null);
    assertThat(name.getRawData(), is(nullValue()));
    assertThat(name.hasRawData(), is(false));
    assertThat(name.hasName(), is(false));
    assertThat(name.hasFirstName(), is(false));
    assertThat(name.hasLastName(), is(false));
    assertThat(name.getFullName(), is(""));
    assertThat(name.toString(), is(""));
    assertThat(name.exceedsMaximumLength(), is(false));
  }

  @Test
  public void emptyName3() {
    final Name name = new Name(null);
    assertThat(name.getRawData(), is(nullValue()));
    assertThat(name.hasRawData(), is(false));
    assertThat(name.hasName(), is(false));
    assertThat(name.hasFirstName(), is(false));
    assertThat(name.hasLastName(), is(false));
    assertThat(name.getFullName(), is(""));
    assertThat(name.toString(), is(""));
    assertThat(name.exceedsMaximumLength(), is(false));
  }

  @Test
  public void firstNameWithLast_1() {
    final Name name = new Name(null, "LAST");
    assertThat(name.getRawData(), is(nullValue()));
    assertThat(name.hasRawData(), is(false));
    assertThat(name.hasName(), is(true));
    assertThat(name.hasFirstName(), is(false));
    assertThat(name.hasLastName(), is(true));
    assertThat(name.getFullName(), is("LAST"));
    assertThat(name.toString(), is("LAST"));
    assertThat(name.exceedsMaximumLength(), is(false));
  }

  @Test
  public void firstNameWithLast_2() {
    final Name name = new Name("\t\t", "LAST");
    assertThat(name.getRawData(), is(nullValue()));
    assertThat(name.hasRawData(), is(false));
    assertThat(name.hasName(), is(true));
    assertThat(name.hasFirstName(), is(false));
    assertThat(name.hasLastName(), is(true));
    assertThat(name.getFullName(), is("LAST"));
    assertThat(name.toString(), is("LAST"));
    assertThat(name.exceedsMaximumLength(), is(false));
  }

  @Test
  public void firstNameWithLast_3() {
    final Name name = new Name("", "VERY LONG LAST NAME THAT IS RIDICULOUSLY LONG");
    assertThat(name.getRawData(), is(nullValue()));
    assertThat(name.hasRawData(), is(false));
    assertThat(name.hasName(), is(true));
    assertThat(name.hasFirstName(), is(false));
    assertThat(name.hasLastName(), is(true));
    assertThat(name.getFullName(), is("VERY LONG LAST NAME THAT IS RIDICULOUSLY LONG"));
    assertThat(name.toString(), is("VERY LONG LAST NAME THAT IS RIDICULOUSLY LONG"));
    assertThat(name.exceedsMaximumLength(), is(false));
  }

  @Test
  public void firstNameWithLast1() {
    final Name name = new Name("FIRST", "LAST");
    assertThat(name.getRawData(), is(nullValue()));
    assertThat(name.hasRawData(), is(false));
    assertThat(name.hasName(), is(true));
    assertThat(name.hasFirstName(), is(true));
    assertThat(name.hasLastName(), is(true));
    assertThat(name.getFullName(), is("FIRST LAST"));
    assertThat(name.toString(), is("FIRST LAST"));
    assertThat(name.exceedsMaximumLength(), is(false));
  }

  @Test
  public void lastNameWithFirst_1() {
    final Name name = new Name("FIRST", null);
    assertThat(name.getRawData(), is(nullValue()));
    assertThat(name.hasRawData(), is(false));
    assertThat(name.hasName(), is(true));
    assertThat(name.hasFirstName(), is(true));
    assertThat(name.hasLastName(), is(false));
    assertThat(name.getFullName(), is("FIRST"));
    assertThat(name.toString(), is("FIRST"));
    assertThat(name.exceedsMaximumLength(), is(false));
  }

  @Test
  public void lastNameWithFirst_2() {
    final Name name = new Name("FIRST", "\t\t");
    assertThat(name.getRawData(), is(nullValue()));
    assertThat(name.hasRawData(), is(false));
    assertThat(name.hasName(), is(true));
    assertThat(name.hasFirstName(), is(true));
    assertThat(name.hasLastName(), is(false));
    assertThat(name.getFullName(), is("FIRST"));
    assertThat(name.toString(), is("FIRST"));
    assertThat(name.exceedsMaximumLength(), is(false));
  }
}
