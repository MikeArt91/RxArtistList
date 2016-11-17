package com.mikeart.rxartistlist.data.repository.datasource;

import com.mikeart.rxartistlist.data.ApplicationTestCase;
import com.mikeart.rxartistlist.data.cache.ListCache;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DiskListDataStoreTest extends ApplicationTestCase {

    private DiskListDataStore diskListDataStore;

    private static final int FAKE_ARTIST_ID = 11;

    @Mock private ListCache mockListCache;

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        diskListDataStore = new DiskListDataStore(mockListCache);
    }

    @Test
    public void testGetArtistEntityListFromCache() {
        diskListDataStore.artistEntityList();
        verify(mockListCache).get();
    }

}
