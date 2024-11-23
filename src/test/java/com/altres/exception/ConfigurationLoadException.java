package com.altres.exception;

/**
 * Exception thrown when there is an error loading the properties file.
 */
public class ConfigurationLoadException extends Exception {

  public ConfigurationLoadException(String message, Throwable cause) {
    super(message, cause);
  }
}



