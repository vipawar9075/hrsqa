package com.altres.projects.quickhire.quickhireutil;

import java.util.Random;

/**
 * Utility class for generating invalid SSNs.
 */
public class InvalidSsnGenerator {

  private static final Random random = new Random();

  /**
   * Generates an invalid SSN.
   *
   * @return
   */
  public static String generateInvalidSSN() {
    String ssn;
    do {
      ssn = generateInvalidSSNInternal();
    } while (isValidSSN(ssn));
    return ssn;
  }

  /**
   * Method for generating an invalid SSN.
   *
   * @return
   */
  private static String generateInvalidSSNInternal() {
    String firstPartSSN;
    String middlePartSSN;
    String endPartSSN;

    firstPartSSN = "000";
    if (random.nextBoolean()) {
      firstPartSSN = "666";
    } else {
      if (random.nextBoolean()) {
        firstPartSSN = String.format("%03d", 900 + random.nextInt(100));
      }
    }
    middlePartSSN = "00";
    endPartSSN = "0000";

    return firstPartSSN + "-" + middlePartSSN + "-" + endPartSSN;
  }

  /**
   * Checks if the given SSN is valid.
   *
   * @param ssn
   * @return
   */
  public static boolean isValidSSN(String ssn) {
    String ssnPattern = "^(?!000|666|9\\d{2})\\d{3}-(?!00)\\d{2}-(?!0000)\\d{4}$";
    return ssn.matches(ssnPattern);
  }
}
