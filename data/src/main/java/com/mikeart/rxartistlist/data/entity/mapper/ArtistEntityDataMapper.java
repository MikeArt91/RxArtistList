package com.mikeart.rxartistlist.data.entity.mapper;

import com.mikeart.rxartistlist.data.entity.ArtistEntity;
import com.mikeart.rxartistlist.domain.Artist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ArtistEntity} (in the data layer) to {@link Artist} in the
 * domain layer.
 */
@Singleton
public class ArtistEntityDataMapper {

    @Inject
    public ArtistEntityDataMapper() {}

    /**
     * Transform a {@link ArtistEntity} into an {@link Artist}.
     *
     * @param artistEntity Object to be transformed.
     * @return {@link Artist} if valid {@link Artist} otherwise null.
     */
    public Artist transform(ArtistEntity artistEntity) {
        Artist artist = null;
        if (artistEntity != null) {
            artist = new Artist(artistEntity.getArtistId());
            artist.setName(artistEntity.getName());
            artist.setGenres(ListToString(artistEntity.getGenres())); // transfroming list to string
            artist.setTracks(artistEntity.getTracks());
            artist.setAlbums(artistEntity.getAlbums());
            artist.setLink(artistEntity.getLink());
            artist.setDescription(artistEntity.getDescription());
            artist.setCoverBig(artistEntity.getCoverBig());
            artist.setCoverSmall(artistEntity.getCoverSmall());
        }

        return artist;
    }

    /**
     * Transform a List of {@link ArtistEntity} into a Collection of {@link Artist}.
     *
     * @param artistEntityCollection Object Collection to be transformed.
     * @return {@link Artist} if valid {@link ArtistEntity} otherwise null.
     */
    public List<Artist> transform(Collection<ArtistEntity> artistEntityCollection) {
        List<Artist> artistsList = new ArrayList<>(20);
        Artist artist;
        for (ArtistEntity artistEntity : artistEntityCollection) {
            artist = transform(artistEntity);
            if (artist != null) {
                artistsList.add(artist);
            }
        }

        return artistsList;
    }


    public String ListToString(List<String> genresList) {
        String genresString;
        StringBuilder stringBuilder = new StringBuilder();

        for(String s:genresList) {
            stringBuilder.append(s);
            stringBuilder.append(", ");
        }
        genresString = stringBuilder.toString();

        // this is to delete ", " from the end of the string
        genresString = genresString.length()>0
                ? genresString.substring(0,genresString.length()-2) :genresString;

        return genresString;
    }


}
