package com.mikeart.rxartistlist.data.repository.datasource;

import com.mikeart.rxartistlist.data.ApplicationTestCase;
import com.mikeart.rxartistlist.data.cache.ListCache;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ArtistDataStoreFactoryTest extends ApplicationTestCase {

  private ArtistDataStoreFactory artistDataStoreFactory;

  @Mock private ListCache mockListCache;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    artistDataStoreFactory =
        new ArtistDataStoreFactory(RuntimeEnvironment.application, mockListCache);
  }

  @Test
  public void testCreateDiskDataStore() {
    given(mockListCache.isCached()).willReturn(true);
    given(mockListCache.isExpired()).willReturn(false);

    ArtistDataStore artistDataStore = artistDataStoreFactory.createList();

    assertThat(artistDataStore, is(notNullValue()));
    assertThat(artistDataStore, is(instanceOf(ArtistDataStore.class)));

    verify(mockListCache).isCached();
    verify(mockListCache).isExpired();
  }


  @Test
  public void testCreateCloudDataStore() {
    given(mockListCache.isExpired()).willReturn(true);
    given(mockListCache.isCached()).willReturn(false);

    ArtistDataStore artistDataStore = artistDataStoreFactory.createList();

    assertThat(artistDataStore, is(notNullValue()));
    assertThat(artistDataStore, is(instanceOf(CloudArtistDataStore.class)));

    verify(mockListCache).isExpired();
  }

}
