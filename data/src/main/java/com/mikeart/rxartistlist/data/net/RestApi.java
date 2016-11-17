package com.mikeart.rxartistlist.data.net;

import com.mikeart.rxartistlist.data.entity.ArtistEntity;

import java.util.List;
import rx.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
  String API_BASE_URL = "http://download.cdn.yandex.net/mobilization-2016/";
  /** Api url for getting all artists */
  String API_URL_GET_ARTIST_LIST = API_BASE_URL + "artists.json";

  /**
   * Retrieves an {@link rx.Observable} which will emit a List of {@link ArtistEntity}.
   */
  Observable<List<ArtistEntity>> artistEntityList();

}
