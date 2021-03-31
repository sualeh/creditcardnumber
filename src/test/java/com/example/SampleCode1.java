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
import us.fatehi.creditcardnumber.BankCard;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.Name;

public class SampleCode1 {

  public static void main(final String[] args) {
    final AccountNumber pan = AccountNumbers.accountNumber("5266-0922-0141-6174");
    final ExpirationDate expiration = new ExpirationDate(2015, 4);
    final Name name = new Name("Sualeh", "Fatehi");
    final BankCard card = new BankCard(pan, expiration, name);
    System.out.println(card);
  }
}
