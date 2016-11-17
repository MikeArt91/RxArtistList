package com.mikeart.rxartistlist.data.repository;

import com.mikeart.rxartistlist.data.entity.mapper.ArtistEntityDataMapper;
import com.mikeart.rxartistlist.data.repository.datasource.ArtistDataStore;
import com.mikeart.rxartistlist.data.repository.datasource.ArtistDataStoreFactory;
import com.mikeart.rxartistlist.domain.Artist;
import com.mikeart.rxartistlist.domain.repository.ArtistRepository;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * {@link ArtistRepository} for retrieving artist data.
 */
@Singleton
public class ArtistDataRepository implements ArtistRepository {

  private final ArtistDataStoreFactory artistDataStoreFactory;
  private final ArtistEntityDataMapper artistEntityDataMapper;

  /**
   * Constructs a {@link ArtistRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param artistEntityDataMapper {@link ArtistEntityDataMapper}.
   */
  @Inject
  public ArtistDataRepository(ArtistDataStoreFactory dataStoreFactory,
                              ArtistEntityDataMapper artistEntityDataMapper) {
    this.artistDataStoreFactory = dataStoreFactory;
    this.artistEntityDataMapper = artistEntityDataMapper;
  }

  @Override public Observable<List<Artist>> artists() {
    final ArtistDataStore artistDataStore = this.artistDataStoreFactory.createList();
    return artistDataStore.artistEntityList().map(this.artistEntityDataMapper::transform);
  }

  @Override public Observable<Artist> artist(int artistId) {
    return artists().flatMapIterable(ArtistEntity -> ArtistEntity).filter(UserEntity -> UserEntity.getArtistId() == artistId);
  }
}
