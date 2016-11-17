package com.mikeart.rxartistlist.data.entity.mapper;

import com.mikeart.rxartistlist.data.ApplicationTestCase;
import com.mikeart.rxartistlist.data.entity.ArtistEntity;
import com.mikeart.rxartistlist.domain.Artist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ArtistEntityDataMapperTest extends ApplicationTestCase {

    private static final int FAKE_ARTIST_ID = 100500;
    private static final String FAKE_NAME = "Billy Jean";
    private static final List<String> FAKE_GENRES_LIST = Arrays.asList("pop","rock","rap");
    private static final String FAKE_GENRES_STR = "pop, rock, rap";
    private static final String FAKE_COVER_URL = "http://www.troll.com/face.jpg";

    private ArtistEntityDataMapper artistEntityDataMapper;

    @Before
    public void setUp() throws Exception {
        artistEntityDataMapper = new ArtistEntityDataMapper();
    }

    @Test
    public void testTransformArtistEntity() {
        ArtistEntity artistEntity = createFakeArtistEntity();
        Artist artist = artistEntityDataMapper.transform(artistEntity);

        assertThat(artist, is(instanceOf(Artist.class)));
        assertThat(artist.getArtistId(), is(FAKE_ARTIST_ID));
        assertThat(artist.getName(), is(FAKE_NAME));
        assertThat(artist.getGenres(), is(FAKE_GENRES_STR));
        assertThat(artist.getCoverBig(), is(FAKE_COVER_URL));
    }

    @Test
    public void testTransformArtistEntityCollection() {
        ArtistEntity mockArtistEntityOne = mock(ArtistEntity.class);
        ArtistEntity mockArtistEntityTwo = mock(ArtistEntity.class);

        List<ArtistEntity> artistEntityList = new ArrayList<>(5);
        artistEntityList.add(mockArtistEntityOne);
        artistEntityList.add(mockArtistEntityTwo);

        Collection<Artist> artistCollection = artistEntityDataMapper.transform(artistEntityList);

        assertThat(artistCollection.toArray()[0], is(instanceOf(Artist.class)));
        assertThat(artistCollection.toArray()[1], is(instanceOf(Artist.class)));
        assertThat(artistCollection.size(), is(2));
    }

    private ArtistEntity createFakeArtistEntity() {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setArtistId(FAKE_ARTIST_ID);
        artistEntity.setName(FAKE_NAME);
        artistEntity.setGenres(FAKE_GENRES_LIST);
        artistEntity.setCoverBig(FAKE_COVER_URL);

        return artistEntity;
    }

}
