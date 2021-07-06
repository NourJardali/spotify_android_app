package com.example.spotify.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ArtistSearchItem implements Serializable {
    public static class Followers implements Serializable {
        @SerializedName("total")
        @Expose
        private int total;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
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
        @SerializedName("followers")
        @Expose
        private Followers followers;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("images")
        @Expose
        private List<Image> images;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("popularity")
        @Expose
        private int popularity;

        public Followers getFollowers() {
            return followers;
        }

        public int getPopularity() {
            return popularity;
        }

        public List<Image> getImages() {
            return images;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setFollowers(Followers followers) {
            this.followers = followers;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPopularity(int popularity) {
            this.popularity = popularity;
        }
    }

    public static class Artist implements Serializable {
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

    @SerializedName("artists")
    @Expose
    private Artist artists;

    public Artist getArtists() {
        return artists;
    }

    public void setArtists(Artist artists) {
        this.artists = artists;
    }
}
