package com.mikeart.rxartistlist.presentation.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mikeart.rxartistlist.presentation.R;
import com.mikeart.rxartistlist.presentation.internal.di.components.ArtistComponent;
import com.mikeart.rxartistlist.presentation.model.ArtistModel;
import com.mikeart.rxartistlist.presentation.presenter.ArtistDetailsPresenter;
import com.mikeart.rxartistlist.presentation.view.ArtistDetailsView;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

/**
 * Fragment that shows details of a certain artist.
 */
public class ArtistDetailsFragment extends BaseFragment implements ArtistDetailsView {

  @Inject
  ArtistDetailsPresenter artistDetailsPresenter;

  @Bind(R.id.iv_cover_big) ImageView iv_cover_big;
  @Bind(R.id.tv_fullname) TextView tv_fullname;
  @Bind(R.id.tv_genres) TextView tv_genres;
  @Bind(R.id.tv_alb_n_tracks) TextView tv_alb_n_tracks;
  @Bind(R.id.tv_description) TextView tv_description;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;
  @Bind(R.id.detail_toolbar) android.support.v7.widget.Toolbar toolbar;
  @Bind(R.id.fab) FloatingActionButton fab_button;

  private ActionBar actionBar;
  private String link, cover;

  public ArtistDetailsFragment() {
    setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(ArtistComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_artist_details, container, false);
    ButterKnife.bind(this, fragmentView);

    AppCompatActivity activity = (AppCompatActivity) getActivity();
    activity.setSupportActionBar(toolbar);

    actionBar = activity.getSupportActionBar();

    setHasOptionsMenu(true);

    if(actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(null);
    }

    Picasso.with(context()).load(cover).into(iv_cover_big);

    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.artistDetailsPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadArtistDetails();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.artistDetailsPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.artistDetailsPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.artistDetailsPresenter.destroy();
  }

  @Override public void renderArtist(ArtistModel artist) {
    if (artist != null) {

      cover = artist.getCoverBig();
      Picasso.with(context()).load(cover).into(iv_cover_big);

      this.tv_fullname.setText(artist.getName());
      this.tv_genres.setText(artist.getGenres());

      // preparing albums and tracks for the view
      int albums=artist.getAlbums();
      int tracks=artist.getTracks();

      final String albumsStr = this.getResources().
              getQuantityString(R.plurals.albums, albums, albums);
      final String tracksStr = this.getResources()
              .getQuantityString(R.plurals.tracks, tracks, tracks);

      this.tv_alb_n_tracks.setText(albumsStr+" • "+tracksStr);

      // capitalize first char of description
      StringBuilder sb = new StringBuilder(artist.getDescription());
      sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

      this.tv_description.setText(sb.toString());

      link = artist.getLink();
    }
  }

  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
    this.getActivity().setProgressBarIndeterminateVisibility(true);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
    this.getActivity().setProgressBarIndeterminateVisibility(false);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return getActivity().getApplicationContext();
  }

  /**
   * Loads artist's details.
   */
  private void loadArtistDetails() {
    if (this.artistDetailsPresenter != null) {
      this.artistDetailsPresenter.initialize();
    }
  }

  private void openWebPage(String url) {
    Uri webpage = Uri.parse(url);
    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
      startActivity(intent);
    }
  }

  @OnClick(R.id.bt_retry)
  void onButtonRetryClick() {
    ArtistDetailsFragment.this.loadArtistDetails();
  }

  @OnClick(R.id.fab)
  void onFabClick(){
    if( link != null && !link.isEmpty())
    {
      openWebPage(link);
    }
    else{
      Toast.makeText(context(), R.string.utl_not_found, Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()){
      case android.R.id.home:
        getActivity().onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

}
