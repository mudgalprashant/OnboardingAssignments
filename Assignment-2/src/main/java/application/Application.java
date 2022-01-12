package application;

import controller.ApplicationUtility;

/**
 * Application.
 */
public class Application {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    final ApplicationUtility applicationUtility = new ApplicationUtility();

    applicationUtility.performTasks();

  }
}