package com.mikeart.rxartistlist.data.cache.serializer;

import com.mikeart.rxartistlist.data.ApplicationTestCase;
import com.mikeart.rxartistlist.data.entity.ArtistEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArtistSerializerTest extends ApplicationTestCase {

    private static final String JSON_LIST_RESPONSE = "[\n" +
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

    private JsonSerializer jsonSerializer;

    @Before
    public void setUp() {
        jsonSerializer = new JsonSerializer();
    }

    @Test
    public void testSerializeListHappyCase() {
        List<ArtistEntity> artistEntityOne = jsonSerializer.deserializeArtist(JSON_LIST_RESPONSE);
        String jsonString = jsonSerializer.serializeArtist(artistEntityOne);
        List<ArtistEntity> artistEntityTwo = jsonSerializer.deserializeArtist(jsonString);

        for (int i = 0; i<artistEntityOne.size(); i++){
            assertThat(artistEntityOne.get(i).getArtistId(), is(artistEntityTwo.get(i).getArtistId()));
            assertThat(artistEntityOne.get(i).getName(), is(equalTo(artistEntityTwo.get(i).getName())));
            assertThat(artistEntityOne.get(i).getGenres(), is(equalTo(artistEntityTwo.get(i).getGenres())));
            assertThat(artistEntityOne.get(i).getTracks(), is(artistEntityTwo.get(i).getTracks()));
            assertThat(artistEntityOne.get(i).getAlbums(), is(artistEntityTwo.get(i).getAlbums()));
            assertThat(artistEntityOne.get(i).getLink(), is(equalTo(artistEntityTwo.get(i).getLink())));
            assertThat(artistEntityOne.get(i).getDescription(), is(equalTo(artistEntityTwo.get(i).getDescription())));
            assertThat(artistEntityOne.get(i).getCoverSmall(), is(equalTo(artistEntityTwo.get(i).getCoverSmall())));
            assertThat(artistEntityOne.get(i).getCoverBig(), is(equalTo(artistEntityTwo.get(i).getCoverBig())));
        }
    }

    @Test
    public void testDesearializeListHappyCase() {
        List<ArtistEntity> artistEntity = jsonSerializer.deserializeArtist(JSON_LIST_RESPONSE);

        assertThat(artistEntity.get(0).getArtistId(), is(1080505));
        assertThat(artistEntity.get(0).getName(), is("Tove Lo"));
        assertThat(artistEntity.get(0).getGenres(), is(Arrays.asList("pop", "dance", "electronics")));
        assertThat(artistEntity.get(0).getTracks(), is(81));
        assertThat(artistEntity.get(0).getAlbums(), is(22));
        assertThat(artistEntity.get(0).getLink(), is("http://www.tove-lo.com/"));
        assertThat(artistEntity.get(0).getDescription(), is("шведская певица и автор песен. Она привлекла к себе внимание в"));
        assertThat(artistEntity.get(0).getCoverSmall(), is("http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300"));
        assertThat(artistEntity.get(0).getCoverBig(), is("http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/1000x1000"));

    }

}
