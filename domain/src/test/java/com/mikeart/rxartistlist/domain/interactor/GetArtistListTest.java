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

public class GetArtistListTest {

  private GetArtistList getArtistList;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;
  @Mock private ArtistRepository mockArtistRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    getArtistList = new GetArtistList(mockArtistRepository, mockThreadExecutor,
        mockPostExecutionThread);
  }

  @Test
  public void testGetArtistListUseCaseObservableHappyCase() {
    getArtistList.buildUseCaseObservable();

    verify(mockArtistRepository).artists();
    verifyNoMoreInteractions(mockArtistRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }
}
