package com.example.spotify.model.api.managers;

import com.example.spotify.model.api.interfaces.AlbumApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumApiManager {
    private AlbumApi albumApi;

    public AlbumApi getAlbumApi(){
        if(albumApi == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            albumApi = new Retrofit.Builder().baseUrl("https://api.spotify.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient).build().create(AlbumApi.class);
        }
        return albumApi;
    }
}
