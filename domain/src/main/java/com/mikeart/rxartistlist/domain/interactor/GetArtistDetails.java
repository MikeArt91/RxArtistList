package com.mikeart.rxartistlist.domain.interactor;

import com.mikeart.rxartistlist.domain.Artist;
import com.mikeart.rxartistlist.domain.executor.PostExecutionThread;
import com.mikeart.rxartistlist.domain.executor.ThreadExecutor;
import com.mikeart.rxartistlist.domain.repository.ArtistRepository;

import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Artist}.
 */
public class GetArtistDetails extends UseCase {

  private final int artistId;
  private final ArtistRepository artistRepository;

  @Inject
  public GetArtistDetails(int artistId, ArtistRepository artistRepository,
                          ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.artistId = artistId;
    this.artistRepository = artistRepository;
  }

  @Override protected Observable buildUseCaseObservable() {
    return this.artistRepository.artist(this.artistId);
  }
}
