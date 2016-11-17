package com.mikeart.rxartistlist.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import com.mikeart.rxartistlist.presentation.R;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.mikeart.rxartistlist.presentation.internal.di.components.ArtistComponent;
import com.mikeart.rxartistlist.presentation.model.ArtistModel;
import com.mikeart.rxartistlist.presentation.presenter.ArtistListPresenter;
import com.mikeart.rxartistlist.presentation.view.ArtistListView;
import com.mikeart.rxartistlist.presentation.view.adapter.ArtistsAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Fragment that shows a list of Artists.
 */
public class ArtistListFragment extends BaseFragment implements ArtistListView {

  /**
   * Interface for listening artist list events.
   */
  public interface ArtistListListener {
    void onArtistClicked(final ArtistModel artistModel);
  }

  @Inject
  ArtistListPresenter artistListPresenter;
  @Inject
  ArtistsAdapter artistsAdapter;

  @Bind(R.id.rv_artists) RecyclerView rv_artists;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;
  @Bind(R.id.toolbar_list) android.support.v7.widget.Toolbar toolbar;

  private ArtistListListener artistListListener;

  public ArtistListFragment() {
    setRetainInstance(true);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof ArtistListListener) {
      this.artistListListener = (ArtistListListener) activity;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(ArtistComponent.class).inject(this);

  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_artist_list, container, false);
    ButterKnife.bind(this, fragmentView);
    setupRecyclerView();

    AppCompatActivity activity = (AppCompatActivity) getActivity();
    activity.setSupportActionBar(toolbar);

    ActionBar actionBar = activity.getSupportActionBar();

    if(actionBar != null) {
        actionBar.setTitle(R.string.activity_title_artist_list);
    }


    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.artistListPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadArtistList();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.artistListPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.artistListPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    rv_artists.setAdapter(null);
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.artistListPresenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.artistListListener = null;
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

  @Override public void renderArtistList(List<ArtistModel> artistModelList) {
    if (artistModelList != null) {
      this.artistsAdapter.setArtistsCollection(artistModelList);
    }
  }

  @Override public void viewArtist(ArtistModel artistModel) {
    if (this.artistListListener != null) {
      this.artistListListener.onArtistClicked(artistModel);
    }
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    this.artistsAdapter.setOnItemClickListener(onItemClickListener);

    if(getActivity().getResources().getConfiguration()
            .orientation == Configuration.ORIENTATION_PORTRAIT) {
      this.rv_artists.setLayoutManager(new GridLayoutManager(context(),1)); // 1 column
    } else {
      this.rv_artists.setLayoutManager(new GridLayoutManager(context(),2)); // 2 columns
    }

     this.rv_artists.setAdapter(artistsAdapter);
  }

  /**
   * Loads all artists.
   */
  private void loadArtistList() {
    this.artistListPresenter.initialize();
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    ArtistListFragment.this.loadArtistList();
  }

  private ArtistsAdapter.OnItemClickListener onItemClickListener =
      new ArtistsAdapter.OnItemClickListener() {
        @Override public void onArtistItemClicked(ArtistModel artistModel) {
          if (ArtistListFragment.this.artistListPresenter != null && artistModel != null) {
            ArtistListFragment.this.artistListPresenter.onArtistClicked(artistModel);
          }
        }
      };
}
