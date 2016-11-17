package com.mikeart.rxartistlist.test.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;


import com.mikeart.rxartistlist.presentation.R;
import com.mikeart.rxartistlist.presentation.view.activity.ArtistDetailsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class ArtistDetailsActivityTest extends ActivityInstrumentationTestCase2<ArtistDetailsActivity> {

  private static final int FAKE_ARTIST_ID = 2915;

  private ArtistDetailsActivity artistDetailsActivity;

  public ArtistDetailsActivityTest() {
    super(ArtistDetailsActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    this.artistDetailsActivity = getActivity();
  }

  @Override protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsArtistDetailsFragment() {
    Fragment artistDetailsFragment =
        artistDetailsActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
    assertThat(artistDetailsFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.artistDetailsActivity.getTitle().toString().trim();

    assertThat(actualTitle, is("Details"));
  }

  public void testLoadArtistHappyCaseViews() {
    onView(ViewMatchers.withId(R.id.rl_retry)).check(matches(not(isDisplayed())));
    onView(ViewMatchers.withId(R.id.rl_progress)).check(matches(not(isDisplayed())));

    onView(ViewMatchers.withId(R.id.fab)).check(matches(isDisplayed()));

    onView(ViewMatchers.withId(R.id.tv_fullname)).check(matches(isDisplayed()));
    onView(ViewMatchers.withId(R.id.tv_description)).check(matches(isDisplayed()));
  }

  public void testLoadArtistHappyCaseData() {
    onView(ViewMatchers.withId(R.id.tv_fullname)).check(matches(withText("Ne-Yo")));
    onView(withId(R.id.tv_genres)).check(matches(withText("rnb, pop, rap")));
    onView(withId(R.id.tv_alb_n_tracks)).check(matches(withText("152 альбома • 256 песен")));
  }

  private Intent createTargetIntent() {
    Intent intentLaunchActivity =
        ArtistDetailsActivity.getCallingIntent(getInstrumentation().getTargetContext(), FAKE_ARTIST_ID);

    return intentLaunchActivity;
  }
}
