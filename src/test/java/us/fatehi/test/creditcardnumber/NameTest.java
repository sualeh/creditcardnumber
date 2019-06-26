/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
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
import static org.hamcrest.Matchers.sameInstance;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.Name;

public class NameTest
{

  @Test
  public void name_1()
  {
    final String rawName = null;
    final Name name = new Name(rawName);
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.hasName(), is(false));
  }

  @Test
  public void name_2()
  {
    final String rawName = "\t\t";
    final Name name = new Name(rawName);
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.hasName(), is(false));
  }

  @Test
  public void name_3()
  {
    final String rawName = " /                        ";
    final Name name = new Name(rawName);
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.hasName(), is(false));
  }

  @Test
  public void name1()
  {
    final String rawName = "SUALEH";
    final Name name = new Name(rawName);
    assertThat(name.hasName(), is(true));
    assertThat(name.getRawData(), is("SUALEH"));
    assertThat(name.getFirstName(), is(""));
    assertThat(name.getLastName(), is("Sualeh"));
    assertThat(name.getFullName(), is("Sualeh"));
  }

  @Test
  public void name2()
  {
    final String rawName = "FATEHI/SUALEH";
    final Name name = new Name(rawName);
    assertThat(name.hasName(), is(true));
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.getFirstName(), is("Sualeh"));
    assertThat(name.getLastName(), is("Fatehi"));
    assertThat(name.getFullName(), is("Sualeh Fatehi"));
  }

  @Test
  public void name3()
  {
    final String rawName = "FATEHI/SUALEH             ";
    final Name name = new Name(rawName);
    assertThat(name.hasName(), is(true));
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.getFirstName(), is("Sualeh"));
    assertThat(name.getLastName(), is("Fatehi"));
    assertThat(name.getFullName(), is("Sualeh Fatehi"));
  }

  @Test
  public void name4()
  {
    final String rawName = "MICHAELS OPEN VALUE CARD  ";
    final Name name = new Name(rawName);
    assertThat(name.hasName(), is(true));
    assertThat(name.getRawData(), is(rawName));
    assertThat(name.getFirstName(), is(""));
    assertThat(name.getLastName(), is("Michaels Open Value Card"));
    assertThat(name.getFullName(), is("Michaels Open Value Card"));
  }

}
