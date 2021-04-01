/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import java.util.regex.Pattern;

public interface Utility {

  Pattern non_digit = Pattern.compile("[^0-9]");
}
