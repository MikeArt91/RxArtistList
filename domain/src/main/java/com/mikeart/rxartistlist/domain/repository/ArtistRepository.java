package com.mikeart.rxartistlist.domain.repository;

import com.mikeart.rxartistlist.domain.Artist;

import java.util.List;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Artist} related data.
 */
public interface ArtistRepository {
  /**
   * Get an {@link rx.Observable} which will emit a List of {@link Artist}.
   */
  Observable<List<Artist>> artists();

  /**
   * Get an {@link rx.Observable} which will emit a {@link Artist}.
   *
   * @param artistId The artist id used to retrieve artist data.
   */
  Observable<Artist> artist(final int artistId);
}
