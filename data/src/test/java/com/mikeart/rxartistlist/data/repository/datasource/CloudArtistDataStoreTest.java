package com.mikeart.rxartistlist.data.repository.datasource;

import com.mikeart.rxartistlist.data.ApplicationTestCase;
import com.mikeart.rxartistlist.data.cache.ListCache;
import com.mikeart.rxartistlist.data.entity.ArtistEntity;
import com.mikeart.rxartistlist.data.net.RestApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CloudArtistDataStoreTest extends ApplicationTestCase {

  private static final int FAKE_ARTIST_ID = 765;

  private CloudArtistDataStore cloudArtistDataStore;

  @Mock private RestApi mockRestApi;
  @Mock private ListCache mockListCache;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    cloudArtistDataStore = new CloudArtistDataStore(mockRestApi, mockListCache);
  }

  @Test
  public void testGetArtistEntityListFromApi() {
    List<ArtistEntity> fakeListEntity = new ArrayList<ArtistEntity>(5) {};
    Observable<List<ArtistEntity>> fakeObservable = Observable.just(fakeListEntity);

    given(mockRestApi.artistEntityList()).willReturn(fakeObservable);

    cloudArtistDataStore.artistEntityList();
    verify(mockRestApi).artistEntityList();
  }

}
