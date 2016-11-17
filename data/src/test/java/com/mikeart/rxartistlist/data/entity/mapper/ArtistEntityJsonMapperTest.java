package com.mikeart.rxartistlist.data.entity.mapper;

import com.mikeart.rxartistlist.data.ApplicationTestCase;
import com.mikeart.rxartistlist.data.entity.ArtistEntity;
import com.google.gson.JsonSyntaxException;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArtistEntityJsonMapperTest extends ApplicationTestCase {
    private static final String JSON_RESPONSE_ARTIST_DETAILS =
            "{\n"+
            "    \"id\": 1080505,\n" +
            "    \"name\": \"Tove Lo\",\n" +
            "    \"genres\": [\n" +
                "      \"pop\",\n" +
                "      \"dance\",\n" +
                "      \"electronics\"\n" +
                "    ],\n" +
            "    \"tracks\": 81,\n" +
            "    \"albums\": 22,\n" +
            "    \"link\": \"http://www.tove-lo.com/\",\n" +
            "    \"description\": \"шведская певица и автор песен. Она привлекла к себе внимание в\",\n" +
            "    \"cover\": {\n" +
                "      \"small\": \"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300\",\n" +
                "      \"big\": \"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/1000x1000\"\n" +
                "    }\n" +
            "  }";

   private static final String JSON_RESPONSE_ARTIST_COLLECTION = "[\n" +
           "  {\n" +
           "    \"id\": 1080505,\n" +
           "    \"name\": \"Tove Lo\",\n" +
           "    \"genres\": [\n" +
           "      \"pop\",\n" +
           "      \"dance\",\n" +
           "      \"electronics\"\n" +
           "    ],\n" +
           "    \"tracks\": 81,\n" +
           "    \"albums\": 22,\n" +
           "    \"link\": \"http://www.tove-lo.com/\",\n" +
           "    \"description\": \"шведская певица и автор песен. Она привлекла к себе внимание в\",\n" +
           "    \"cover\": {\n" +
           "      \"small\": \"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300\",\n" +
           "      \"big\": \"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/1000x1000\"\n" +
           "    }\n" +
           "  },\n" +
           "  {\n" +
           "    \"id\": 2915,\n" +
           "    \"name\": \"Ne-Yo\",\n" +
           "    \"genres\": [\n" +
           "      \"rnb\",\n" +
           "      \"pop\",\n" +
           "      \"rap\"\n" +
           "    ],\n" +
           "    \"tracks\": 256,\n" +
           "    \"albums\": 152,\n" +
           "    \"link\": \"http://www.neyothegentleman.com/\",\n" +
           "    \"description\": \"обладатель трёх премии Грэмми, американский певец, автор песен\",\n" +
           "    \"cover\": {\n" +
           "      \"small\": \"http://avatars.yandex.net/get-music-content/15ae00fc.p.2915/300x300\",\n" +
           "      \"big\": \"http://avatars.yandex.net/get-music-content/15ae00fc.p.2915/1000x1000\"\n" +
           "    }\n" +
           "  }\n" +
           "]";

    private ArtistEntityJsonMapper artistEntityJsonMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        artistEntityJsonMapper = new ArtistEntityJsonMapper();
    }

    @Test
    public void testTransformArtistEntityHappyCase() {
        ArtistEntity artistEntity = artistEntityJsonMapper.transformArtistEntity(JSON_RESPONSE_ARTIST_DETAILS);

        assertThat(artistEntity.getArtistId(), is(1080505));
        assertThat(artistEntity.getName(), is(equalTo("Tove Lo")));
        assertThat(artistEntity.getLink(), is(equalTo("http://www.tove-lo.com/")));
        assertThat(artistEntity.getGenres(), is(equalTo(Arrays.asList("pop", "dance", "electronics"))));
        assertThat(artistEntity.getCoverSmall(), is(equalTo("http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300")));
    }

    @Test
    public void testTransformArtistEntityCollectionHappyCase() {
        Collection<ArtistEntity> artistEntityCollection =
                artistEntityJsonMapper.transformArtistEntityCollection(
                        JSON_RESPONSE_ARTIST_COLLECTION);

        assertThat(((ArtistEntity) artistEntityCollection.toArray()[0]).getArtistId(), is(1080505));
        assertThat(((ArtistEntity) artistEntityCollection.toArray()[1]).getArtistId(), is(2915));
        assertThat(artistEntityCollection.size(), is(2));
    }

    @Test
    public void testTransformArtistEntityNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        artistEntityJsonMapper.transformArtistEntity("ironman");
    }

    @Test
    public void testTransformArtistEntityCollectionNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        artistEntityJsonMapper.transformArtistEntityCollection("Tony Stark");
    }
}
