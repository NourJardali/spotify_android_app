package com.example.spotify.interfaces;

import com.example.spotify.model.pojo.ArtistSearchItem;

public interface ArtistSearchListener {
    void onFetchProgress(ArtistSearchItem artistSearchItem);

    void onFetchComplete(boolean success);
}
