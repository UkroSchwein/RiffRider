package com.example.riffrider;

import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class SongFragment extends Fragment {
    private TextView songTextView;

    private ScrollView scrollView;
    private ImageView scrollButton;
    private TextView speedButton;
    private ImageView moveButton;

    private MyViewModel itemViewModel;

    private ValueAnimator animator;

    String songText;
    String songName;
    String songArtist;
    String songGenre;

    ImageView imageView;

    int songId;

    FavoriteSong existingSong;


    private boolean isScrolling = false;
    private int scrollSpeed1 = 100000;
    private int scrollSpeed2 = 50000;
    private int scrollSpeed3 = 10000;

    private int scrollSp = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.song_layout, container, false);
        songTextView = view.findViewById(R.id.songTextView);

        itemViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        itemViewModel.CreateDB(getContext());


        if (getArguments() != null) {
            songText = getArguments().getString("songText");
            songTextView.setText(songText);
            songName = getArguments().getString("songName");
            songArtist = getArguments().getString("songAuthor");
            songGenre = getArguments().getString("songGenre");
            songId = getArguments().getInt("songId");
        }


        existingSong = itemViewModel.getFavoriteSongById(songId);
        imageView =  (ImageView) view.findViewById(R.id.moveButton);

        if (existingSong != null) {
            imageView.setImageResource(R.drawable.free_icon_heart_1077086_1);
        } else {
            imageView.setImageResource(R.drawable.heart_outline);
        }


        scrollView = view.findViewById(R.id.scrollView);
        scrollButton = view.findViewById(R.id.scrollButton);
        speedButton = view.findViewById(R.id.speedButton);
        moveButton = view.findViewById(R.id.moveButton);

        scrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isScrolling) {
                    startScrolling();
                } else {
                    stopScrolling();
                }
            }
        });

        speedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseScrollSpeed();
            }
        });

        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFavorite();
            }
        });

        int targetScrollY = songTextView.getBottom() - scrollView.getHeight();
        animator = ValueAnimator.ofInt(scrollView.getScrollY(), 10000);
        animator.setDuration(scrollSpeed1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                scrollView.scrollTo(0, animatedValue);
            }
        });

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).hideBottomNavigation();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) requireActivity()).showBottomNavigation();
    }

    private void startScrolling() {
        isScrolling = true;
        animator.start();
        scrollButton.setImageResource(R.drawable.pause);
    }

    private void stopScrolling() {
        isScrolling = false;
        animator.cancel();
        scrollButton.setImageResource(R.drawable.free_icon_play_button_arrowhead_27223_1);
    }

    private void increaseScrollSpeed() {
        if (scrollSp == 3)
            scrollSp = 0;
        scrollSp++;
        String text = scrollSp + "x";
        speedButton.setText(text);
        switch (scrollSp){
            case 1:
                animator.setDuration(scrollSpeed1);
                break;
            case 2:
                animator.setDuration(scrollSpeed2);
                break;
            case 3:
                animator.setDuration(scrollSpeed3);
                break;
        }
    }

    private void AddFavorite() {
        existingSong = itemViewModel.getFavoriteSongById(songId);
        if (existingSong != null) {
            itemViewModel.deleteFavoriteById(songId);
            imageView.setImageResource(R.drawable.heart_outline);
        } else {
            itemViewModel.addFavoriteSong(songId, songName, songArtist, songText, songGenre);
            imageView.setImageResource(R.drawable.free_icon_heart_1077086_1);
        }
    }
}
