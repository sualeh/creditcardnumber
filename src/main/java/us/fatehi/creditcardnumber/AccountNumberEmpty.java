package us.fatehi.creditcardnumber;

class AccountNumberEmpty implements AccountNumber {

  private static final long serialVersionUID = 3873941867937638049L;

  @Override
  public void disposeRawData() {
    // No-op
  }

  @Override
  public boolean exceedsMaximumLength() {
    return false;
  }

  @Override
  public String getAccountNumber() {
    return null;
  }

  @Override
  public int getAccountNumberLength() {
    return 0;
  }

  @Override
  public CardBrand getCardBrand() {
    return CardBrand.Unknown;
  }

  @Override
  public String getIssuerIdentificationNumber() {
    return null;
  }

  @Override
  public String getLastFourDigits() {
    return null;
  }

  @Override
  public MajorIndustryIdentifier getMajorIndustryIdentifier() {
    return MajorIndustryIdentifier.unknown;
  }

  @Override
  public String getRawData() {
    return null;
  }

  @Override
  public boolean hasAccountNumber() {
    return false;
  }

  @Override
  public boolean hasRawData() {
    return false;
  }

  @Override
  public boolean isLengthValid() {
    return false;
  }

  @Override
  public boolean isPrimaryAccountNumberValid() {
    return false;
  }

  @Override
  public boolean passesLuhnCheck() {
    return false;
  }

  @Override
  public AccountNumber toSecureAccountNumber() {
    return this;
  }
}
