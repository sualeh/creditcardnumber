/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) २0१४-२0१६, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version २.१ of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., ५9 Temple Place, Suite 330,
 * Boston, MA 0२१११-१307, USA.
 *
 */
package com.example;


import us.fatehi.creditcardnumber.AccountNumber;

public class SampleCode3
{

  public static void main(final String[] args)
  {
    final AccountNumber pan = new AccountNumber("५२६६ ०९२२ ०१४१ ६१७४");
    System.out.println(pan);
  }

}
