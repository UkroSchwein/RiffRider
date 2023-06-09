package com.example.riffrider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<FavoriteSong> songs;
    private OnItemClickListener listener;

    public void setItems(List<FavoriteSong> itemList) {
        this.songs = itemList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(FavoriteSong song);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewSongTitle;
        public TextView textViewArtist;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewSongTitle = itemView.findViewById(R.id.textViewSongTitle);
            textViewArtist = itemView.findViewById(R.id.textViewArtist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            FavoriteSong song = songs.get(position);
                            listener.onItemClick(song);
                        }
                    }
                }
            });
        }
    }

    public FavoriteAdapter(List<FavoriteSong> songs) {
        this.songs = songs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_song, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FavoriteSong song = songs.get(position);
        holder.textViewSongTitle.setText(song.getTitle());
        holder.textViewArtist.setText(song.getArtist());
    }

    @Override
    public int getItemCount() {
        return songs != null ? songs.size() : 0;
    }
}
