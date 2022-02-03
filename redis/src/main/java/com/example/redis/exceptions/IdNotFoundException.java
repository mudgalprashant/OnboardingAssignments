package com.example.redis.exceptions;

/**
 * The type Invalid entry exception.
 */
public class IdNotFoundException extends Exception {
  /**
   * Instantiates a new Invalid entry exception.
   *
   * @param string the string for Exception Description
   */
  public IdNotFoundException(String string){
        super(string);
    }
}
