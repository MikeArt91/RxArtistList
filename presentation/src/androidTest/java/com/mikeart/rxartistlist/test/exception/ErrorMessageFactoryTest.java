package com.mikeart.rxartistlist.test.exception;

import android.test.AndroidTestCase;
import com.mikeart.rxartistlist.data.exception.NetworkConnectionException;
import com.mikeart.rxartistlist.data.exception.ArtistNotFoundException;
import com.mikeart.rxartistlist.presentation.R;
import com.mikeart.rxartistlist.presentation.exception.ErrorMessageFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ErrorMessageFactoryTest extends AndroidTestCase {

  @Override protected void setUp() throws Exception {
    super.setUp();
  }

  public void testNetworkConnectionErrorMessage() {
    String expectedMessage = getContext().getString(R.string.exception_message_no_connection);
    String actualMessage = ErrorMessageFactory.create(getContext(),
        new NetworkConnectionException());

    assertThat(actualMessage, is(equalTo(expectedMessage)));
  }

  public void testArtistNotFoundErrorMessage() {
    String expectedMessage = getContext().getString(R.string.exception_message_artist_not_found);
    String actualMessage = ErrorMessageFactory.create(getContext(), new ArtistNotFoundException());

    assertThat(actualMessage, is(equalTo(expectedMessage)));
  }
}
