package com.mikeart.rxartistlist.test.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.mikeart.rxartistlist.presentation.view.activity.ArtistListActivity;
import com.mikeart.rxartistlist.presentation.R;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArtistListActivityTest extends ActivityInstrumentationTestCase2<ArtistListActivity> {

  private ArtistListActivity artistListActivity;

  public ArtistListActivityTest() {
    super(ArtistListActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    artistListActivity = getActivity();
  }

  @Override protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsArtistListFragment() {
    Fragment artistListFragment =
        artistListActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
    assertThat(artistListFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.artistListActivity.getTitle().toString().trim();

    assertThat(actualTitle, is("Исполнители"));
  }

  private Intent createTargetIntent() {
    Intent intentLaunchActivity =
        ArtistListActivity.getCallingIntent(getInstrumentation().getTargetContext());

    return intentLaunchActivity;
  }
}
