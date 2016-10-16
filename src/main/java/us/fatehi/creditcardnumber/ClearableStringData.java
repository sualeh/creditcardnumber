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

public final class ClearableStringData
{

  private final char[] data;

  public ClearableStringData(final String data)
  {
    if (data != null)
    {
      this.data = data.toCharArray();
    }
    else
    {
      this.data = new char[0];
    }
  }

  public char charAt(final int i)
  {
    return data[i];
  }

  public void clearData()
  {
    Arrays.fill(data, (char) 0);
  }

  public void clearData(final int fromIndex)
  {
    Arrays.fill(data, Integer.max(0, fromIndex), data.length, (char) 0);
  }

  public void clearData(final int fromIndex, final int toIndex)
  {
    Arrays.fill(data,
                Integer.max(0, fromIndex),
                Integer.min(data.length, toIndex),
                (char) 0);
  }

  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (getClass() != obj.getClass())
    {
      return false;
    }
    final ClearableStringData other = (ClearableStringData) obj;
    if (!Arrays.equals(data, other.data))
    {
      return false;
    }
    return true;
  }

  public String getData()
  {
    if (hasData())
    {
      return new String(data);
    }
    else
    {
      return null;
    }
  }

  public boolean hasData()
  {
    return data.length > 0 && data[0] != 0 && data[data.length - 1] != 0;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(data);
    return result;
  }

  public int length()
  {
    return data.length;
  }

}
