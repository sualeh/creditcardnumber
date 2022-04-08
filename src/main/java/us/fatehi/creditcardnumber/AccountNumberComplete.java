/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.right;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static us.fatehi.creditcardnumber.AccountNumbers.parseAccountNumber;

final class AccountNumberComplete extends BaseRawData implements AccountNumber {

  private static final long serialVersionUID = -7012531091389412459L;

  // See https://www.iso.org/news/2016/11/Ref2146.html
  private static final int IIN_LEN = 8;
  private static final int BIN_LEN = 6;

  private final DisposableStringData accountNumber;
  private final AccountNumber panSecure;
  private final BinCode binCode;

  AccountNumberComplete(final String rawAccountNumber) {
    super(requireNonNull(rawAccountNumber, "No raw account number provided"));

    final String accountNumberString = parseAccountNumber(trimToEmpty(rawAccountNumber));
    accountNumber = new DisposableStringData(accountNumberString);

    panSecure = AccountNumbers.secureAccountNumber(rawAccountNumber);
    Optional<Integer> code = getBin();
    binCode = code.flatMap(BinCodeReader::getByCode).orElse(null);
  }

  @Override
  public void dispose() {
    super.disposeRawData();
    accountNumber.disposeData();
  }

  @Override
  public boolean exceedsMaximumLength() {
    return panSecure.exceedsMaximumLength();
  }

  @Override
  public String getAccountNumber() {
    return accountNumber.getData();
  }

  @Override
  public int getAccountNumberLength() {
    return panSecure.getAccountNumberLength();
  }

  @Override
  public CardBrand getCardBrand() {
    return panSecure.getCardBrand();
  }

  @Override
  public String getIssuerIdentificationNumber() {
    if (!hasAccountNumber()) {
      return null;
    }
    final String accountNumberString = accountNumber.getData();
    return rightPad(left(accountNumberString, IIN_LEN), IIN_LEN, "0");
  }

  private Optional<Integer> getBin() {
    if (!hasAccountNumber()) {
      return Optional.empty();
    }
    final String accountNumberString = accountNumber.getData();
    return Optional.ofNullable(rightPad(left(accountNumberString, BIN_LEN), BIN_LEN, "0"))
      .map(Integer::parseInt);
  }

  @Override
  public String getLastFourDigits() {
    if (!hasAccountNumber()) {
      return null;
    }
    final String accountNumberString = accountNumber.getData();
    return leftPad(right(accountNumberString, 4), 4, "0");
  }

  @Override
  public MajorIndustryIdentifier getMajorIndustryIdentifier() {
    return panSecure.getMajorIndustryIdentifier();
  }

  @Override
  public boolean hasAccountNumber() {
    return accountNumber.hasData();
  }

  @Override
  public boolean isLengthValid() {
    return panSecure.isLengthValid();
  }

  @Override
  public boolean isPrimaryAccountNumberValid() {
    return panSecure.isPrimaryAccountNumberValid();
  }

  @Override
  public boolean passesLuhnCheck() {
    return panSecure.passesLuhnCheck();
  }

  @Override
  public AccountNumber toSecureAccountNumber() {
    return panSecure;
  }

  @Override
  public BinCode getBinCode()  {
    return binCode;
  }

  @Override
  public String toString() {
    if (hasAccountNumber()) {
      return getAccountNumber();
    } else {
      return panSecure.toString();
    }
  }
}
