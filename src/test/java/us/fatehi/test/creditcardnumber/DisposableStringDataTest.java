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
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import us.fatehi.creditcardnumber.DisposableStringData;

public class DisposableStringDataTest {

  @Test
  public void disposableStringDataEquals() {
    EqualsVerifier.forClass(DisposableStringData.class).verify();
  }

  @Test
  public void dispose() {
    final String dataString = "somedata";
    final DisposableStringData data = new DisposableStringData(dataString);
    assertThat(data.hasData(), is(true));
    assertThat(data.getData(), is(dataString));
    assertThat(data.toString(), is(dataString));
    assertThat(data.subSequence(4, 8).toString(), is("data"));
    assertThat(data.length(), is(8));
    assertThat(data.charAt(0), is('s'));

    assertThrows(ArrayIndexOutOfBoundsException.class, () -> data.charAt(-1));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> data.charAt(8));

    data.disposeData();
    assertThat(data.hasData(), is(false));
    assertThat(data.getData(), is(nullValue()));
    assertThat(data.toString(), is(""));
    assertThat(data.subSequence(0, 0).toString(), is(""));
    assertThat(data.length(), is(0));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> data.charAt(0));
  }

  @Test
  public void empty() {
    final DisposableStringData data = new DisposableStringData(null);
    assertThat(data.hasData(), is(false));
  }
}
