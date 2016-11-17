package com.mikeart.rxartistlist.domain.interactor;

import com.mikeart.rxartistlist.domain.executor.PostExecutionThread;
import com.mikeart.rxartistlist.domain.executor.ThreadExecutor;
import com.mikeart.rxartistlist.domain.repository.ArtistRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetArtistDetailsTest {

  private static final int FAKE_ARTIST_ID = 123;

  private GetArtistDetails getArtistDetails;

  @Mock private ArtistRepository mockArtistRepository;
  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    getArtistDetails = new GetArtistDetails(FAKE_ARTIST_ID, mockArtistRepository,
        mockThreadExecutor, mockPostExecutionThread);
  }

  @Test
  public void testGetArtistDetailsUseCaseObservableHappyCase() {
    getArtistDetails.buildUseCaseObservable();

    verify(mockArtistRepository).artist(FAKE_ARTIST_ID);
    verifyNoMoreInteractions(mockArtistRepository);
    verifyZeroInteractions(mockPostExecutionThread);
    verifyZeroInteractions(mockThreadExecutor);
  }
}
