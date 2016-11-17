package com.mikeart.rxartistlist.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import com.mikeart.rxartistlist.presentation.view.activity.ArtistDetailsActivity;
import com.mikeart.rxartistlist.presentation.view.activity.ArtistListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

  @Inject
  public Navigator() {
    //empty
  }

  /**
   * Goes to the artist details screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToArtistDetails(Context context, int artistId) {
    if (context != null) {
      Intent intentToLaunch = ArtistDetailsActivity.getCallingIntent(context, artistId);
      context.startActivity(intentToLaunch);
    }
  }
}
