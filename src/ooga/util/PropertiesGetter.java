package ooga.util;

import java.util.ResourceBundle;

/**
 * @author jaidharosenblatt Util class to get property file values without having to creat a
 * resource bundle in each class.
 */
public class PropertiesGetter {

  private static final String PATH_TO_BUNDLE = "resources/";
  private static final String UI_PATH = PATH_TO_BUNDLE + "Default";
  private static final String ERRORS_PATH = PATH_TO_BUNDLE + "Errors";


  /**
   * Get a value from Default.properties
   * @param key the key to get the value for
   * @return the value
   */
  public static String getPromptFromKey(String key) {
    ResourceBundle bundle = ResourceBundle.getBundle(UI_PATH);
    return bundle.getString(key);
  }

  /**
   * Get a value from Errors.properties
   * @param key the key to get the value for
   * @return the value
   */
  public static String getErrorMessageFromKey(String key) {
    ResourceBundle bundle = ResourceBundle.getBundle(ERRORS_PATH);
    return bundle.getString(key);
  }

}
