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
package us.fatehi.creditcardnumber;


import java.util.Arrays;

abstract class BaseRawData
  implements RawData
{

  private final char[] rawData;

  protected BaseRawData(final String rawData)
  {
    if (rawData != null)
    {
      this.rawData = rawData.toCharArray();
    }
    else
    {
      this.rawData = new char[0];
    }
  }

  @Override
  public void clearRawData()
  {
    if (rawData != null)
    {
      Arrays.fill(rawData, (char) 0);
    }
  }

  @Override
  public String getRawData()
  {
    if (hasRawData())
    {
      return new String(rawData);
    }
    else
    {
      return null;
    }
  }

  @Override
  public boolean hasRawData()
  {
    return rawData != null && rawData.length > 0 && rawData[0] != 0;
  }

}
