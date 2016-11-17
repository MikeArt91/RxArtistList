package com.mikeart.rxartistlist.presentation.view;

import com.mikeart.rxartistlist.presentation.model.ArtistModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a artist profile.
 */
public interface ArtistDetailsView extends LoadDataView {
  /**
   * Render a artist in the UI.
   *
   * @param artist The {@link ArtistModel} that will be shown.
   */
  void renderArtist(ArtistModel artist);
}
