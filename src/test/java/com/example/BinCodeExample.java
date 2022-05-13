package com.example;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.BinCode;
import us.fatehi.creditcardnumber.BinCodeReader;
import us.fatehi.creditcardnumber.DefaultBinCodeReader;

import java.util.Optional;

import static us.fatehi.creditcardnumber.AccountNumbers.completeAccountNumber;

public class BinCodeExample {
  public static void main(String[] args) throws Exception {
    final AccountNumber pan = completeAccountNumber("5266-0922-0141-6174");
    final BinCodeReader binCodeReader = new DefaultBinCodeReader();
    Optional<BinCode> binCodeCandidate = binCodeReader.getBin(pan);
    System.out.println(binCodeCandidate);
  }
}
