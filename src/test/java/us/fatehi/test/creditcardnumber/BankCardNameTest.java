/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2024, Sualeh Fatehi.
 *
 */
package us.fatehi.test.creditcardnumber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import us.fatehi.creditcardnumber.Name;

public class BankCardNameTest {

  @Test
  public void lastNameWithFirst_3() {
    final Name name = new Name("VERY LONG FIRST NAME THAT IS RIDICULOUSLY LONG", "");
    assertThat(name.getRawData(), is(nullValue()));
    assertThat(name.hasRawData(), is(false));
    assertThat(name.hasName(), is(true));
    assertThat(name.hasFirstName(), is(true));
    assertThat(name.hasLastName(), is(false));
    assertThat(name.getFullName(), is("VERY LONG FIRST NAME THAT IS RIDICULOUSLY LONG"));
    assertThat(name.toString(), is("VERY LONG FIRST NAME THAT IS RIDICULOUSLY LONG"));
    assertThat(name.exceedsMaximumLength(), is(false));
  }

  @Test
  public void longName_1() {
    final String rawName = "VERY LONG LAST NAME THAT IS RIDICULOUSLY LONG/";
    final Name name = new Name(rawName);
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.hasRawData(), is(true));
    assertThat(name.hasName(), is(true));
    assertThat(name.hasFirstName(), is(false));
    assertThat(name.hasLastName(), is(true));
    assertThat(name.getFullName(), is("Very Long Last Name That Is Ridiculously Long"));
    assertThat(name.toString(), is("Very Long Last Name That Is Ridiculously Long"));
    assertThat(name.exceedsMaximumLength(), is(true));
  }

  @Test
  public void longName_2() {
    final String rawName = "/VERY LONG FIRST NAME THAT IS RIDICULOUSLY LONG";
    final Name name = new Name(rawName);
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.hasRawData(), is(true));
    assertThat(name.hasName(), is(true));
    assertThat(name.hasFirstName(), is(true));
    assertThat(name.hasLastName(), is(false));
    assertThat(name.getFullName(), is("Very Long First Name That Is Ridiculously Long"));
    assertThat(name.toString(), is("Very Long First Name That Is Ridiculously Long"));
    assertThat(name.exceedsMaximumLength(), is(true));
  }

  @Test
  public void longName_3() {
    final String rawName =
        "VERY LONG LAST NAME THAT IS RIDICULOUSLY LONG/VERY LONG FIRST NAME THAT IS RIDICULOUSLY LONG";
    final Name name = new Name(rawName);
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.hasRawData(), is(true));
    assertThat(name.hasName(), is(true));
    assertThat(name.hasFirstName(), is(true));
    assertThat(name.hasLastName(), is(true));
    assertThat(
        name.getFullName(),
        is(
            "Very Long First Name That Is Ridiculously Long Very Long Last Name That Is Ridiculously Long"));
    assertThat(
        name.toString(),
        is(
            "Very Long First Name That Is Ridiculously Long Very Long Last Name That Is Ridiculously Long"));
    assertThat(name.exceedsMaximumLength(), is(true));
  }

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

  @Test
  public void nameEquals() {
    EqualsVerifier.forClass(Name.class).withIgnoredFields("rawData").verify();
  }
}
