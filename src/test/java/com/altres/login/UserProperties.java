package com.altres.login;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

/**
 * UserProperties handles the retrieval of user properties such as username and password.
 */
public class UserProperties {

  private final String username;
  private final String password;
  private final int userIndex;

  public UserProperties(String userType) throws IOException {
    String hostname = InetAddress.getLocalHost().getHostName();
    String userFile = "src/main/resources/user/" + hostname + ".user";
    Properties properties = new Properties();
    try (FileInputStream userPropertiesStream = new FileInputStream(userFile)) {
      properties.load(userPropertiesStream);
    } catch (IOException e) {
      System.err.println("Error loading user properties: " + e.getMessage());
      throw e;
    }
    String userDetails = properties.getProperty(userType);
    if (userDetails != null) {
      String[] details = userDetails.split(",");
      this.username = details[0].trim();
      this.password = details[1].trim();
      this.userIndex = !userType.equals("SUPER_USER") ? Integer.parseInt(details[2].trim()) : 0;
    } else {
      throw new IllegalArgumentException("Invalid user type or missing property for: " + userType);
    }
  }

  /**
   * Gets the username related to current user.
   *
   * @return
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets the password related to current user.
   *
   * @return
   */
  public String getPassword() {
    return password;
  }

  /**
   * Gets the user index.
   *
   * @return
   */
  public int getUserIndex() {
    return userIndex;
  }
}
