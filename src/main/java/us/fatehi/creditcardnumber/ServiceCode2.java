/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2025, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

/** Service code, position 2 values. */
public enum ServiceCode2 implements ServiceCodeType {
  unknown(-1, "Unknown"),
  v_0(0, "Normal"),
  v_2(2, "By issuer"),
  v_4(4, "By issuer unless explicit bilateral agreeement applies"),
  ;

  private final int value;
  private final String authorizationProcessing;

  ServiceCode2(final int value, final String authorizationProcessing) {
    this.value = value;
    this.authorizationProcessing = authorizationProcessing;
  }

  /**
   * Gets the authorization processing rules.
   *
   * @return Authorization processing rules.
   */
  public String getAuthorizationProcessing() {
    return authorizationProcessing;
  }

  /** See ServiceCodeType#getDescription() */
  @Override
  public String getDescription() {
    return String.format("Authorization Processing: %s.", authorizationProcessing);
  }

  /** See ServiceCodeType#getValue() */
  @Override
  public int getValue() {
    return value;
  }

  /** See Object#toString() */
  @Override
  public String toString() {
    return String.format("%d - %s", value, getDescription());
  }
}
