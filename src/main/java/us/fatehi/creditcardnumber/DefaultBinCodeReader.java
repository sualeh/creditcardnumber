package us.fatehi.creditcardnumber;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.rightPad;

public class DefaultBinCodeReader implements BinCodeReader<DefaultBinCode> {

  private final CsvToBean<DefaultBinCode> csvReader;

  private static final int BIN_LEN = 6;

  public DefaultBinCodeReader() throws Exception {
    Reader reader = Files.newBufferedReader(getCsvPath());

    this.csvReader = new CsvToBeanBuilder<DefaultBinCode>(reader)
      .withType(DefaultBinCode.class)
      .withSeparator(',')
      .withIgnoreLeadingWhiteSpace(true)
      .build();
  }

  public Optional<DefaultBinCode> getBin(AccountNumber accountNumber) {
    if (!accountNumber.hasAccountNumber()) {
      return Optional.empty();
    }
    return Optional.ofNullable(rightPad(left(accountNumber.getAccountNumber(), BIN_LEN), BIN_LEN, "0"))
      .map(Integer::parseInt)
      .flatMap(code -> csvReader.stream()
        .filter(binCode -> binCode.getBin() == code)
        .findFirst());
  }

  public Path getCsvPath() throws Exception {
    return Paths.get(ClassLoader.getSystemResource("binlist-data.csv").toURI());
  }

}
