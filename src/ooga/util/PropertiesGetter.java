package ooga.util;

import java.util.ResourceBundle;

public class PropertiesGetter {

  private static final String PATH_TO_BUNDLE = "resources/";
  private static final String UI_PATH = PATH_TO_BUNDLE + "Default";
  private static final String ERRORS_PATH = PATH_TO_BUNDLE + "Errors";


  public static String getPromptFromKey(String key) {
    ResourceBundle bundle = ResourceBundle.getBundle(UI_PATH);
    return bundle.getString(key);
  }

  public static String getErrorMessageFromKey(String key) {
    ResourceBundle bundle = ResourceBundle.getBundle(ERRORS_PATH);
    return bundle.getString(key);
  }

}
