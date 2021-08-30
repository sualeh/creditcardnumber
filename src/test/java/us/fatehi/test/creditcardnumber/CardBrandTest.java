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
import static org.hamcrest.Matchers.sameInstance;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.CardBrand;

/**
 * Use test credit card numbers from
 * https://developer.paypal.com/docs/payflow/payflow-pro/payflow-pro-testing/
 */
public class CardBrandTest {

  @Test
  public void americanExpress() {
    final long[] longCardNumbers = {378282246310005L, 371449635398431L, 378734493671000L};
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.AmericanExpress);
    }
  }

  @Test
  public void dinersClub() {
    final long[] longCardNumbers = {30569309025904L, 38520000023237L};
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.DinersClub);
    }
  }

  @Test
  public void discover() {
    final long[] longCardNumbers = {6011000990139424L, 6011111111111117L};
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.Discover);
    }
  }

  @Test
  public void mir() {
    final long[] longCardNumbers = {
      2200155625277062L,
      2201024313392493L,
      2202037066066964L,
      2203328875927137L,
      2204773387264946L
    };
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.MIR);
    }
  }

  @Test
  public void jcb() {
    final long[] longCardNumbers = {
      180072013113445L,
      180015253702997L,
      210040806417574L,
      210074314120578L
    };
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.JCB);
    }
  }

  @Test
  public void jcb16digit() {
    final long[] longCardNumbers = {3530111333300000L,3566002020360505L};
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.JCB);
    }
  }

  @Test
  public void jcb19digit() {
    final long[] longCardNumbers = {3530253533895208350L,3589962713967657076L};
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.JCB);
    }
  }

  @Test
  public void maestro() {
    final long[] longCardNumbers = {
      5893576249190792L,
      5020197760270594L,
      5038995137100760L,
      5038555438947721L,
      5893695217878046L,
      5038973483815604L
    };
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.Maestro);
    }
  }

  @Test
  public void maestro13digit() {
    final long[] longCardNumbers = {
      5038827764320L,
      5038877268248L,
      6304305000689L,
      6763614233226L
    };
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.Maestro);
    }
  }

  @Test
  public void maestro15digit() {
    final long[] longCardNumbers = {
      502093431938115L,
      501837269893631L,
      503874834497950L,
      630497884045549L
    };
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.Maestro);
    }
  }

  @Test
  public void maestro19digit() {
    final long[] longCardNumbers = {
      5020199451495212630L,
      6763577441211256926L,
      5038363563196126588L,
      6304126516377184290L
    };
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.Maestro);
    }
  }

  @Test
  public void masterCard() {
    final long[] longCardNumbers = {5555555555554444L, 5105105105105100L};
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.MasterCard);
    }
  }

  @Test
  public void masterCard2Series() {
    final long[] longCardNumbers = {2221000000000009L, 2223000048400011L, 2223016768739313L};
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.MasterCard);
    }
  }

  @Test
  public void uatp() {
    final long[] longCardNumbers = {
      168714822757781L,
      164564145638158L,
      128646125763323L,
      163751038165008L,
      171005001833666L,
      144501340152408L
    };
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.UATP);
    }
  }

  @Test
  public void unionPay() {
    final long[] longCardNumbers = {
      6244867756201583L,
      6232270603171725L,
      6223040736535646L,
      6223722731815525L,
      6270855608444425L,
      6270207865205158L
    };
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.UnionPay);
    }
  }

  @Test
  public void visa() {
    final long[] longCardNumbers = {4111111111111111L, 4012888888881881L, 4222222222222L};
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.Visa);
    }
  }

  @Test
  public void visa13digit() {
    final long[] longCardNumbers = {4556258335907L, 4826840371540L, 4539736294508L};
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.Visa);
    }
  }

  @Test
  public void visa19digit() {
    final long[] longCardNumbers = {4916656927157289574L, 4024007121577965158L, 4556980068526399611L};
    for (final long longCardNumber : longCardNumbers) {
      test(longCardNumber, CardBrand.Visa);
    }
  }

  private void test(final long longCardNumber, final CardBrand expectedCardBrand) {
    final String cardNumber = String.valueOf(longCardNumber);
    final CardBrand cardBrand = CardBrand.from(cardNumber);
    assertThat(cardNumber, cardBrand, is(sameInstance(expectedCardBrand)));
  }
}
