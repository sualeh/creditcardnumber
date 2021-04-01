/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2021, Sualeh Fatehi.
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
