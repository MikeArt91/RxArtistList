package com.mikeart.rxartistlist.test.presenter;

import android.content.Context;
import android.test.AndroidTestCase;

import com.mikeart.rxartistlist.domain.interactor.GetArtistList;
import com.mikeart.rxartistlist.presentation.mapper.ArtistModelDataMapper;
import com.mikeart.rxartistlist.presentation.presenter.ArtistListPresenter;
import com.mikeart.rxartistlist.presentation.view.ArtistListView;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Subscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class ArtistListPresenterTest extends AndroidTestCase {

  private ArtistListPresenter artistListPresenter;

  @Mock
  private Context mockContext;
  @Mock
  private ArtistListView mockArtistListView;
  @Mock
  private GetArtistList mockGetArtistList;
  @Mock
  private ArtistModelDataMapper mockArtistModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    MockitoAnnotations.initMocks(this);
    artistListPresenter = new ArtistListPresenter(mockGetArtistList, mockArtistModelDataMapper);
    artistListPresenter.setView(mockArtistListView);
  }

  public void testArtistListPresenterInitialize() {
    given(mockArtistListView.context()).willReturn(mockContext);

    artistListPresenter.initialize();

    verify(mockArtistListView).hideRetry();
    verify(mockArtistListView).showLoading();
    verify(mockGetArtistList).execute(any(Subscriber.class));
  }
}
