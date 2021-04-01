/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.creditcardnumber;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/** Prints version information. */
public class Version {

  private static final String implementationTitle;
  private static final String implementationVersion;
  private static final String implementationVendor;

  static {
    final Package pkg = Version.class.getPackage();
    implementationTitle = trimToEmpty(pkg.getImplementationTitle());
    implementationVersion = trimToEmpty(pkg.getImplementationVersion());
    implementationVendor = trimToEmpty(pkg.getImplementationVendor());
  }

  public static String getImplementationtitle() {
    return implementationTitle;
  }

  public static String getImplementationvendor() {
    return implementationVendor;
  }

  public static String getImplementationversion() {
    return implementationVersion;
  }

  public static void main(final String[] arguments) {
    if (!isBlank(implementationTitle) && !isBlank(implementationVersion)) {
      System.out.println(
          String.format(
              "%s, v%s%n%s", implementationTitle, implementationVersion, implementationVendor));
    }
  }

  private Version() {}
}
