package com.example.rha.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rha.LoginActivity;
import com.example.rha.ProvideFoodActivity;
import com.example.rha.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private CardView provide_food;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        provide_food=root.findViewById(R.id.provide_food_card);

        provide_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProvideFoodActivity.class);
                startActivity(intent);

            }
        });


        return root;
    }
}