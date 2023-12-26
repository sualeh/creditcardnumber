/*
 *
 * Credit Card Number
 * https://github.com/sualeh/credit_card_number
 * Copyright (c) 2014-2024, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

interface ServiceCodeType {

  /**
   * Gets the description.
   *
   * @return Description.
   */
  String getDescription();

  /**
   * Gets the service code value.
   *
   * @return Service code value.
   */
  int getValue();
}
