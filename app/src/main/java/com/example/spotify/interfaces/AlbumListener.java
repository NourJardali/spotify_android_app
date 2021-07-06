package com.example.spotify.interfaces;

import com.example.spotify.model.pojo.Albums;

public interface AlbumListener {
    void onFetchProgress(Albums albums);

    void onFetchComplete(boolean success);
}
