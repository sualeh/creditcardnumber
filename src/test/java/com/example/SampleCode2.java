/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2026, Sualeh Fatehi.
 *
 */
package com.example;

import static us.fatehi.creditcardnumber.AccountNumbers.completeAccountNumber;

import us.fatehi.creditcardnumber.AccountNumber;

public class SampleCode2 {

  public static void main(final String[] args) {
    final AccountNumber pan = completeAccountNumber("5266-0922-0141-6174");
    pan.dispose();
    System.out.println(pan);
  }
}
