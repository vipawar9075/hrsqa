package com.altres.exception;

import org.openqa.selenium.ElementNotInteractableException;

/**
 * Exception thrown when element is not visible.
 */
public class ElementNotVisibleException extends ElementNotInteractableException {

  public ElementNotVisibleException(String message) {
    super(message);
  }

  public ElementNotVisibleException(String message, Throwable cause) {
    super(message, cause);
  }
}