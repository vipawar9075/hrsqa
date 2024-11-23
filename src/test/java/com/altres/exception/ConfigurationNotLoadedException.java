package com.altres.exception;

/**
 * Runtime exception thrown when the properties file has not been loaded or a property is not set.
 */
public class ConfigurationNotLoadedException extends Exception {

  public ConfigurationNotLoadedException(String message) {
    super(message);
  }
}

