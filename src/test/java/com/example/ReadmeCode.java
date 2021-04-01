package com.example;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumbers;
import us.fatehi.creditcardnumber.BankCard;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.Name;

public class ReadmeCode {

  public static void main(final String[] args) {
    example1();
    System.out.println("\n\n");
    example2();
    System.out.println("\n\n");
    example3();
  }

  private static void example1() {
    final AccountNumber pan = AccountNumbers.accountNumber("5266-0922-0141-6174");
    final ExpirationDate expiration = new ExpirationDate(2015, 4);
    final Name name = new Name("Sualeh", "Fatehi");
    final BankCard card = new BankCard(pan, expiration, name);
    System.out.println(card);
  }

  private static void example2() {
    final AccountNumber pan = AccountNumbers.accountNumber("5266-0922-0141-6174");
    pan.dispose();
    System.out.println(pan);
  }

  private static void example3() {
    final AccountNumber pan = AccountNumbers.accountNumber("५२६६ ०९२२ ०१४१ ६१७४");
    System.out.println(pan);
  }
}
