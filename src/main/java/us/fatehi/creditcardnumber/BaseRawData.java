/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2026, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

public abstract class BaseRawData implements RawData {

  private final DisposableStringData rawData;

  protected BaseRawData(final String rawData) {
    this.rawData = new DisposableStringData(rawData);
  }

  /** {@inheritDoc} */
  @Override
  public void disposeRawData() {
    rawData.disposeData();
  }

  /** {@inheritDoc} */
  @Override
  public String getRawData() {
    return rawData.getData();
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasRawData() {
    return rawData.hasData();
  }
}
