package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.PriorityQueue;

import models.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * The type Save user details.
 */
public class SaveUserDetails {

  private static final Log log = LogFactory.getLog(SaveUserDetails.class);

  /**
   * Instantiates a new Save user details.
   *
   * @param userDataFile the user data file
   * @param userList     the user list
   */
  public SaveUserDetails(File userDataFile, PriorityQueue<User> userList) {

    try {

      // Create file if it does not exist
      if (!userDataFile.exists()) {
        boolean fileCreated = userDataFile.createNewFile();
      }

      // Write from userList to file
      if (userDataFile.canWrite()) {
        FileOutputStream fileOutputStream = new FileOutputStream(userDataFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (User user : userList) {
          objectOutputStream.writeObject(user);
        }
        fileOutputStream.close();
        objectOutputStream.close();
      }
    } catch (IOException e) {
      log.error(e);
    }
  }
}
