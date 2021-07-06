package com.example.spotify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotify.controller.SearchController;
import com.example.spotify.databinding.ActivityArtistSearchBinding;
import com.example.spotify.interfaces.ArtistClickListener;
import com.example.spotify.interfaces.ArtistSearchListener;
import com.example.spotify.model.adapters.ArtistAdapter;
import com.example.spotify.model.pojo.ArtistSearchItem;

import static com.example.spotify.util.Constants.ACCESS_TOKEN;

public class ArtistSearchActivity extends AppCompatActivity implements ArtistSearchListener, ArtistClickListener {
    private ActivityArtistSearchBinding binding;
    private ArtistAdapter artistAdapter;
    private ArtistSearchItem artists;
    private SearchController searchController;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtistSearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        searchController = new SearchController(this);

        SharedPreferences sharedPreferences = getSharedPreferences("spotify", MODE_PRIVATE);
        accessToken = sharedPreferences.getString(ACCESS_TOKEN, "");
        configViews();
    }

    private void configViews(){
        binding.rvArtists.setHasFixedSize(true);
        binding.rvArtists.setLayoutManager(new LinearLayoutManager(this));
        binding.rvArtists.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        artistAdapter = new ArtistAdapter(artists, this);
        binding.rvArtists.setAdapter(artistAdapter);
        binding.searchView.setActivated(true);
        binding.searchView.setQueryHint("Search for Artists");
        binding.searchView.onActionViewExpanded();
        binding.searchView.setIconified(false);
        binding.searchView.clearFocus();
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchController.startFetching(s, accessToken);
                return false;
            }
        });
    }

    @Override
    public void onFetchProgress(ArtistSearchItem artistSearchItem) {
        this.artists = artistSearchItem;
        artistAdapter.setArtists(artistSearchItem);
        artistAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFetchComplete(boolean success) {
        if(success){
            Toast.makeText(this, "Search result updated", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(ArtistSearchItem.Item artist) {
        Intent albumsIntent = new Intent(this, AlbumsActivity.class);
        albumsIntent.putExtra("artist", artist);
        startActivity(albumsIntent);
    }
}