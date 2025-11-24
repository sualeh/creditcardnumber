/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2026, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import java.util.Arrays;

public final class DisposableStringData implements CharSequence {

  private final char[] data;

  public DisposableStringData(final String data) {
    if (data != null) {
      this.data = data.toCharArray();
    } else {
      this.data = new char[0];
    }
  }

  /** {@inheritDoc} */
  @Override
  public char charAt(final int i) {
    if (i < 0 || i >= length()) {
      throw new ArrayIndexOutOfBoundsException(i);
    }
    return data[i];
  }

  /**
   * Wipes the data from memory. Following recommendations from the <a href=
   * "http://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx">Java
   * Cryptography Architecture (JCA) Reference Guide</a> Also wipes raw data.
   */
  public void disposeData() {
    Arrays.fill(data, (char) 0);
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final DisposableStringData other = (DisposableStringData) obj;
    return Arrays.equals(data, other.data);
  }

  /**
   * Gets data if available, or returns null.
   *
   * @return Data if available, or null
   */
  public String getData() {
    if (hasData()) {
      return new String(data);
    } else {
      return null;
    }
  }

  /**
   * Checks whether data is available.
   *
   * @return Whether data is available
   */
  public boolean hasData() {
    return data.length > 0 && !Arrays.equals(new char[data.length], data);
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(data);
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public int length() {
    if (hasData()) {
      return data.length;
    } else {
      return 0;
    }
  }

  /** {@inheritDoc} */
  @Override
  public CharSequence subSequence(final int start, final int end) {
    return toString().subSequence(start, end);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    if (hasData()) {
      return new String(data);
    } else {
      return "";
    }
  }
}
