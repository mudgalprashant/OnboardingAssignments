package exceptions;

/**
 * The type Cyclic dependency exception.
 */
public class CyclicDependencyException extends Exception {

  /**
   * Instantiates a new Cyclic dependency exception.
   *
   * @param string the string
   */
  public CyclicDependencyException(String string) {
    super(string);
  }
}
