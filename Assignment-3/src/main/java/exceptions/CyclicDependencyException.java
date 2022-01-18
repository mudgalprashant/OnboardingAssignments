package exceptions;

public class CyclicDependencyException extends Exception {

  public CyclicDependencyException(String string) {
    super(string);
  }
}
