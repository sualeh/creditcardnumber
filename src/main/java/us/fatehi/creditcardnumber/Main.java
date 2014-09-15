/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014, Sualeh Fatehi.
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


import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import us.fatehi.creditcardnumber.bankcard.AccountNumber;
import us.fatehi.creditcardnumber.bankcard.BankCard;
import us.fatehi.creditcardnumber.bankcard.PrimaryAccountNumber;

/**
 * Credit Card Number console application.
 */
public class Main
{

  public static void main(final String[] args)
    throws Exception
  {
    Version.main(new String[0]);

    final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    while (true)
    {
      System.out.println("1. Get bank card information from card number");
      System.out.println("0. Quit");
      System.out.print("Choice: ");
      final int choice = toInt(in.readLine(), 0);
      switch (choice)
      {
        case 0:
          System.exit(0);
          break;
        case 1:
          getBankCardInformation(in);
          break;
        default:
          break;
      }
    }

  }

  private static void getBankCardInformation(final BufferedReader in)
    throws IOException
  {
    while (true)
    {
      System.out.println("(Type 0 to return to main menu)");
      System.out.print("Bank Card Number: ");
      final String line = in.readLine();
      final int choice = toInt(line, -1);
      if (choice == 0)
      {
        return;
      }
      if (!isBlank(line))
      {
        final PrimaryAccountNumber pan = new AccountNumber(line);
        final BankCard card = new BankCard(pan);
        System.out.println(card);
      }
    }
  }

}
