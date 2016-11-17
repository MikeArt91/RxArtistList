package com.mikeart.rxartistlist.data.repository.datasource;

import com.mikeart.rxartistlist.data.cache.ListCache;
import com.mikeart.rxartistlist.data.entity.ArtistEntity;


import java.util.List;

import rx.Observable;

class DiskListDataStore implements ArtistDataStore {

    private final ListCache listCache;

    DiskListDataStore(ListCache listCache) {
        this.listCache = listCache;
    }

    @Override public Observable<List<ArtistEntity>> artistEntityList() {
        return this.listCache.get();
    }

}
