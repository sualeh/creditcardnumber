/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Credit Card Number console application. */
public class Main {

  public static void main(final String[] args) throws Exception {
    Version.main(new String[0]);

    final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      System.out.println("** Press <Ctrl-C> to quit **");
      getBankCardInformation(in);
    }
  }

  private static void getBankCardInformation(final BufferedReader in) throws IOException {
    while (true) {
      System.out.print("Primary Account Number: ");
      final String line = in.readLine();
      if (!isBlank(line)) {
        final AccountNumber pan = new AccountNumber(line);
        final BankCard card = new BankCard(pan);
        System.out.println(card);
      }
    }
  }
}
