package com.example.spotify.model.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotify.databinding.AlbumItemBinding;
import com.example.spotify.interfaces.AlbumClickListener;
import com.example.spotify.model.DownloadImageTask;
import com.example.spotify.model.pojo.Albums;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private Albums albums;
    private final AlbumClickListener albumClickListener;

    public AlbumAdapter(Albums albums, AlbumClickListener listener){
        this.albums = albums;
        albumClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumItemBinding binding = AlbumItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumItemBinding binding = holder.binding;
        Albums.Item album = albums.getItems().get(position);
        if(album.getImages().size() != 0)
            new DownloadImageTask(binding.albumImage).execute(album.getImages().get(0).getUrl());
        binding.albumName.setText(album.getName());
        StringBuilder artistList = new StringBuilder();
        if(album.getArtists().size() > 1){
            for(Albums.Artist artist : album.getArtists()){
                artistList.append(artist.getName()).append(", ");
            }
        }
        else{
            artistList.append(album.getArtists().get(0).getName());
        }
        binding.albumArtist.setText(artistList);
        binding.albumReleaseDate.setText(album.getRelease_date());
        binding.albumTracks.setText(album.getTotal_tracks() + " tracks");
        binding.btnPreview.setOnClickListener(view -> albumClickListener.preview(album));
    }

    @Override
    public int getItemCount() {
        return albums != null && albums.getItems() != null ? albums.getItems().size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AlbumItemBinding binding;

        public ViewHolder(AlbumItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
