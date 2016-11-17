package com.mikeart.rxartistlist.domain.interactor;

import com.mikeart.rxartistlist.domain.Artist;
import com.mikeart.rxartistlist.domain.executor.PostExecutionThread;
import com.mikeart.rxartistlist.domain.executor.ThreadExecutor;
import com.mikeart.rxartistlist.domain.repository.ArtistRepository;

import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Artist}.
 */
public class GetArtistList extends UseCase {

  private final ArtistRepository artistRepository;

  @Inject
  public GetArtistList(ArtistRepository artistRepository, ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.artistRepository = artistRepository;
  }

  @Override public Observable buildUseCaseObservable() {

    return this.artistRepository.artists();
  }
}
