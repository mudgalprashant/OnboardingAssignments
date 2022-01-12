package services;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.PriorityQueue;

import models.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The type Load user details.
 */
public class LoadUserDetails {

  // logger object
  private static final Log log = LogFactory.getLog(SaveUserDetails.class);

  /**
   * Instantiates a new Load user details.
   *
   * @param userDataFile the user data file
   * @param userList     the user list
   */
  public LoadUserDetails(File userDataFile, PriorityQueue<User> userList) {
    try {

      // Create file if it does not exist
      if (!userDataFile.exists()) {
        boolean fileCreated = userDataFile.createNewFile();
      }

      // Read from file to userList
      if (userDataFile.canRead()) {

        FileInputStream fileInputStream = new FileInputStream(userDataFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User loadedUserList = (User) objectInputStream.readObject();

        //userList.addAll(loadedUserList);
        userList.add(loadedUserList);

        fileInputStream.close();
        objectInputStream.close();
      }
    } catch (StreamCorruptedException | ClassNotFoundException | FileNotFoundException e) {
      log.error(e);
    } catch (EOFException ignored) {
      System.out.print("");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
