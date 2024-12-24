/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2025, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static us.fatehi.creditcardnumber.Utility.non_digit;

import java.io.Serializable;
import java.util.Objects;

/**
 * See <a href= "https://en.wikipedia.org/wiki/Magnetic_stripe_card#Financial_cards">Wikipedia:
 * Financial Cards</a>
 */
public final class ServiceCode extends BaseRawData implements Serializable {

  private static final long serialVersionUID = -5127753346282374841L;

  private final String serviceCode;
  private final ServiceCode1 serviceCode1;
  private final ServiceCode2 serviceCode2;
  private final ServiceCode3 serviceCode3;

  /** Unknown service code. */
  public ServiceCode() {
    this(null);
  }

  /**
   * Service code from string.
   *
   * @param rawServiceCode Raw service code from magnetic track data.
   */
  public ServiceCode(final String rawServiceCode) {
    super(rawServiceCode);

    serviceCode = non_digit.matcher(trimToEmpty(rawServiceCode)).replaceAll("");

    serviceCode1 = serviceCode(0, ServiceCode1.unknown);
    serviceCode2 = serviceCode(1, ServiceCode2.unknown);
    serviceCode3 = serviceCode(2, ServiceCode3.unknown);
  }

  /** See java.lang.Object#equals(java.lang.Object) */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ServiceCode)) {
      return false;
    }
    final ServiceCode other = (ServiceCode) obj;
    return Objects.equals(serviceCode, other.serviceCode);
  }

  @Override
  public boolean exceedsMaximumLength() {
    return trimToEmpty(getRawData()).length() > 3;
  }

  /**
   * Gets the parsed service code.
   *
   * @return Service code.
   */
  public String getServiceCode() {
    return serviceCode;
  }

  /**
   * Gets the service code position 1 value.
   *
   * @return Service code position 1.
   */
  public ServiceCode1 getServiceCode1() {
    return serviceCode1;
  }

  /**
   * Gets the service code position 2 value.
   *
   * @return Service code position 2.
   */
  public ServiceCode2 getServiceCode2() {
    return serviceCode2;
  }

  /**
   * Gets the service code position 3 value.
   *
   * @return Service code position 3.
   */
  public ServiceCode3 getServiceCode3() {
    return serviceCode3;
  }

  /** See java.lang.Object#hashCode() */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (serviceCode == null ? 0 : serviceCode.hashCode());
    return result;
  }

  public boolean hasServiceCode() {
    return !(serviceCode1 == ServiceCode1.unknown
        || serviceCode2 == ServiceCode2.unknown
        || serviceCode3 == ServiceCode3.unknown);
  }

  /** See java.lang.Object#toString() */
  @Override
  public String toString() {
    return serviceCode;
  }

  private <S extends Enum<S> & ServiceCodeType> S serviceCode(
      final int position, final S defaultServiceCode) {
    if (serviceCode.length() > position) {
      final int value = Character.digit(serviceCode.charAt(position), 10);
      final S[] serviceCodes = defaultServiceCode.getDeclaringClass().getEnumConstants();
      for (final S serviceCode : serviceCodes) {
        if (serviceCode.getValue() == value) {
          return serviceCode;
        }
      }
    }
    return defaultServiceCode;
  }
}
