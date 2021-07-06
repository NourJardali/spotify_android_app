package com.example.spotify.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Albums implements Serializable {
    public static class ExternalUrls implements Serializable {
        @SerializedName("spotify")
        @Expose
        private String spotify;

        public String getSpotify() {
            return spotify;
        }

        public void setSpotify(String spotify) {
            this.spotify = spotify;
        }
    }

    public static class Artist implements Serializable {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class Image implements Serializable {
        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class Item implements Serializable {
        @SerializedName("external_urls")
        @Expose
        private ExternalUrls external_urls;
        @SerializedName("artists")
        @Expose
        private List<Artist> artists;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("images")
        @Expose
        private List<Image> images;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("release_date")
        @Expose
        private String release_date;
        @SerializedName("total_tracks")
        @Expose
        private int total_tracks;

        public ExternalUrls getExternal_urls() {
            return external_urls;
        }

        public void setExternal_urls(ExternalUrls external_urls) {
            this.external_urls = external_urls;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<Image> getImages() {
            return images;
        }

        public int getTotal_tracks() {
            return total_tracks;
        }

        public List<Artist> getArtists() {
            return artists;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setArtists(List<Artist> artists) {
            this.artists = artists;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public void setTotal_tracks(int total_tracks) {
            this.total_tracks = total_tracks;
        }
    }

    @SerializedName("items")
    @Expose
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
