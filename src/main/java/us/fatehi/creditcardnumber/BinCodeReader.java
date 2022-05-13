package us.fatehi.creditcardnumber;

import java.util.Optional;

public interface BinCodeReader {

  Optional<BinCode> getBin(AccountNumber accountNumber);
}
