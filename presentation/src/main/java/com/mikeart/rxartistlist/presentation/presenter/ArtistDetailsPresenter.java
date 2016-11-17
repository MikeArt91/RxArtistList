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
import com.mikeart.rxartistlist.presentation.view.ArtistDetailsView;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class ArtistDetailsPresenter implements Presenter {

  private ArtistDetailsView viewDetailsView;

  private final UseCase getArtistDetailsUseCase;
  private final ArtistModelDataMapper artistModelDataMapper;

  @Inject
  public ArtistDetailsPresenter(@Named("artistDetails") UseCase getArtistDetailsUseCase,
                                ArtistModelDataMapper artistModelDataMapper) {
    this.getArtistDetailsUseCase = getArtistDetailsUseCase;
    this.artistModelDataMapper = artistModelDataMapper;
  }

  public void setView(@NonNull ArtistDetailsView view) {
    this.viewDetailsView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getArtistDetailsUseCase.unsubscribe();
    this.viewDetailsView = null;
  }

  /**
   * Initializes the presenter by start retrieving artist details.
   */
  public void initialize() {
    this.loadArtistDetails();
  }

  /**
   * Loads artist details.
   */
  private void loadArtistDetails() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getArtistDetails();
  }

  private void showViewLoading() {
    this.viewDetailsView.showLoading();
  }

  private void hideViewLoading() {
    this.viewDetailsView.hideLoading();
  }

  private void showViewRetry() {
    this.viewDetailsView.showRetry();
  }

  private void hideViewRetry() {
    this.viewDetailsView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.context(),
        errorBundle.getException());
    this.viewDetailsView.showError(errorMessage);
  }

  private void showArtistDetailsInView(Artist artist) {
    final ArtistModel artistModel = this.artistModelDataMapper.transform(artist);
    this.viewDetailsView.renderArtist(artistModel);
  }

  private void getArtistDetails() {
    this.getArtistDetailsUseCase.execute(new ArtistDetailsSubscriber());
  }

  @RxLogSubscriber
  private final class ArtistDetailsSubscriber extends DefaultSubscriber<Artist> {

    @Override public void onCompleted() {
      ArtistDetailsPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      ArtistDetailsPresenter.this.hideViewLoading();
      ArtistDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      ArtistDetailsPresenter.this.showViewRetry();
    }

    @Override public void onNext(Artist artist) {
      ArtistDetailsPresenter.this.showArtistDetailsInView(artist);
    }
  }
}
