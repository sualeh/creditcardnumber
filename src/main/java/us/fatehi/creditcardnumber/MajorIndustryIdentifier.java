/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2025, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * The first digit of a credit card number is the Major Industry Identifier (MII) (see ISO/IEC
 * 7812), which represents the category of entity which issued the card.
 *
 * <p>See <a href=
 * "https://en.wikipedia.org/wiki/Bank_card_number#Major_Industry_Identifier_.28MII.29">Major
 * Industry Identifier</a>
 */
public enum MajorIndustryIdentifier {
  unknown(-1, "unknown"),
  mii_0(0, "ISO/TC 68 and other future industry assignments"),
  mii_1(1, "Airlines"),
  mii_2(2, "Airlines and other future industry assignments"),
  mii_3(3, "Travel and entertainment and banking/financial"),
  mii_4(4, "Banking and financial"),
  mii_5(5, "Banking and financial"),
  mii_6(6, "Merchandising and banking/financial"),
  mii_7(7, "Petroleum and other future industry assignments"),
  mii_8(8, "Healthcare, telecommunications and other future industry assignments"),
  mii_9(9, "National assignment"),
  ;

  /**
   * Parses MII value.
   *
   * @param accountNumber Card primary account number.
   * @return MII value.
   */
  public static MajorIndustryIdentifier from(final String accountNumber) {
    if (!isBlank(accountNumber)) {
      final int value = Character.digit(accountNumber.charAt(0), 10);
      for (final MajorIndustryIdentifier mii : values()) {
        if (mii.getValue() == value) {
          return mii;
        }
      }
    }
    return unknown;
  }

  private final int value;
  private final String description;

  MajorIndustryIdentifier(final int value, final String description) {
    this.value = value;
    this.description = description;
  }

  /**
   * Gets the description.
   *
   * @return Description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the MII value.
   *
   * @return MII value.
   */
  public int getValue() {
    return value;
  }

  /** See java.lang.Enum#toString() */
  @Override
  public String toString() {
    return "%d - %s".formatted(value, description);
  }
}
