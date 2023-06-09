package com.example.riffrider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class FavoriteFragment extends Fragment {

    private MyViewModel itemViewModel;
    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private List<FavoriteSong> songList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_layout, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoriteAdapter(songList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FavoriteSong song) {
                NavController navController = Navigation.findNavController(requireView());
                Bundle args = new Bundle();
                args.putString("songText", song.getLyrics());
                args.putString("songName", song.getTitle());
                args.putString("songAuthor", song.getArtist());
                args.putString("songGenre",song.getGenre());
                args.putInt("songId", song.id);
                navController.navigate(R.id.songFragment, args);
            }
        });
        itemViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        itemViewModel.CreateDB(getContext());
        itemViewModel.getFavoriteList().observe(getViewLifecycleOwner(), itemList -> {
            adapter.setItems(itemList);
        });



        if (itemViewModel.favoriteDao.getAllFavorites().size() == 0) {
            recyclerView.setVisibility(View.GONE);
            view.findViewById(R.id.emptyImageView).setVisibility(View.VISIBLE);
            view.findViewById(R.id.emptyTextView).setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            view.findViewById(R.id.emptyImageView).setVisibility(View.GONE);
            view.findViewById(R.id.emptyTextView).setVisibility(View.GONE);
        }

        EditText searchEditText = view.findViewById(R.id.editTextSearch);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Не используется
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                itemViewModel.searchFavorite(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Не используется
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        itemViewModel.CloseDB();
    }
}
