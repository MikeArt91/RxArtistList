package com.mikeart.rxartistlist.data.repository.datasource;

import com.mikeart.rxartistlist.data.cache.ListCache;
import com.mikeart.rxartistlist.data.entity.ArtistEntity;
import com.mikeart.rxartistlist.data.net.RestApi;

import java.util.List;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link ArtistDataStore} implementation based on connections to the api (Cloud).
 */
class CloudArtistDataStore implements ArtistDataStore {

  private final RestApi restApi;
  private final ListCache listCache;

  private final Action1<List<ArtistEntity>> saveToCacheListAction = artistEntities -> {
    if (artistEntities != null) {
      CloudArtistDataStore.this.listCache.put(artistEntities);
    }
  };

  /**
   * Construct a {@link ArtistDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param listCache A {@link ListCache} to cache data retrieved from the api.
   */
  CloudArtistDataStore(RestApi restApi, ListCache listCache) {
    this.restApi = restApi;
    //this.userCache = userCache;
    this.listCache = listCache;
  }

  @Override public Observable<List<ArtistEntity>> artistEntityList() {
    return this.restApi.artistEntityList().doOnNext(saveToCacheListAction);
  }

}
