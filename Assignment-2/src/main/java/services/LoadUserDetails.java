package services;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.PriorityQueue;

import models.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The type Load user details.
 */
public class LoadUserDetails {

  // logger object
  private static final Log log = LogFactory.getLog(LoadUserDetails.class);

  /**
   * Instantiates a new Load user details.
   *
   * @param userDataFile the user data file
   * @param userList     the user list
   */
  public LoadUserDetails(File userDataFile, PriorityQueue<User> userList) {
    try {

      // Create file if it does not exist
      userDataFile.createNewFile();
      // Read from file to userList
      if (userDataFile.canRead()) {

        FileInputStream fileInputStream = new FileInputStream(userDataFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
          Object loadedObject = objectInputStream.readObject();
          if (loadedObject == null) {
            break;
          }
          User loadedUser = (User) loadedObject;

          userList.add(loadedUser);
        }

        fileInputStream.close();
        objectInputStream.close();
      }
    } catch (EOFException ignored) {
    } catch (ClassNotFoundException | IOException | SecurityException exception) {
      log.error(exception);
    }
  }
}
