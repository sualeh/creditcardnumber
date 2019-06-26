/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2016, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */
package us.fatehi.test.creditcardnumber;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import us.fatehi.creditcardnumber.ServiceCode;
import us.fatehi.creditcardnumber.ServiceCode1;
import us.fatehi.creditcardnumber.ServiceCode2;
import us.fatehi.creditcardnumber.ServiceCode3;

public class ServiceCodeTest
{

  @Test
  public void serviceCode_1()
  {
    final String rawServiceCode = null;
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should not have service code",
               !serviceCode.hasServiceCode(),
               is(true));
  }

  @Test
  public void serviceCode_2()
  {
    final String rawServiceCode = "\t\t";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should not have service code",
               !serviceCode.hasServiceCode(),
               is(true));
  }

  @Test
  public void serviceCode_3()
  {
    final String rawServiceCode = "AQW";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should not have service code",
               !serviceCode.hasServiceCode(),
               is(true));
  }

  @Test
  public void serviceCode_4()
  {
    final String rawServiceCode = "10";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat(serviceCode.getServiceCode1(), is(ServiceCode1.v_1));
    assertThat(serviceCode.getServiceCode2(), is(ServiceCode2.v_0));
    assertThat("Should not have service code",
               !serviceCode.hasServiceCode(),
               is(true));
  }

  @Test
  public void serviceCode1()
  {
    final String rawServiceCode = "101";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should have service code",
               serviceCode.hasServiceCode(),
               is(true));
    assertThat(serviceCode.getServiceCode1(), is(ServiceCode1.v_1));
    assertThat(serviceCode.getServiceCode2(), is(ServiceCode2.v_0));
    assertThat(serviceCode.getServiceCode3(), is(ServiceCode3.v_1));
  }

  @Test
  public void serviceCode2()
  {
    final String rawServiceCode = "222 ";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should have service code",
               serviceCode.hasServiceCode(),
               is(true));
    assertThat(serviceCode.getServiceCode1(), is(ServiceCode1.v_2));
    assertThat(serviceCode.getServiceCode2(), is(ServiceCode2.v_2));
    assertThat(serviceCode.getServiceCode3(), is(ServiceCode3.v_2));
  }

  @Test
  public void serviceCode3()
  {
    final String rawServiceCode = "52525";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertThat(serviceCode.getRawData(), is(rawServiceCode));
    assertThat("Should have service code",
               serviceCode.hasServiceCode(),
               is(true));
    assertThat(serviceCode.getServiceCode1(), is(ServiceCode1.v_5));
    assertThat(serviceCode.getServiceCode2(), is(ServiceCode2.v_2));
    assertThat(serviceCode.getServiceCode3(), is(ServiceCode3.v_5));
  }

}
