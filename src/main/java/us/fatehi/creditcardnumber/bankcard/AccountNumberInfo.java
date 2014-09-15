package us.fatehi.creditcardnumber.bankcard;


public class AccountNumberInfo
  implements AccountNumber
{

  private static final long serialVersionUID = 2002490292247684624L;

  private final MajorIndustryIdentifier majorIndustryIdentifier;
  private final String issuerIdentificationNumber;
  private final String lastFourDigits;
  private final CardBrand cardBrand;
  private final boolean passesLuhnCheck;
  private final int accountNumberLength;
  private final boolean exceedsMaximumLength;

  public AccountNumberInfo(final AccountNumber accountNumber)
  {
    if (accountNumber != null)
    {
      majorIndustryIdentifier = accountNumber.getMajorIndustryIdentifier();
      issuerIdentificationNumber = accountNumber
        .getIssuerIdentificationNumber();
      lastFourDigits = accountNumber.getLastFourDigits();
      cardBrand = accountNumber.getCardBrand();
      passesLuhnCheck = accountNumber.isPassesLuhnCheck();
      accountNumberLength = accountNumber.getAccountNumberLength();
      exceedsMaximumLength = accountNumber.exceedsMaximumLength();
    }
    else
    {
      majorIndustryIdentifier = MajorIndustryIdentifier.unknown;
      issuerIdentificationNumber = "";
      lastFourDigits = "";
      cardBrand = CardBrand.unknown;
      passesLuhnCheck = false;
      accountNumberLength = -1;
      exceedsMaximumLength = false;
    }
  }

  @Override
  public boolean exceedsMaximumLength()
  {
    return exceedsMaximumLength;
  }

  @Override
  public String getAccountNumber()
  {
    throw new IllegalAccessError("Account number is not available");
  }

  @Override
  public int getAccountNumberLength()
  {
    return accountNumberLength;
  }

  @Override
  public CardBrand getCardBrand()
  {
    return cardBrand;
  }

  @Override
  public String getIssuerIdentificationNumber()
  {
    return issuerIdentificationNumber;
  }

  @Override
  public String getLastFourDigits()
  {
    return lastFourDigits;
  }

  @Override
  public MajorIndustryIdentifier getMajorIndustryIdentifier()
  {
    return majorIndustryIdentifier;
  }

  @Override
  public boolean hasPrimaryAccountNumber()
  {
    return false;
  }

  @Override
  public boolean isPassesLuhnCheck()
  {
    return passesLuhnCheck;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return String.format("%s-%s", cardBrand, lastFourDigits);
  }

}
