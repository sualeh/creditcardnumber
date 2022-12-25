/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2023, Sualeh Fatehi.
 *
 */
package com.example;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumbers;
import us.fatehi.creditcardnumber.BankCard;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.Name;
import us.fatehi.creditcardnumber.ServiceCode;

public class ReadmeCode {

  public static void main(final String[] args) throws Exception {
    System.out.println("\n\n");
    example1();
    System.out.println("\n\n");
    example2();
    System.out.println("\n\n");
    example3();
    System.out.println("\n\n");
    example4();
    System.out.println("\n\n");
    example5();
    System.out.println("\n\n");
  }

  private static void example1() {
    System.out.println("### How to Get Bank Card Information\n");

    // Example
    System.out.println(">> Get bank card information");
    final AccountNumber pan = AccountNumbers.completeAccountNumber("5266-0922-0141-6174");
    final ExpirationDate expiration = new ExpirationDate(2015, 4);
    final Name name = new Name("Sualeh", "Fatehi");
    final ServiceCode serviceCode = new ServiceCode("225");
    final BankCard card = new BankCard(pan, expiration, name, serviceCode);
    System.out.println(card);
  }

  private static void example2() {
    System.out.println("### How to Secure the Credit Card Number\n");

    // Example
    System.out.println(">> Secure the credit card number");
    final AccountNumber pan = AccountNumbers.completeAccountNumber("5266-0922-0141-6174");
    pan.dispose();
    System.out.println(pan.getAccountNumber());

    // Additionally, allow garbage collection
    System.out.println(">> Allow garbage collection");
    final AccountNumber securePan = pan.toSecureAccountNumber();
    System.out.println(securePan.getAccountNumber());
  }

  private static void example3() throws Exception {
    System.out.println("### How to Encrypt the Credit Card Number\n");

    // Set-up
    final Key key;
    final Cipher cipher;
    final KeyGenerator kgen = KeyGenerator.getInstance("AES");
    kgen.init(128);
    key = kgen.generateKey();
    cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);

    // Example
    System.out.println(">> Encrypt the credit card number");
    final AccountNumber sealedPan =
        AccountNumbers.sealedAccountNumber("5266-0922-0141-6174", cipher);
    System.out.println(sealedPan.getAccountNumber());

    // Example
    System.out.println(">> Decrypt the credit card number");
    final AccountNumber pan = AccountNumbers.completeAccountNumber(sealedPan, key);
    System.out.println(pan.getAccountNumber());
  }

  private static void example4() {
    System.out.println("### Internationalization is Supported\n");

    // Example
    System.out.println(">> Internationalization");
    final AccountNumber pan = AccountNumbers.completeAccountNumber("५२६६ ०९२२ ०१४१ ६१७४");
    System.out.println(pan.getAccountNumber());
  }

  private static void example5() {
    System.out.println("### Look-ahead Typing\n");

    // Example
    System.out.println(">> Look-ahead Typing");
    final CardBrand cardBrand = CardBrand.from("5366");
    System.out.println(cardBrand);
  }
}
