package com.mikeart.rxartistlist.presentation.exception;

import android.content.Context;
import com.mikeart.rxartistlist.data.exception.NetworkConnectionException;
import com.mikeart.rxartistlist.data.exception.ArtistNotFoundException;
import com.mikeart.rxartistlist.presentation.R;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

  private ErrorMessageFactory() {
    //empty
  }

  /**
   * Creates a String representing an error message.
   *
   * @param context Context needed to retrieve string resources.
   * @param exception An exception used as a condition to retrieve the correct error message.
   * @return {@link String} an error message.
   */
  public static String create(Context context, Exception exception) {
    String message = context.getString(R.string.exception_message_generic);

    if (exception instanceof NetworkConnectionException) {
      message = context.getString(R.string.exception_message_no_connection);
    } else if (exception instanceof ArtistNotFoundException) {
      message = context.getString(R.string.exception_message_artist_not_found);
    }

    return message;
  }
}
