package application;

import services.ThreadUtility;

/**
 * Root class of the project
 */
public class Application {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    ThreadUtility threadUtility = new ThreadUtility();
    threadUtility.runThreads();
  }
}

