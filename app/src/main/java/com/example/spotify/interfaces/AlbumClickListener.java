package com.example.spotify.interfaces;

import com.example.spotify.model.pojo.Albums;

public interface AlbumClickListener {
    void preview(Albums.Item album);
}
