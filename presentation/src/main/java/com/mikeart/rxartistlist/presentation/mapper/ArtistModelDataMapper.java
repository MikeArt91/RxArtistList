package com.mikeart.rxartistlist.presentation.mapper;

import com.mikeart.rxartistlist.domain.Artist;
import com.mikeart.rxartistlist.presentation.internal.di.PerActivity;
import com.mikeart.rxartistlist.presentation.model.ArtistModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Artist} (in the domain layer) to {@link ArtistModel} in the
 * presentation layer.
 */
@PerActivity
public class ArtistModelDataMapper {
    @Inject
    public ArtistModelDataMapper() {}

    /**
     * Transform a {@link Artist} into an {@link ArtistModel}.
     *
     * @param artist Object to be transformed.
     * @return {@link ArtistModel}.
     */
    public ArtistModel transform(Artist artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        ArtistModel artistModel = new ArtistModel(artist.getArtistId());
        artistModel.setName(artist.getName());
        artistModel.setGenres(artist.getGenres());
        artistModel.setTracks(artist.getTracks());
        artistModel.setAlbums(artist.getAlbums());
        artistModel.setLink(artist.getLink());
        artistModel.setDescription(artist.getDescription());
        artistModel.setCoverBig(artist.getCoverBig());
        artistModel.setCoverSmall(artist.getCoverSmall());

        return artistModel;
    }

    /**
     * Transform a List of {@link Artist} into a List of {@link ArtistModel}.
     *
     * @param artistsList Objects to be transformed.
     * @return List of {@link ArtistModel}.
     */

    public List<ArtistModel> transform(List<Artist> artistsList) {
        List<ArtistModel> artistModelsList;

        if (artistsList != null && !artistsList.isEmpty()) {
            artistModelsList = new ArrayList<>();
            for (Artist artist : artistsList) {
                artistModelsList.add(transform(artist));
            }
        } else {
            artistModelsList = Collections.emptyList();
        }

        return artistModelsList;
    }
}
