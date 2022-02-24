/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) २0१४-२0१६, Sualeh Fatehi.
 *
 */
package com.example;

import static us.fatehi.creditcardnumber.AccountNumbers.accountNumberComplete;

import us.fatehi.creditcardnumber.AccountNumber;

public class SampleCode3 {

  public static void main(final String[] args) {
    final AccountNumber pan = accountNumberComplete("५२६६ ०९२२ ०१४१ ६१७४");
    System.out.println(pan);
  }
}
