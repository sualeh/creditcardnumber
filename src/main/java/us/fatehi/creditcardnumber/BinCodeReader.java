package us.fatehi.creditcardnumber;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public final class BinCodeReader {

  private final CsvToBean<BinCode> csvReader;

  private BinCodeReader() throws URISyntaxException, IOException {
    Reader reader = Files.newBufferedReader(Paths.get(
      ClassLoader.getSystemResource("binlist-data.csv").toURI()));

    this.csvReader = new CsvToBeanBuilder<BinCode>(reader)
      .withType(BinCode.class)
      .withSeparator(',')
      .withIgnoreLeadingWhiteSpace(true)
      .build();
  }

  public static Optional<BinCode> getByCode(int code) {
    try {
      return getInstance().getBinCode(code);
    } catch (URISyntaxException | IOException e) {
      return Optional.empty();
    }
  }

  private Optional<BinCode> getBinCode(int code) {
    return csvReader.stream()
      .filter(binCode -> binCode.getBin() == code)
      .findFirst();
  }

  private static BinCodeReader getInstance() throws URISyntaxException, IOException {
    return new BinCodeReader();
  }

}
