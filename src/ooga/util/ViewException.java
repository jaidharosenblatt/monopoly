package ooga.util;

/**
 * @author jaidharosenblatt Custom exception for errors in the frontend
 */
public class ViewException extends RuntimeException {

  /**
   * Create an exception based on an issue in our code.
   */
  public ViewException(String message, Object... values) {
    super(String.format(message, values));
  }

  /**
   * Create an exception based on a caught exception with a different message.
   */
  public ViewException(Throwable cause, String message, Object... values) {
    super(String.format(message, values), cause);
  }

  /**
   * Create an exception based on a caught exception, with no additional message.
   */
  public ViewException(Throwable cause) {
    super(cause);
  }
}
