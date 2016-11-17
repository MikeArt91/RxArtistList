package com.mikeart.rxartistlist.test.mapper;

import com.mikeart.rxartistlist.domain.Artist;
import com.mikeart.rxartistlist.presentation.mapper.ArtistModelDataMapper;
import com.mikeart.rxartistlist.presentation.model.ArtistModel;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;


public class ArtistModelDataMapperTest extends TestCase{

    private static final int FAKE_ARTIST_ID = 10500;
    private static final String FAKE_NAME = "Billy Jean";
    private static final String FAKE_GENRES = "pop, rock";
    private static final String FAKE_COVER_URL = "http://www.troll.com/face.jpg";

    private ArtistModelDataMapper artistModelDataMapper;

    @Override protected void setUp() throws Exception {
        super.setUp();
        artistModelDataMapper = new ArtistModelDataMapper();
    }

    public void testTransformArtist() {
        Artist artist = createFakeArtist();
        ArtistModel artistModel = artistModelDataMapper.transform(artist);

        assertThat(artistModel, is(instanceOf(ArtistModel.class)));
        assertThat(artistModel.getArtistId(), is(FAKE_ARTIST_ID));
        assertThat(artistModel.getName(), is(FAKE_NAME));
        assertThat(artistModel.getGenres(), is(FAKE_GENRES));
        assertThat(artistModel.getCoverBig(), is(FAKE_COVER_URL));
    }

    public void testTransformArtistCollection() {
        Artist mockArtistOne = mock(Artist.class);
        Artist mockArtistTwo = mock(Artist.class);

        List<Artist> artistList = new ArrayList<Artist>(5);
        artistList.add(mockArtistOne);
        artistList.add(mockArtistTwo);

        List<ArtistModel> artistModelList = artistModelDataMapper.transform(artistList);

        assertThat(artistModelList.toArray()[0], is(instanceOf(ArtistModel.class)));
        assertThat(artistModelList.toArray()[1], is(instanceOf(ArtistModel.class)));
        assertThat(artistModelList.size(), is(2));
    }

    private Artist createFakeArtist() {
        Artist artist = new Artist(FAKE_ARTIST_ID);
        artist.setName(FAKE_NAME);
        artist.setGenres(FAKE_GENRES);
        artist.setCoverBig(FAKE_COVER_URL);

        return artist;
    }
}
