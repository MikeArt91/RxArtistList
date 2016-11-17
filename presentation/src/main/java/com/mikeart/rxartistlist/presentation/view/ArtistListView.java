package com.mikeart.rxartistlist.presentation.view;


import com.mikeart.rxartistlist.presentation.model.ArtistModel;

import java.util.List;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link ArtistModel}.
 */
public interface ArtistListView extends LoadDataView {
  /**
   * Render a artist list in the UI.
   *
   * @param artistModelList The list of {@link ArtistModel} that will be shown.
   */

  void renderArtistList(List<ArtistModel> artistModelList);

  /**
   * View a {@link ArtistModel} profile/details.
   *
   * @param artistModel The artist that will be shown.
   */
  void viewArtist(ArtistModel artistModel);
}
