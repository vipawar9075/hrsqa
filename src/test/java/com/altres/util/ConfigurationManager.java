package com.altres.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

import com.altres.exception.ConfigurationLoadException;
import com.altres.exception.ConfigurationNotLoadedException;

/**
 * ConfigurationManager loads and provides access to configuration properties.
 */
public class ConfigurationManager {

  private static final Properties prop = new Properties();

  /**
   * Loads the properties file based on the hostname.
   *
   * @throws ConfigurationLoadException
   */
  public static void loadProperties() throws ConfigurationLoadException {
    FileInputStream fileInputStream = null;
    try {
      InetAddress inetAddress = InetAddress.getLocalHost();
      String hostname = inetAddress.getHostName();
      fileInputStream = new FileInputStream("src/main/resources/properties/" + hostname + ".properties");
      prop.load(fileInputStream);
    } catch (IOException e) {
      throw new ConfigurationLoadException("Failed to load properties file.", e);
    } finally {
      if (fileInputStream != null) {
        try {
          fileInputStream.close();
        } catch (IOException e) {
          System.err.println("Failed to close FileInputStream: " + e.getMessage());
        }
      }
    }
  }

  /**
   * Retrieves the value of a property by key.
   *
   * @param key
   * @return
   * @throws ConfigurationNotLoadedException
   */
  public static String getProperty(String key) throws ConfigurationNotLoadedException {
    String value = prop.getProperty(key);
    if (value != null && !value.trim().isEmpty()) {
      return value;
    }
    throw new ConfigurationNotLoadedException("Properties file not loaded. Call loadProperties() first.");
  }
}




