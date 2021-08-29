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
import us.fatehi.creditcardnumber.ServiceCode;

public class ReadmeCode {

  public static void main(final String[] args) {
    example1();
    System.out.println("\n\n");
    example2();
    System.out.println("\n\n");
    example3();
  }

  private static void example1() {
    System.out.println("# How to Get Bank Card Information\n");
    final AccountNumber pan = AccountNumbers.accountNumber("5266-0922-0141-6174");
    final ExpirationDate expiration = new ExpirationDate(2015, 4);
    final Name name = new Name("Sualeh", "Fatehi");
    final ServiceCode serviceCode = new ServiceCode("225");
    final BankCard card = new BankCard(pan, expiration, name, serviceCode);
    System.out.println(card);
  }

  private static void example2() {
    System.out.println("# How to Secure the Credit Card Number\n");
    final AccountNumber pan = AccountNumbers.accountNumber("5266-0922-0141-6174");
    pan.dispose();
    System.out.println(pan.getAccountNumber());

    // Additionally, allow garbage collection
    System.out.println("## Allow garbage collection");
    final AccountNumber securePan = pan.toSecureAccountNumber();
    System.out.println(securePan.getAccountNumber());
  }

  private static void example3() {
    System.out.println("# Internationalization is Supported\n");
    final AccountNumber pan = AccountNumbers.accountNumber("५२६६ ०९२२ ०१४१ ६१७४");
    System.out.println(pan.getAccountNumber());
  }
}
