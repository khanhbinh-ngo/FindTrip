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

        // Initialize views
        initializeViews();

        // Setup RecyclerView
        setupRecyclerView();

        // Setup click listeners
        setupClickListeners();
    }

    private void initializeViews() {
        searchBar = findViewById(R.id.searchBar);
        filterButton = findViewById(R.id.filterButton);
        recyclerView = findViewById(R.id.recyclerView);

        promoButton = findViewById(R.id.promo);
        hotelsButton = findViewById(R.id.hotels);
        exploreButton = findViewById(R.id.explore);
        flightsButton = findViewById(R.id.flights);
    }

    private void setupRecyclerView() {
        // Set layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Create sample data

        // Set adapter
    }



    private void setupClickListeners() {
        // Tab buttons click listeners
        View.OnClickListener tabListener = v -> {
            Button selected = (Button) v;
            String buttonText = selected.getText().toString();
            Toast.makeText(this, "Selected: " + buttonText, Toast.LENGTH_SHORT).show();

            // Handle different tab selections
        };

        promoButton.setOnClickListener(tabListener);
        hotelsButton.setOnClickListener(tabListener);
        exploreButton.setOnClickListener(tabListener);
        flightsButton.setOnClickListener(tabListener);

        // Bottom navigation click listeners
        setupBottomNavigation();

        // Filter button click listener
        filterButton.setOnClickListener(v -> {
            Toast.makeText(this, "Opening filter options...", Toast.LENGTH_SHORT).show();
            // Open filter dialog or activity
            Intent intent = new Intent(HomePageActivity.this, SearchFilterActivity.class);
            startActivity(intent);
        });
    }



    private void setupBottomNavigation() {
        ImageView homeIcon = findViewById(R.id.homeIcon);
        ImageView favoritesIcon = findViewById(R.id.favoritesIcon);
        ImageView cartIcon = findViewById(R.id.cartIcon);
        ImageView profileIcon = findViewById(R.id.profileIcon);

        homeIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
            // Already on home, maybe refresh data
            refreshData();
        });

        favoritesIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Favorites clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomePageActivity.this, FavoriteActivity.class);
            startActivity(intent);
        });

        cartIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show();
            // Navigate to cart activity
            // Intent intent = new Intent(HomePageActivity.this, CartActivity.class);
            // startActivity(intent);
        });

        profileIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomePageActivity.this, MyAccountActivity.class);
            startActivity(intent);
        });
    }

    private void refreshData() {
        // Refresh the current data
        Toast.makeText(this, "Data refreshed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when returning to this activity
        refreshData();
    }
}