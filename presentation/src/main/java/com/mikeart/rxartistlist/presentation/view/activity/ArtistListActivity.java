package com.mikeart.rxartistlist.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.mikeart.rxartistlist.presentation.R;
import com.mikeart.rxartistlist.presentation.internal.di.HasComponent;
import com.mikeart.rxartistlist.presentation.internal.di.components.ArtistComponent;
import com.mikeart.rxartistlist.presentation.internal.di.components.DaggerArtistComponent;
import com.mikeart.rxartistlist.presentation.model.ArtistModel;
import com.mikeart.rxartistlist.presentation.view.fragment.ArtistListFragment;


/**
 * Activity that shows a list of Artists.
 */
public class ArtistListActivity extends BaseActivity implements HasComponent<ArtistComponent>,
        ArtistListFragment.ArtistListListener {

    public static Intent getCallingIntent(Context context) {
    return new Intent(context, ArtistListActivity.class);
  }

  private ArtistComponent artistComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);
    this.initializeInjector();

    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new ArtistListFragment());
    }
  }

  private void initializeInjector() {
    this.artistComponent = DaggerArtistComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public ArtistComponent getComponent() {
    return artistComponent;
  }

  @Override public void onArtistClicked(ArtistModel artistModel) {
    this.navigator.navigateToArtistDetails(this, artistModel.getArtistId());
  }
}
