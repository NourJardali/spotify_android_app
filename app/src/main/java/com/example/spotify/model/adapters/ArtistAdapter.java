package com.example.spotify.model.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotify.databinding.ArtistItemBinding;
import com.example.spotify.interfaces.ArtistClickListener;
import com.example.spotify.model.DownloadImageTask;
import com.example.spotify.model.pojo.ArtistSearchItem;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    private ArtistSearchItem artists;
    private final ArtistClickListener artistClickListener;

    public ArtistAdapter(ArtistSearchItem artists, ArtistClickListener listener){
        this.artists = artists;
        artistClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ArtistItemBinding binding = ArtistItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    public void setArtists(ArtistSearchItem artistSearchItem){
        artists = artistSearchItem;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArtistItemBinding binding = holder.binding;
        ArtistSearchItem.Item artist = artists.getArtists().getItems().get(position);
        if(artist.getImages().size() != 0)
            new DownloadImageTask(binding.artistImage).execute(artist.getImages().get(0).getUrl());
        binding.artistName.setText(artist.getName());
        binding.nbFollowers.setText(artist.getFollowers().getTotal() + " followers");
        binding.ratingBar.setRating((float) (artist.getPopularity() * 0.05));

        binding.getRoot().setOnClickListener(view -> artistClickListener.onClick(artist));
    }

    @Override
    public int getItemCount() {
        return artists != null && artists.getArtists().getItems() != null ? artists.getArtists().getItems().size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ArtistItemBinding binding;

        public ViewHolder(ArtistItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
