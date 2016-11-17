package com.mikeart.rxartistlist.data.exception;

/**
 * Exception throw by the application when a Artist search can't return a valid result.
 */
public class ArtistNotFoundException extends Exception {

  public ArtistNotFoundException() {
    super();
  }

  public ArtistNotFoundException(final String message) {
    super(message);
  }

  public ArtistNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ArtistNotFoundException(final Throwable cause) {
    super(cause);
  }
}
