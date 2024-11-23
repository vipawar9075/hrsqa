package com.altres.projects.formbuilder.fbutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.altres.util.Constants;

/**
 * Utility class for loading properties and interacting with web elements.
 */
public class FBPropertyLoader {

  /**
   * Loads properties from the specified file.
   *
   * @return
   * @throws IOException
   */
  public static Properties loadProperties() throws IOException {
    Properties properties = new Properties();
    String path = Constants.FB_ELEMENT_PROPERTIES_PATH;
    try (FileInputStream inputStream = new FileInputStream(path)) {
      properties.load(inputStream);
    }
    return properties;
  }
}
