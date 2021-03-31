/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package com.example;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumbers;

public class SampleCode2 {

  public static void main(final String[] args) {
    final AccountNumber pan = AccountNumbers.accountNumber("5266-0922-0141-6174");
    pan.disposeRawData();
    System.out.println(pan);
  }
}
