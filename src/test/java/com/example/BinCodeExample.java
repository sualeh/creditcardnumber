package com.example;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.DefaultBinCode;
import us.fatehi.creditcardnumber.DefaultBinCodeReader;

import java.util.Optional;

import static us.fatehi.creditcardnumber.AccountNumbers.completeAccountNumber;

public class BinCodeExample {
  public static void main(String[] args) throws Exception {
    final AccountNumber pan = completeAccountNumber("5266-0922-0141-6174");
    final DefaultBinCodeReader binCodeReader = new DefaultBinCodeReader();
    Optional<DefaultBinCode> binCodeCandidate = binCodeReader.getBin(pan);
    System.out.println(binCodeCandidate);
  }
}
