/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2026, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

/** Service code, position 3 values. */
public enum ServiceCode3 implements ServiceCodeType {
  unknown(-1, "Unknown", ""),
  v_0(0, "No restrictions", "PIN required"),
  v_1(1, "No restrictions", "None"),
  v_2(2, "Goods and services only", "None"),
  v_3(3, "ATM only", "PIN required"),
  v_4(4, "Cash only", "None"),
  v_5(5, "Goods and services only", "PIN required"),
  v_6(6, "No restrictions", "Prompt for PIN if PED present"),
  v_7(7, "Goods and services only", "Prompt for PIN if PED present"),
  ;

  private final int value;
  private final String allowedServices;
  private final String pinRequirements;

  ServiceCode3(final int value, final String allowedServices, final String pinRequirements) {
    this.value = value;
    this.allowedServices = allowedServices;
    this.pinRequirements = pinRequirements;
  }

  /**
   * Gets the allowed services.
   *
   * @return Allowed services.
   */
  public String getAllowedServices() {
    return allowedServices;
  }

  /** See ServiceCodeType#getDescription() */
  @Override
  public String getDescription() {
    return "Allowed Services: %s. PIN Requirements: %s."
        .formatted(allowedServices, pinRequirements);
  }

  /**
   * Gets the the PIN requirements.
   *
   * @return PIN requirements.
   */
  public String getPinRequirements() {
    return pinRequirements;
  }

  /** See ServiceCodeType#getValue() */
  @Override
  public int getValue() {
    return value;
  }

  /** See Object#toString() */
  @Override
  public String toString() {
    return "%d - %s".formatted(value, getDescription());
  }
}
