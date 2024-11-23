package com.altres.projects.careers.careersutil;

/**
 * Provides a thread-local storage mechanism for managing the company name within the current thread
 */
public class CompanyContext {

  private static final ThreadLocal<String> companyName = new ThreadLocal<>();

  /**
   * Retrieves the company name associated with the current thread.
   *
   * @return
   */
  public static String getCompanyName() {
    return companyName.get();
  }

  /**
   * Sets the company name for the current thread.
   *
   * @param companyName
   */
  public static void setCompanyName(String companyName) {
    CompanyContext.companyName.set(companyName);
  }

}
