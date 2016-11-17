package com.mikeart.rxartistlist.presentation.model;

/**
 * Class that represents an artist in the presentation layer.
 */
public class ArtistModel {
    private final int artistId;

    public ArtistModel(int artistId) {this.artistId = artistId;}

    private String name;
    private String genres;
    private int tracks;
    private int albums;
    private String link;
    private String description;
    private String coverBig;
    private String coverSmall;

    public int getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public int getAlbums() {
        return albums;
    }

    public void setAlbums(int albums) {
        this.albums = albums;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverBig() {
        return coverBig;
    }

    public void setCoverBig(String coverBig) {
        this.coverBig = coverBig;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Artist Model Details *****\n");
        stringBuilder.append("artistId=" + this.getArtistId() + "\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("genres=" + this.getGenres() + "\n");
        stringBuilder.append("tracks=" + this.getTracks() + "\n");
        stringBuilder.append("albums=" + this.getAlbums() + "\n");
        stringBuilder.append("link=" + this.getLink() + "\n");
        stringBuilder.append("description=" + this.getDescription() + "\n");
        stringBuilder.append("cover big=" + this.getCoverBig() + "\n");
        stringBuilder.append("cover small=" + this.getCoverSmall() + "\n");
        stringBuilder.append("*******************************\n");

        return stringBuilder.toString();
    }
}
