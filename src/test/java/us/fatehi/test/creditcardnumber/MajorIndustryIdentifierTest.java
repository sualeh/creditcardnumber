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

import us.fatehi.creditcardnumber.MajorIndustryIdentifier;

public class MajorIndustryIdentifierTest {

  @Test
  public void description() {
    assertThat(MajorIndustryIdentifier.unknown.getDescription(), is("unknown"));
  }

  @Test
  public void from() {
    assertThat(MajorIndustryIdentifier.from(null), is(MajorIndustryIdentifier.unknown));
    assertThat(MajorIndustryIdentifier.from("\t\t"), is(MajorIndustryIdentifier.unknown));
    assertThat(MajorIndustryIdentifier.from("abc"), is(MajorIndustryIdentifier.unknown));
    assertThat(MajorIndustryIdentifier.from("23"), is(MajorIndustryIdentifier.mii_2));
  }
}
