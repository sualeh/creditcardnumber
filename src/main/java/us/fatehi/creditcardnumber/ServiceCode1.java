/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

/** Service code, position 1 values. */
public enum ServiceCode1 implements ServiceCodeType {
  unknown(-1, "Unknown", ""),
  v_1(1, "International interchange", "None"),
  v_2(2, "International interchange", "Integrated circuit card"),
  v_5(5, "National interchange", "None"),
  v_6(6, "National interchange", "Integrated circuit card"),
  v_7(7, "Private", "None"),
  v_9(9, "Test", "Test"),
  ;

  private final int value;
  private final String interchange;
  private final String technology;

  ServiceCode1(final int value, final String interchange, final String technology) {
    this.value = value;
    this.interchange = interchange;
    this.technology = technology;
  }

  /** See ServiceCodeType#getDescription() */
  @Override
  public String getDescription() {
    return String.format("Interchange: %s. Technology: %s.", interchange, technology);
  }

  /**
   * Gets the interchange rules.
   *
   * @return Interchange rules.
   */
  public String getInterchange() {
    return interchange;
  }

  /**
   * Gets the the technology.
   *
   * @return Technology.
   */
  public String getTechnology() {
    return technology;
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
