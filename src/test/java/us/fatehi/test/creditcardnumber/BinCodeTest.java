package us.fatehi.test.creditcardnumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumbers;
import us.fatehi.creditcardnumber.DefaultBinCode;
import us.fatehi.creditcardnumber.DefaultBinCodeReader;

public class BinCodeTest {

  @Test
  public void binCodeTest() throws Exception {
    final String rawAccountNumber = "5266092201416173";
    final AccountNumber pan = AccountNumbers.completeAccountNumber(rawAccountNumber);

    final DefaultBinCodeReader binCodeReader = new DefaultBinCodeReader();
    DefaultBinCode actual = binCodeReader.getBin(pan).get();

    DefaultBinCode expected = new DefaultBinCode();
    expected.setBin(526609);
    expected.setBrand("MASTERCARD");
    expected.setType("CREDIT");
    expected.setCategory("");
    expected.setIssuer("EUROPAY AUSTRIA ZAHlanGSVERKEHRSSYSTEME GMBH");
    expected.setAlpha2("AT");
    expected.setAlpha3("AUT");
    expected.setCountry("Austria");
    expected.setLatitude(47.5162);
    expected.setLongitude(14.5501);
    expected.setBankPhone("");
    expected.setBankUrl("");

    Assertions.assertEquals(expected, actual);
  }
}
