package us.fatehi.creditcardnumber;

import java.util.Optional;

public interface BinCodeReader<T> {

  Optional<T> getBin(AccountNumber accountNumber);
}
