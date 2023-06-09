package com.example.riffrider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment {

    MyViewModel itemViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_layout, container, false);
        RelativeLayout frameLayout = view.findViewById(R.id.frameLayout);
        Button cardView = view.findViewById(R.id.acceptButton);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frameLayout.getVisibility() == View.VISIBLE) {
                    frameLayout.setVisibility(View.GONE);
                } else {
                    // Фрейм скрыт, показ
                }
            }
        });

        CheckBox checkboxGenre1 = view.findViewById(R.id.checkbox1);
        CheckBox checkboxGenre2 = view.findViewById(R.id.checkbox2);

        Button buttonConfirm = view.findViewById(R.id.acceptButton);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> selectedGenres = new ArrayList<>();

                if (checkboxGenre1.isChecked()) {
                    selectedGenres.add("Альтернатива");
                }

                if (checkboxGenre2.isChecked()) {
                    selectedGenres.add("Панк");
                }
                DataHolder.setData(selectedGenres);
                DataHolder.setFiltered(true);
                if (selectedGenres.isEmpty())
                    DataHolder.setFiltered(false);
                requireActivity().onBackPressed();
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
}

