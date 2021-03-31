/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

public interface RawData {

  /**
   * Wipes raw data for security reasons. Following recommendations from the <a href=
   * "http://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx">Java
   * Cryptography Architecture (JCA) Reference Guide</a>
   */
  void disposeRawData();

  /**
   * Whether the raw data exceeds the maximum length allowed.
   *
   * @return True if too long
   */
  boolean exceedsMaximumLength();

  /**
   * Raw data.
   *
   * @return Raw data
   */
  String getRawData();

  /**
   * Whether raw data is present.
   *
   * @return True if raw data is available.
   */
  boolean hasRawData();
}
