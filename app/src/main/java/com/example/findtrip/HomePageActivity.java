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
        List<CardItem> dataList = createSampleCardItems();

        // Set adapter
        MyAdapter adapter = new MyAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private List<CardItem> createSampleCardItems() {
        List<CardItem> dataList = new ArrayList<>();

        // Add sample data
        dataList.add(new CardItem("https://example.com/image1.jpg", "Chùa Byodo-In Temple", "$129.00"));
        dataList.add(new CardItem("https://example.com/image2.jpg", "Đền Parthenon", "$189.00"));
        dataList.add(new CardItem("https://example.com/image3.jpg", "Tháp Eiffel", "$299.00"));
        dataList.add(new CardItem("https://example.com/image4.jpg", "Tượng Nữ thần Tự do", "$249.00"));
        dataList.add(new CardItem("https://example.com/image5.jpg", "Colosseum Roma", "$199.00"));
        dataList.add(new CardItem("https://example.com/image6.jpg", "Machu Picchu", "$399.00"));
        dataList.add(new CardItem("https://example.com/image7.jpg", "Vạn Lý Trường Thành", "$169.00"));
        dataList.add(new CardItem("https://example.com/image8.jpg", "Taj Mahal", "$229.00"));
        dataList.add(new CardItem("https://example.com/image9.jpg", "Christ the Redeemer", "$279.00"));
        dataList.add(new CardItem("https://example.com/image10.jpg", "Chichen Itza", "$319.00"));

        return dataList;
    }

    private void setupClickListeners() {
        // Tab buttons click listeners
        View.OnClickListener tabListener = v -> {
            Button selected = (Button) v;
            String buttonText = selected.getText().toString();
            Toast.makeText(this, "Selected: " + buttonText, Toast.LENGTH_SHORT).show();

            // Handle different tab selections
            handleTabSelection(buttonText);
        };

        promoButton.setOnClickListener(tabListener);
        hotelsButton.setOnClickListener(tabListener);
        exploreButton.setOnClickListener(tabListener);
        flightsButton.setOnClickListener(tabListener);

        // Bottom navigation click listeners
        setupBottomNavigation();

        // Filter button click listener
        filterButton.setOnClickListener(v -> {
            Toast.makeText(this, "Filter clicked", Toast.LENGTH_SHORT).show();
            // Open filter dialog or activity
            openFilterOptions();
        });
    }

    private void handleTabSelection(String tabName) {
        // Update RecyclerView data based on selected tab
        List<CardItem> filteredData = new ArrayList<>();

        switch (tabName.toLowerCase()) {
            case "promo":
                filteredData = getPromoItems();
                break;
            case "hotels":
                filteredData = getHotelItems();
                break;
            case "explore":
                filteredData = getExploreItems();
                break;
            case "flights":
                filteredData = getFlightItems();
                break;
            default:
                filteredData = createSampleCardItems();
                break;
        }

        // Update adapter with new data
        MyAdapter newAdapter = new MyAdapter(this, filteredData);
        recyclerView.setAdapter(newAdapter);
    }

    private List<CardItem> getPromoItems() {
        List<CardItem> promoItems = new ArrayList<>();
        promoItems.add(new CardItem("https://example.com/promo1.jpg", "Promo Deal 1", "$99.00"));
        promoItems.add(new CardItem("https://example.com/promo2.jpg", "Promo Deal 2", "$79.00"));
        promoItems.add(new CardItem("https://example.com/promo3.jpg", "Promo Deal 3", "$119.00"));
        promoItems.add(new CardItem("https://example.com/promo4.jpg", "Promo Deal 4", "$89.00"));
        return promoItems;
    }

    private List<CardItem> getHotelItems() {
        List<CardItem> hotelItems = new ArrayList<>();
        hotelItems.add(new CardItem("https://example.com/hotel1.jpg", "Luxury Hotel A", "$299.00"));
        hotelItems.add(new CardItem("https://example.com/hotel2.jpg", "Beach Resort B", "$399.00"));
        hotelItems.add(new CardItem("https://example.com/hotel3.jpg", "City Hotel C", "$199.00"));
        hotelItems.add(new CardItem("https://example.com/hotel4.jpg", "Mountain Lodge D", "$249.00"));
        return hotelItems;
    }

    private List<CardItem> getExploreItems() {
        List<CardItem> exploreItems = new ArrayList<>();
        exploreItems.add(new CardItem("https://example.com/explore1.jpg", "Adventure Tour 1", "$159.00"));
        exploreItems.add(new CardItem("https://example.com/explore2.jpg", "City Walking Tour", "$49.00"));
        exploreItems.add(new CardItem("https://example.com/explore3.jpg", "Museum Visit", "$29.00"));
        exploreItems.add(new CardItem("https://example.com/explore4.jpg", "Nature Hike", "$79.00"));
        return exploreItems;
    }

    private List<CardItem> getFlightItems() {
        List<CardItem> flightItems = new ArrayList<>();
        flightItems.add(new CardItem("https://example.com/flight1.jpg", "Tokyo Flight", "$899.00"));
        flightItems.add(new CardItem("https://example.com/flight2.jpg", "Paris Flight", "$799.00"));
        flightItems.add(new CardItem("https://example.com/flight3.jpg", "New York Flight", "$699.00"));
        flightItems.add(new CardItem("https://example.com/flight4.jpg", "London Flight", "$749.00"));
        return flightItems;
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

    private void openFilterOptions() {
        // Open filter dialog or activity
        // You can implement a dialog or navigate to a filter activity
        Toast.makeText(this, "Opening filter options...", Toast.LENGTH_SHORT).show();

        // Example: Open filter dialog
        // FilterDialog dialog = new FilterDialog();
        // dialog.show(getSupportFragmentManager(), "filter_dialog");
    }

    private void refreshData() {
        // Refresh the current data
        List<CardItem> refreshedData = createSampleCardItems();
        MyAdapter refreshAdapter = new MyAdapter(this, refreshedData);
        recyclerView.setAdapter(refreshAdapter);
        Toast.makeText(this, "Data refreshed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when returning to this activity
        refreshData();
    }
}