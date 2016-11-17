package com.mikeart.rxartistlist.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ArtistEntity {

    private class Cover {

        private Cover() {
            //empty
        }

        @SerializedName("big")
        private String big;

        @SerializedName("small")
        private String small;

    }

    @SerializedName("id")
    private int artistId;

    @SerializedName("name")
    private String name;

    @SerializedName("genres")
    private List<String> genres = new ArrayList<String>();

    @SerializedName("tracks")
    private int tracks;

    @SerializedName("albums")
    private int albums;

    @SerializedName("link")
    private String link;

    @SerializedName("description")
    private String description;

    @SerializedName("cover")
    private Cover cover = new Cover();

    public ArtistEntity() {
        //empty
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
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
        return cover.big;
    }

    public void setCoverBig(String coverBig) {
        this.cover.big = coverBig;
    }

    public String getCoverSmall() {
        return cover.small;
    }

    public void setCoverSmall(String coverSmall) {
        this.cover.small = coverSmall;
    }

    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Artist Entity Details *****\n");
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
