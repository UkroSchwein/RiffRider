package com.example.riffrider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecommendedsongsFragment extends Fragment {

    private MyViewModel itemViewModel;
    private RecyclerView recyclerView;
    private SongAdapter adapter;
    private List<Song> songList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommendedsong_layout, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SongAdapter(songList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song song) {
                NavController navController = Navigation.findNavController(requireView());
                Bundle args = new Bundle();
                args.putString("songText", song.getLyrics());
                args.putString("songName", song.getTitle());
                args.putString("songAuthor", song.getArtist());
                args.putString("songGenre",song.getGenre());
                args.putInt("songId", song.songId);
                navController.navigate(R.id.songFragment, args);
            }
        });
        itemViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        itemViewModel.CreateDB(getContext());
        itemViewModel.getItemList().observe(getViewLifecycleOwner(), itemList -> {
            adapter.setItems(itemList);
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        itemViewModel.CloseDB();
    }
}

