package com.mikeart.rxartistlist.data.repository;

import com.mikeart.rxartistlist.data.ApplicationTestCase;
import com.mikeart.rxartistlist.data.entity.ArtistEntity;
import com.mikeart.rxartistlist.data.entity.mapper.ArtistEntityDataMapper;
import com.mikeart.rxartistlist.data.repository.datasource.ArtistDataStore;
import com.mikeart.rxartistlist.data.repository.datasource.ArtistDataStoreFactory;
import com.mikeart.rxartistlist.domain.Artist;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ArtistDataRepositoryTest extends ApplicationTestCase {

  private static final int FAKE_ARTIST_ID = 123;

  private ArtistDataRepository artistDataRepository;

  @Mock private ArtistDataStoreFactory mockArtistDataStoreFactory;
  @Mock private ArtistEntityDataMapper mockArtistEntityDataMapper;
  @Mock private ArtistDataStore mockArtistDataStore;
  @Mock private ArtistEntity mockArtistEntity;
  @Mock private List<ArtistEntity> mockArtistEntityList;
  @Mock private Artist mockArtist;
  @Mock private List<Artist> mockArtistList;

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    artistDataRepository = new ArtistDataRepository(mockArtistDataStoreFactory,
        mockArtistEntityDataMapper);

    given(mockArtistDataStoreFactory.createList()).willReturn(mockArtistDataStore);
    given(mockArtistDataStoreFactory.createCloudDataStore()).willReturn(mockArtistDataStore);
  }

  @Test
  public void testGetArtistsHappyCase() {
    List<ArtistEntity> artistsList = new ArrayList<>();
    artistsList.add(new ArtistEntity());
    given(mockArtistDataStore.artistEntityList()).willReturn(Observable.just(artistsList));

    artistDataRepository.artists();

    verify(mockArtistDataStoreFactory).createList();
    verify(mockArtistDataStore).artistEntityList();
  }


}
