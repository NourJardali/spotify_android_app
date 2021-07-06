package com.example.spotify.model.api.interfaces;

import com.example.spotify.model.pojo.Albums;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface AlbumApi {
    @GET("/v1/artists/{id}/albums")
    Call<Albums> getAlbums(@Path(value = "id", encoded = true) String id, @QueryMap Map<String, String> parameters, @HeaderMap Map<String, String> headers);
}
