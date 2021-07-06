package com.example.spotify.model.api.interfaces;

import com.example.spotify.model.pojo.ArtistSearchItem;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;

public interface SearchApi {
    @GET("/v1/search")
    Call<ArtistSearchItem> getArtists(@QueryMap Map<String, String> parameters, @HeaderMap Map<String, String> headers);
}
