package com.mikeart.rxartistlist.presentation.internal.di.modules;

import com.mikeart.rxartistlist.domain.executor.PostExecutionThread;
import com.mikeart.rxartistlist.domain.executor.ThreadExecutor;
import com.mikeart.rxartistlist.domain.interactor.GetArtistDetails;
import com.mikeart.rxartistlist.domain.interactor.GetArtistList;
import com.mikeart.rxartistlist.domain.interactor.UseCase;
import com.mikeart.rxartistlist.domain.repository.ArtistRepository;
import com.mikeart.rxartistlist.presentation.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Dagger module that provides artist related collaborators.
 */
@Module
public class ArtistModule {

  private int artistId = -1;

  public ArtistModule() {}

  public ArtistModule(int artistId) {
    this.artistId = artistId;
  }

  @Provides @PerActivity @Named("artistList")
  UseCase provideGetUserListUseCase(
      GetArtistList getArtistList) {
    return getArtistList;
  }

  @Provides @PerActivity @Named("artistDetails") UseCase provideGetArtistDetailsUseCase(
          ArtistRepository artistRepository, ThreadExecutor threadExecutor,
          PostExecutionThread postExecutionThread) {
    return new GetArtistDetails(artistId, artistRepository, threadExecutor, postExecutionThread);
  }
}