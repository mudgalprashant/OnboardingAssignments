package application;

import services.MenuUtility;

/**
 * The type Application.
 */
public class Application {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    final MenuUtility menuUtility = new MenuUtility();
    menuUtility.performTasks();
  }
}