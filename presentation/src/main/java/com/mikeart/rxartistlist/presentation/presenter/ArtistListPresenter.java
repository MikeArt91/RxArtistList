package com.mikeart.rxartistlist.presentation.presenter;

import android.support.annotation.NonNull;
import com.mikeart.rxartistlist.domain.Artist;
import com.mikeart.rxartistlist.domain.exception.DefaultErrorBundle;
import com.mikeart.rxartistlist.domain.exception.ErrorBundle;
import com.mikeart.rxartistlist.domain.interactor.DefaultSubscriber;
import com.mikeart.rxartistlist.domain.interactor.UseCase;
import com.mikeart.rxartistlist.presentation.exception.ErrorMessageFactory;
import com.mikeart.rxartistlist.presentation.internal.di.PerActivity;
import com.mikeart.rxartistlist.presentation.mapper.ArtistModelDataMapper;
import com.mikeart.rxartistlist.presentation.model.ArtistModel;
import com.mikeart.rxartistlist.presentation.view.ArtistListView;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class ArtistListPresenter implements Presenter {

  private ArtistListView viewListView;

  private final UseCase getArtistListUseCase;
  private final ArtistModelDataMapper artistModelDataMapper;

  @Inject
  public ArtistListPresenter(@Named("artistList") UseCase getArtistListUseCase,
                             ArtistModelDataMapper artistModelDataMapper) {
    this.getArtistListUseCase = getArtistListUseCase;
    this.artistModelDataMapper = artistModelDataMapper;
  }

  public void setView(@NonNull ArtistListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getArtistListUseCase.unsubscribe();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the artist list.
   */
  public void initialize() {
    this.loadArtistList();
  }

  /**
   * Loads all artists.
   */
  private void loadArtistList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getArtistList();
  }

  public void onArtistClicked(ArtistModel artistModel) {
    this.viewListView.viewArtist(artistModel);
  }

  private void showViewLoading() {
    this.viewListView.showLoading();
  }

  private void hideViewLoading() {
    this.viewListView.hideLoading();
  }

  private void showViewRetry() {
    this.viewListView.showRetry();
  }

  private void hideViewRetry() {
    this.viewListView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
        errorBundle.getException());
    this.viewListView.showError(errorMessage);
  }

  private void showArtistsCollectionInView(List<Artist> artistsList) {
    final List<ArtistModel> artistModelsList =
        this.artistModelDataMapper.transform(artistsList);

    // sorting artist list before viewing
    Collections.sort(artistModelsList, new Comparator<ArtistModel>() {
      public int compare(ArtistModel left, ArtistModel right){
        return left.getName().compareToIgnoreCase(right.getName());
      }
    });

        this.viewListView.renderArtistList(artistModelsList);
  }

  private void getArtistList() {
    this.getArtistListUseCase.execute(new ArtistListSubscriber());
  }

  private final class ArtistListSubscriber extends DefaultSubscriber<List<Artist>> {

    @Override public void onCompleted() {
      ArtistListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      ArtistListPresenter.this.hideViewLoading();
      ArtistListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      ArtistListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<Artist> artists) {
      ArtistListPresenter.this.showArtistsCollectionInView(artists);
    }
  }
}
