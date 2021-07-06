package com.example.spotify.controller;

import android.util.Log;

import com.example.spotify.interfaces.ArtistSearchListener;
import com.example.spotify.model.api.managers.SearchApiManager;
import com.example.spotify.model.pojo.ArtistSearchItem;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchController implements ISearchController {
    private final SearchApiManager searchApiManager = new SearchApiManager();
    private static final String TAG = SearchController.class.getSimpleName();
    private final ArtistSearchListener artistSearchListener;

    public SearchController(ArtistSearchListener listener){
        artistSearchListener = listener;
    }

    @Override
    public void startFetching(String query, String accessToken) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("q", query);
        parameters.put("type", "artist");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization",  "Bearer " + accessToken);
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        Call<ArtistSearchItem> searchCall = searchApiManager.getArtistsApi().getArtists(parameters, headers);
        searchCall.enqueue(new Callback<ArtistSearchItem>() {
            @Override
            public void onResponse(Call<ArtistSearchItem> call, Response<ArtistSearchItem> response) {
                if(response.code() == 200){
                    artistSearchListener.onFetchProgress(response.body());
                    artistSearchListener.onFetchComplete(true);
                }
                else{
                    artistSearchListener.onFetchComplete(false);
                }
            }

            @Override
            public void onFailure(Call<ArtistSearchItem> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                artistSearchListener.onFetchComplete(false);
            }
        });
    }
}
