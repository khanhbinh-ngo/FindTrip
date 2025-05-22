package com.example.findtrip;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private EditText searchBar;
    private ImageView filterButton;
    private RecyclerView recyclerView;

    private Button promoButton, hotelsButton, exploreButton, flightsButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        searchBar = findViewById(R.id.searchBar);
        filterButton = findViewById(R.id.filterButton);

        promoButton = findViewById(R.id.promo);
        hotelsButton = findViewById(R.id.hotels);
        exploreButton = findViewById(R.id.explore);
        flightsButton = findViewById(R.id.flights);

        ImageView homeIcon = findViewById(R.id.homeIcon);
        ImageView favoritesIcon = findViewById(R.id.favoritesIcon);
        ImageView cartIcon = findViewById(R.id.cartIcon);
        ImageView profileIcon = findViewById(R.id.profileIcon);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        setupBottomNavigation();

        View.OnClickListener tabListener = v -> {
            Button selected = (Button) v;
            Toast.makeText(this, "Selected: " + selected.getText(), Toast.LENGTH_SHORT).show();
        };

        promoButton.setOnClickListener(tabListener);
        hotelsButton.setOnClickListener(tabListener);
        exploreButton.setOnClickListener(tabListener);
        flightsButton.setOnClickListener(tabListener);

        homeIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
        });

        favoritesIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Favorites clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomePageActivity.this, FavoriteActivity.class);
            startActivity(intent);
        });

        cartIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show();
        });

        profileIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomePageActivity.this, MyAccountActivity.class);
            startActivity(intent);
        });


        filterButton.setOnClickListener(v ->
                Toast.makeText(this, "Filter clicked", Toast.LENGTH_SHORT).show());
    }

    private void setupBottomNavigation() {
        findViewById(R.id.homeIcon).setOnClickListener(v ->
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show());
        findViewById(R.id.favoritesIcon).setOnClickListener(v ->
                Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show());
        findViewById(R.id.cartIcon).setOnClickListener(v ->
                Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show());
        findViewById(R.id.profileIcon).setOnClickListener(v ->
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show());
    }

    private List<String> getSampleData() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            data.add("Item " + i);
        }
        return data;
    }
}

