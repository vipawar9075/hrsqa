package com.altres.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Utility class for extracting Date in required date format
 */
public class Date {

  /**
   * This method for extracting Past Date in MM/dd/yyyy format.
   *
   * @param dateString
   * @param days
   * @return
   */
  public static String getPreviousDate(String dateString, int days) {
    LocalDate currentDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    LocalDate previousDate = currentDate.minusDays(days);
    return previousDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
  }

  /**
   * This method for extracting Current Date in MM/dd/yyyy format.
   *
   * @return
   */
  public static String getCurrentDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    return LocalDate.now().format(formatter);
  }

  /**
   * This method for extracting Future Date in MM/dd/yyyy format.
   *
   * @param dateString
   * @param days
   * @return
   */
  public static String getFutureDate(String dateString, int days) {
    LocalDate currentDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    LocalDate previousDate = currentDate.plusDays(days);
    return previousDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
  }

}


