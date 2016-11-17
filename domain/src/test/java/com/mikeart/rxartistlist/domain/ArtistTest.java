package com.mikeart.rxartistlist.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArtistTest {
    private static final int FAKE_ARTIST_ID = 7;

    private Artist artist;

    @Before
    public void setUp() {
        artist = new Artist(FAKE_ARTIST_ID);
    }

    @Test
    public void testUserConstructorHappyCase() {
        int artistId = artist.getArtistId();

        assertThat(artistId, is(FAKE_ARTIST_ID));
    }
}
