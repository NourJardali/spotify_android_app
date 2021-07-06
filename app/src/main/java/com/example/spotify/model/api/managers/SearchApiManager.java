package com.example.spotify.model.api.managers;

import com.example.spotify.model.api.interfaces.SearchApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchApiManager {
    private SearchApi searchApi;

    public SearchApi getArtistsApi(){
        if(searchApi == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            searchApi = new Retrofit.Builder().baseUrl("https://api.spotify.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient).build().create(SearchApi.class);
        }
        return searchApi;
    }
}
