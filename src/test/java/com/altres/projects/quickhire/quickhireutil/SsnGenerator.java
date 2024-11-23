package com.altres.projects.quickhire.quickhireutil;

import java.util.Random;

/**
 * Utility class for generating valid SSN.
 */
public class SsnGenerator {

  public static final Random random = new Random();

  /**
   * Generates a valid SSN.
   *
   * @return
   */
  public static String generateValidSSN() {
    String ssn;
    do {
      ssn = generateSSN();
    } while (!isValidSSN(ssn));
    return ssn;
  }

  /**
   * Generates an SSN.
   *
   * @return
   */
  private static String generateSSN() {
    String firstPartSSN;
    String middlePartSSN;
    String endPartSSN;
    do {
      firstPartSSN = String.format("%03d", random.nextInt(1000));
    } while (firstPartSSN.equals("000") || firstPartSSN.equals("666") || (
        Integer.parseInt(firstPartSSN) >= 900 && Integer.parseInt(firstPartSSN) <= 999));
    middlePartSSN = String.format("%02d", random.nextInt(100));
    while (middlePartSSN.equals("00")) {
      middlePartSSN = String.format("%02d", random.nextInt(100));
    }
    endPartSSN = String.format("%04d", random.nextInt(10000));
    while (endPartSSN.equals("0000")) {
      endPartSSN = String.format("%04d", random.nextInt(10000));
    }
    return firstPartSSN + "-" + middlePartSSN + "-" + endPartSSN;
  }

  /**
   * Checks if the given SSN is valid.
   *
   * @param ssn
   * @return
   */
  public static boolean isValidSSN(String ssn) {
    String firstPartSSN = "^(?!000|666|9\\d{2})\\d{3}";
    String middlePartSSN = "(?!00)\\d{2}";
    String endPartSSN = "(?!0000)\\d{4}$";
    String ssnPattern = firstPartSSN + "-" + middlePartSSN + "-" + endPartSSN;
    return ssn.matches(ssnPattern);
  }
}

