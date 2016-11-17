package com.mikeart.rxartistlist.data.cache;

import com.mikeart.rxartistlist.data.entity.ArtistEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Mike on 11.11.2016.
 */

public interface ListCache {


    /**
     * Gets an {@link rx.Observable} which will emit a {@link ArtistEntity}.
     *
     * Method is used to retrieve list data.
     */
    Observable<List<ArtistEntity>> get();

    /**
     * Puts and element into the cache.
     *
     * @param artistEntityList Element to insert in the cache.
     */
    void put(List<ArtistEntity> artistEntityList);

    /**
     * Checks if an element (Artist) exists in the cache.
     *
     * used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached();

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
