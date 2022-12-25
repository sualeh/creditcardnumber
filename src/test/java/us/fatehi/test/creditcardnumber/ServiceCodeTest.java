/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2023, Sualeh Fatehi.
 *
 */
package us.fatehi.test.creditcardnumber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import us.fatehi.creditcardnumber.ServiceCode;
import us.fatehi.creditcardnumber.ServiceCode1;
import us.fatehi.creditcardnumber.ServiceCode2;
import us.fatehi.creditcardnumber.ServiceCode3;

public class ServiceCodeTest {

  @Test
  public void serviceCode_0() {
    final ServiceCode serviceCode = new ServiceCode();
    assertThat(serviceCode.getRawData(), is(nullValue()));
    assertThat("Should not have service code", !serviceCode.hasServiceCode(), is(true));
  }

  @Test
  public void serviceCode_1() {
    final String rawServiceCode = null;
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should not have service code", !serviceCode.hasServiceCode(), is(true));
  }

  @Test
  public void serviceCode_2() {
    final String rawServiceCode = "\t\t";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should not have service code", !serviceCode.hasServiceCode(), is(true));
  }

  @Test
  public void serviceCode_3() {
    final String rawServiceCode = "AQW";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should not have service code", !serviceCode.hasServiceCode(), is(true));
  }

  @Test
  public void serviceCode_4() {
    final String rawServiceCode = "10";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat(serviceCode.getServiceCode1(), is(ServiceCode1.v_1));
    assertThat(serviceCode.getServiceCode2(), is(ServiceCode2.v_0));
    assertThat("Should not have service code", !serviceCode.hasServiceCode(), is(true));
  }

  @Test
  public void serviceCode_5() {
    final String rawServiceCode = "888";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat(serviceCode.getServiceCode1(), is(ServiceCode1.unknown));
    assertThat(serviceCode.getServiceCode2(), is(ServiceCode2.unknown));
    assertThat(serviceCode.getServiceCode3(), is(ServiceCode3.unknown));
    assertThat("Should not have service code", !serviceCode.hasServiceCode(), is(true));
  }

  @Test
  public void serviceCode_6() {
    for (final String s1 : new String[] {"1", "8"}) {
      for (final String s2 : new String[] {"2", "8"}) {
        for (final String s3 : new String[] {"1", "8"}) {
          final String serviceCodeString = s1 + s2 + s3;
          final boolean isValid = !serviceCodeString.contains("8");
          final ServiceCode serviceCode = new ServiceCode(serviceCodeString);
          assertThat(serviceCode.hasServiceCode(), is(isValid));
        }
      }
    }
  }

  @Test
  public void serviceCode1() {
    final String rawServiceCode = "101";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should have service code", serviceCode.hasServiceCode(), is(true));
    assertThat(serviceCode.getServiceCode1(), is(ServiceCode1.v_1));
    assertThat(serviceCode.getServiceCode2(), is(ServiceCode2.v_0));
    assertThat(serviceCode.getServiceCode3(), is(ServiceCode3.v_1));
  }

  @Test
  public void serviceCode2() {
    final String rawServiceCode = "222 ";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should have service code", serviceCode.hasServiceCode(), is(true));

    final ServiceCode1 serviceCode1 = serviceCode.getServiceCode1();
    final ServiceCode2 serviceCode2 = serviceCode.getServiceCode2();
    final ServiceCode3 serviceCode3 = serviceCode.getServiceCode3();

    assertThat(serviceCode1, is(ServiceCode1.v_2));
    assertThat(serviceCode2, is(ServiceCode2.v_2));
    assertThat(serviceCode3, is(ServiceCode3.v_2));

    assertThat(
        serviceCode1.toString(),
        is("2 - Interchange: International interchange. Technology: Integrated circuit card."));
    assertThat(
        serviceCode1.getDescription(),
        is("Interchange: International interchange. Technology: Integrated circuit card."));
    assertThat(serviceCode1.getTechnology(), is("Integrated circuit card"));
    assertThat(serviceCode1.getInterchange(), is("International interchange"));
    assertThat(serviceCode1.getValue(), is(2));

    assertThat(serviceCode2.toString(), is("2 - Authorization Processing: By issuer."));
    assertThat(serviceCode2.getDescription(), is("Authorization Processing: By issuer."));
    assertThat(serviceCode2.getAuthorizationProcessing(), is("By issuer"));
    assertThat(serviceCode2.getValue(), is(2));

    assertThat(
        serviceCode3.toString(),
        is("2 - Allowed Services: Goods and services only. PIN Requirements: None."));
    assertThat(
        serviceCode3.getDescription(),
        is("Allowed Services: Goods and services only. PIN Requirements: None."));
    assertThat(serviceCode3.getAllowedServices(), is("Goods and services only"));
    assertThat(serviceCode3.getPinRequirements(), is("None"));
    assertThat(serviceCode3.getValue(), is(2));

    assertThat(serviceCode.toString(), is("222"));
    assertThat(serviceCode.getServiceCode(), is("222"));
    assertThat(serviceCode.exceedsMaximumLength(), is(false));
  }

  @Test
  public void serviceCode3() {
    final String rawServiceCode = "52525";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat(serviceCode.exceedsMaximumLength(), is(true));
    assertThat("Should have service code", serviceCode.hasServiceCode(), is(true));
    assertThat(serviceCode.getServiceCode1(), is(ServiceCode1.v_5));
    assertThat(serviceCode.getServiceCode2(), is(ServiceCode2.v_2));
    assertThat(serviceCode.getServiceCode3(), is(ServiceCode3.v_5));
  }

  @Test
  public void serviceCodeEquals() {
    EqualsVerifier.forClass(ServiceCode.class).withOnlyTheseFields("serviceCode").verify();
  }
}
