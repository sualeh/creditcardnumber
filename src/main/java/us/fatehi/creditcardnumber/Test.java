package us.fatehi.creditcardnumber;

import java.util.Date;

public class Test {
  public static void main(String[] args) {
    AccountNumber pan = AccountNumbers.completeAccountNumber("5536-9140-2504-4771");
    ExpirationDate expiration = new ExpirationDate(new Date());
    Name name = new Name("Sualeh", "Fatehi");
    ServiceCode serviceCode = new ServiceCode("225");
    BankCard card = new BankCard(pan, expiration, name, serviceCode);
    System.out.println(card);
  }
}
