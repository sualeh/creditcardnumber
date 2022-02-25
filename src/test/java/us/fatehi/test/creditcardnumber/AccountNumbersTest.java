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

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumbers;

public class AccountNumbersTest {

  private final AccountNumber empty = AccountNumbers.emptyAccountNumber();
  private final AccountNumber last4 = AccountNumbers.accountNumberLastFour("5266092201416174");
  private final AccountNumber secure = AccountNumbers.secureAccountNumber("5266092201416174");
  private final AccountNumber pan1 = AccountNumbers.completeAccountNumber("5266092201416174");
  private final AccountNumber pan1a = AccountNumbers.completeAccountNumber("5266092201416174");
  private final AccountNumber pan2 = AccountNumbers.completeAccountNumber("5266092201416173");

  @Test
  public void equals_Pan() {
    final boolean equals1 = AccountNumbers.equals(pan1, pan1a);
    assertThat(equals1, is(true));

    final boolean equals2 = AccountNumbers.equals(pan1a, pan1);
    assertThat(equals2, is(true));
  }

  @Test
  public void equals1() {
    final boolean equals = AccountNumbers.equals(pan1, pan2);
    assertThat(equals, is(false));
  }

  @Test
  public void equalsNone_Empty() {
    for (final AccountNumber accountNumber : new AccountNumber[] {pan1, pan2, secure, last4}) {
      final boolean equals1 = AccountNumbers.equals(empty, accountNumber);
      assertThat(equals1, is(false));

      final boolean equals2 = AccountNumbers.equals(accountNumber, empty);
      assertThat(equals2, is(false));
    }
  }

  @Test
  public void equalsNone_Last4() {
    for (final AccountNumber accountNumber : new AccountNumber[] {pan1, pan2, secure, empty}) {
      final boolean equals1 = AccountNumbers.equals(last4, accountNumber);
      assertThat(equals1, is(false));

      final boolean equals2 = AccountNumbers.equals(accountNumber, last4);
      assertThat(equals2, is(false));
    }
  }

  @Test
  public void equalsNone_Pan() {
    for (final AccountNumber accountNumber : new AccountNumber[] {pan2, secure, empty, last4}) {
      final boolean equals1 = AccountNumbers.equals(pan1, accountNumber);
      assertThat(equals1, is(false));

      final boolean equals2 = AccountNumbers.equals(accountNumber, pan1);
      assertThat(equals2, is(false));
    }
  }

  @Test
  public void equalsNone_Secure() {
    for (final AccountNumber accountNumber : new AccountNumber[] {pan1, pan2, empty, last4}) {
      final boolean equals1 = AccountNumbers.equals(secure, accountNumber);
      assertThat(equals1, is(false));

      final boolean equals2 = AccountNumbers.equals(accountNumber, secure);
      assertThat(equals2, is(false));
    }
  }

  @Test
  public void equalsNotOverridden() {

    for (final AccountNumber accountNumber1 :
        new AccountNumber[] {pan1, pan2, secure, last4, empty}) {
      for (final AccountNumber accountNumber2 :
          new AccountNumber[] {pan1, pan2, secure, last4, empty}) {

        if (accountNumber1 == accountNumber2) {
          continue;
        }

        final boolean equals1 = accountNumber1.equals(accountNumber2);
        assertThat(equals1, is(false));

        final boolean equals2 = accountNumber2.equals(accountNumber1);
        assertThat(equals2, is(false));
      }
    }

    final boolean equals3 = pan1.equals(pan1a);
    assertThat(equals3, is(false));

    final boolean equals4 = pan1a.equals(pan1);
    assertThat(equals4, is(false));
  }

  @Test
  public void equalsNull1() {
    final boolean equals = AccountNumbers.equals(null, null);
    assertThat(equals, is(false));
  }

  @Test
  public void equalsNull2() {
    final boolean equals = AccountNumbers.equals(pan1, null);
    assertThat(equals, is(false));
  }

  @Test
  public void equalsNull3() {
    final boolean equals = AccountNumbers.equals(null, pan1);
    assertThat(equals, is(false));
  }

  @Test
  public void selfEquals() {
    for (final AccountNumber accountNumber :
        new AccountNumber[] {pan1, pan2, secure, last4, empty}) {
      final boolean equals = AccountNumbers.equals(accountNumber, accountNumber);
      assertThat(equals, is(true));
    }
  }
}
