package com.mikeart.rxartistlist.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mikeart.rxartistlist.data.cache.ListCache;
import com.mikeart.rxartistlist.data.entity.mapper.ArtistEntityJsonMapper;
import com.mikeart.rxartistlist.data.net.RestApi;
import com.mikeart.rxartistlist.data.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link ArtistDataStore}.
 */
@Singleton
public class ArtistDataStoreFactory {

  private final Context context;
  private final ListCache listCache;

  @Inject
  public ArtistDataStoreFactory(@NonNull Context context, @NonNull ListCache listCache) {
    this.context = context.getApplicationContext();
    this.listCache = listCache;

  }

  public ArtistDataStore createList() {
    ArtistDataStore userDataStore;

    if (!this.listCache.isExpired() && this.listCache.isCached()) {
      userDataStore = new DiskListDataStore(this.listCache);
    } else {
      userDataStore = createCloudDataStore();
    }

    return userDataStore;
  }


  /**
   * Create {@link ArtistDataStore} to retrieve data from the Cloud.
   */
  public ArtistDataStore createCloudDataStore() {
    ArtistEntityJsonMapper artistEntityJsonMapper = new ArtistEntityJsonMapper();
    RestApi restApi = new RestApiImpl(this.context, artistEntityJsonMapper);

    return new CloudArtistDataStore(restApi, this.listCache);
  }
}
