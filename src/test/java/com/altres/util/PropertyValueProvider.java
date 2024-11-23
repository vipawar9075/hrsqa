package com.altres.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface for fetching values from  properties file.
 */
public interface PropertyValueProvider {

  /**
   * Gets the error message for a given key.
   *
   * @param key
   * @return
   * @throws IOException
   */
  String getPropertyValueByKey(String key) throws IOException;

  /**
   * Gets the error messages for a list of keys.
   *
   * @param keys
   * @return
   * @throws IOException
   */
  Map<String, String> getPropertyValuesByKeys(List<String> keys) throws IOException;

}


