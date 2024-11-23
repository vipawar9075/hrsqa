package com.altres.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Implementation of ErrorMessagesProvider for reading error messages from quickhire-elements.properties files.
 */
public class ConfigPropertiesProviderImpl implements PropertyValueProvider {

  private String configFilePath;

  public ConfigPropertiesProviderImpl(String configFilePath) {
    this.configFilePath = configFilePath;
  }

  /**
   * Gets the error message for a given key.
   *
   * @param key
   * @return
   * @throws IOException
   */
  @Override
  public String getPropertyValueByKey(String key) throws IOException {
    Properties properties = new Properties();
    try (FileInputStream fis = new FileInputStream(configFilePath)) {
      properties.load(fis);
    }
    return properties.getProperty(key);
  }

  /**
   * Gets the error messages for a list of keys.
   *
   * @param keys
   * @return
   * @throws IOException
   */
  @Override
  public Map<String, String> getPropertyValuesByKeys(List<String> keys) throws IOException {
    Properties properties = new Properties();
    try (FileInputStream fis = new FileInputStream(configFilePath)) {
      properties.load(fis);
    }
    Map<String, String> errorMessages = new HashMap<>();
    for (String key : keys) {
      errorMessages.put(key, properties.getProperty(key));
    }
    return errorMessages;
  }

}