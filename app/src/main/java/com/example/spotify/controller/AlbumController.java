package com.example.spotify.controller;

import android.util.Log;

import com.example.spotify.interfaces.AlbumListener;
import com.example.spotify.model.api.managers.AlbumApiManager;
import com.example.spotify.model.pojo.Albums;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumController implements IAlbumController {
    private final AlbumApiManager albumApiManager = new AlbumApiManager();
    private static final String TAG = AlbumController.class.getSimpleName();
    private final AlbumListener albumListener;

    public AlbumController(AlbumListener albumListener){
        this.albumListener = albumListener;
    }

    @Override
    public void startFetching(String id, String accessToken) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("include_groups", "album");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization",  "Bearer " + accessToken);
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        Call<Albums> albumsCall = albumApiManager.getAlbumApi().getAlbums(id, parameters, headers);
        albumsCall.enqueue(new Callback<Albums>() {
            @Override
            public void onResponse(Call<Albums> call, Response<Albums> response) {
                if(response.code() == 200){
                    albumListener.onFetchProgress(response.body());
                    albumListener.onFetchComplete(true);
                }
                else{
                    albumListener.onFetchComplete(false);
                }
            }

            @Override
            public void onFailure(Call<Albums> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                albumListener.onFetchComplete(false);
            }
        });
    }
}
