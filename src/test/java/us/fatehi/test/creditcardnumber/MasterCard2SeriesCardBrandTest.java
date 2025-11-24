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
import static org.hamcrest.Matchers.sameInstance;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.CardBrand;

/**
 * Use test credit card numbers from
 * https://developer.paypal.com/docs/payflow/payflow-pro/payflow-pro-testing/
 */
public class MasterCard2SeriesCardBrandTest {

  @Test
  public void masterCard2Series_1negative() {
    final long longCardNumber = 2221000000000000L;
    for (long i = 1; i <= 10; i++) {
      test(longCardNumber - i, CardBrand.Unknown);
    }
  }

  @Test
  public void masterCard2Series_1positive() {
    final long longCardNumber = 2221000000000000L;
    for (long i = 0; i <= 10; i++) {
      test(longCardNumber + i, CardBrand.MasterCard);
    }
  }

  @Test
  public void masterCard2Series_2negative() {
    final long longCardNumber = 2720999999999999L;
    for (long i = 1; i <= 10; i++) {
      test(longCardNumber + i, CardBrand.Unknown);
    }
  }

  @Test
  public void masterCard2Series_2positive() {
    final long longCardNumber = 2720999999999999L;
    for (long i = 0; i <= 10; i++) {
      test(longCardNumber - i, CardBrand.MasterCard);
    }
  }

  private void test(final long longCardNumber, final CardBrand expectedCardBrand) {
    final String cardNumber = String.valueOf(longCardNumber);
    final CardBrand cardBrand = CardBrand.from(cardNumber);
    assertThat(cardNumber, cardBrand, is(sameInstance(expectedCardBrand)));
  }
}
