package com.altres.reporting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * ExtentReportsManager manages the ExtentReports instance used for reporting test results.
 */
public class ExtentReportsManager {

  private static final String REPORT_FILE_NAME_PATTERN = "Summary_%s.html";
  private static ExtentReports extentReport;

  /**
   * Creates a test in the ExtentReports.
   *
   * @param reportLocation
   * @return
   */
  public static synchronized ExtentReports getInstance(String reportLocation) {
    if (extentReport == null) {
      extentReport = new ExtentReports();
      DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
      String formattedDate = dateFormat.format(new Date());
      String reportPath = reportLocation + String.format(REPORT_FILE_NAME_PATTERN, formattedDate);
      ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
      sparkReporter.config().setTheme(Theme.STANDARD);
      sparkReporter.config().setDocumentTitle("Selenium automation report");
      sparkReporter.config().setReportName("Automation Script Execution Report");
      extentReport.attachReporter(sparkReporter);
      extentReport.setSystemInfo("HostName", "localhost");
      extentReport.setSystemInfo("Tester Name", "test");
    }
    return extentReport;
  }
}



