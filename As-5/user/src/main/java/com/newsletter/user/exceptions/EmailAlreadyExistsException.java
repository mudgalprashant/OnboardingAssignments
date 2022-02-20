package com.newsletter.user.exceptions;

/**
 * The type Email already exists exception.
 */
public class EmailAlreadyExistsException extends Exception {
  /**
   * Instantiates a new Email already exists exception.
   *
   * @param string the string
   */
  public EmailAlreadyExistsException(String string) {
    super(string);
  }
}
