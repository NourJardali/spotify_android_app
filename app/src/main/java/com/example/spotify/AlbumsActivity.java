package com.example.spotify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotify.controller.AlbumController;
import com.example.spotify.databinding.ActivityAlbumsBinding;
import com.example.spotify.interfaces.AlbumClickListener;
import com.example.spotify.interfaces.AlbumListener;
import com.example.spotify.model.adapters.AlbumAdapter;
import com.example.spotify.model.pojo.Albums;
import com.example.spotify.model.pojo.ArtistSearchItem;

import static com.example.spotify.util.Constants.ACCESS_TOKEN;

public class AlbumsActivity extends AppCompatActivity implements AlbumListener, AlbumClickListener {
    private ActivityAlbumsBinding binding;
    private AlbumAdapter albumAdapter;
    private Albums albums;
    private ArtistSearchItem.Item artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlbumsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        AlbumController albumController = new AlbumController(this);
        SharedPreferences sharedPreferences = getSharedPreferences("spotify", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(ACCESS_TOKEN, "");
        artist = (ArtistSearchItem.Item) getIntent().getSerializableExtra("artist");
        configViews();
        albumController.startFetching(artist.getId(), accessToken);
    }

    private void configViews(){
        binding.artistName.setText(artist.getName());
        binding.rvAlbums.setHasFixedSize(true);
        binding.rvAlbums.setLayoutManager(new LinearLayoutManager(this));
        binding.rvAlbums.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        albumAdapter = new AlbumAdapter(albums, this);
        binding.rvAlbums.setAdapter(albumAdapter);
    }

    @Override
    public void onFetchProgress(Albums albums) {
        this.albums = albums;
        albumAdapter.setAlbums(albums);
        albumAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFetchComplete(boolean success) {
        if(success){
            Toast.makeText(this, "Albums fetched", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void preview(Albums.Item album) {
        Intent previewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(album.getExternal_urls().getSpotify()));
        startActivity(previewIntent);
    }
}