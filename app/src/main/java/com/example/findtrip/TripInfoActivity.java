package com.example.findtrip;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TripInfoActivity extends AppCompatActivity {

    // Declare UI components
    private ImageView btnBack;
    private ImageView btnLogo;
    private ImageView btnNotification;
    private ImageView templeImage;
    private TextView titleText;
    private TextView subtitleText;
    private TextView descriptionText1;
    private TextView descriptionText2;

    // Bottom Navigation
    private ImageView homeIcon;
    private ImageView favoritesIcon;
    private ImageView cartIcon;
    private ImageView profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tripinfo);

        // Initialize views
        initViews();

        // Set up click listeners
        setupClickListeners();

        // Load initial data
        loadData();
    }

    private void initViews() {
        // Top toolbar components
        btnBack = findViewById(R.id.btn_back);
        btnLogo = findViewById(R.id.btn_logo);
        btnNotification = findViewById(R.id.btn_notification);

        // Content components
        templeImage = findViewById(R.id.anhdiadanh);
        titleText = findViewById(R.id.title);
        subtitleText = findViewById(R.id.subtitle);
        descriptionText1 = findViewById(R.id.description);


        // Bottom navigation components
        homeIcon = findViewById(R.id.homeIcon);
        favoritesIcon = findViewById(R.id.favoritesIcon);
        cartIcon = findViewById(R.id.cartIcon);
        profileIcon = findViewById(R.id.profileIcon);
    }

    private void setupClickListeners() {
        // Back button click listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Logo click listener
        btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to home or refresh current page
                Toast.makeText(TripInfoActivity.this, "Logo clicked", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(TripInfoActivity.this, HomePageActivity.class);
                startActivity(home);
                // You can add navigation logic here
                // Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                // startActivity(intent);
            }
        });

        // Notification button click listener
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to notifications
                Toast.makeText(TripInfoActivity.this, "Notifications", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                // startActivity(intent);
            }
        });

        // Temple image click listener
        templeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show full screen image or image gallery
                Toast.makeText(TripInfoActivity.this, "View full image", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, ImageViewActivity.class);
                // startActivity(intent);
            }
        });

        // Bottom navigation click listeners
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });

        favoritesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFavorites();
            }
        });

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCart();
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToProfile();
            }
        });
    }

    private void loadData() {
        // Load temple information from database or API
        // This is where you would typically load data from your backend

        // For now, we'll set some sample data
        String title = "Chùa Byodo-In Temple";
        String subtitle = "Một ngôi chùa Phật giáo tuyệt đẹp tại Hawai với kiến trúc Nhật Bản truyền thống.";
        String description1 = "Chùa Byodo-In là một ngôi chùa Phật giáo được xây dựng năm 1968 để kỷ niệm 100 năm người Nhật đầu tiên đến Hawaii. Ngôi chùa này được xây dựng theo mô hình của ngôi chùa Byodo-in nổi tiếng ở Uji, Nhật Bản.";
        String description2 = "Với khung cảnh thiên nhiên tuyệt đẹp và kiến trúc độc đáo, đây là điểm đến lý tưởng cho những ai muốn tìm hiểu về văn hóa Nhật Bản.";

        // You can update TextViews if you have them with proper IDs
        // titleText.setText(title);
        // subtitleText.setText(subtitle);
        // descriptionText1.setText(description1);
        // descriptionText2.setText(description2);
    }

    // Bottom navigation methods
    private void navigateToHome() {
        Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(this, HomeActivity.class);
        // startActivity(intent);
        updateBottomNavigation(0);
    }

    private void navigateToFavorites() {
        Toast.makeText(this, "Favorites selected", Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(this, FavoritesActivity.class);
        // startActivity(intent);
        updateBottomNavigation(1);
    }

    private void navigateToCart() {
        Toast.makeText(this, "Cart selected", Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(this, CartActivity.class);
        // startActivity(intent);
        updateBottomNavigation(2);
    }

    private void navigateToProfile() {
        Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(this, ProfileActivity.class);
        // startActivity(intent);
        updateBottomNavigation(3);
    }

    private void updateBottomNavigation(int selectedIndex) {
        // Reset all icons to normal state
        homeIcon.setAlpha(0.6f);
        favoritesIcon.setAlpha(0.6f);
        cartIcon.setAlpha(0.6f);
        profileIcon.setAlpha(0.6f);

        // Highlight selected icon
        switch (selectedIndex) {
            case 0:
                homeIcon.setAlpha(1.0f);
                break;
            case 1:
                favoritesIcon.setAlpha(1.0f);
                break;
            case 2:
                cartIcon.setAlpha(1.0f);
                break;
            case 3:
                profileIcon.setAlpha(1.0f);
                break;
        }
    }

    // Handle system back button
    @Override
    public void onBackPressed() {
        // Add custom back button behavior if needed
        super.onBackPressed();
    }

    // Optional: Add methods for data loading from API
    private void loadTempleDataFromAPI() {
        // This method would typically use Retrofit, Volley, or similar
        // to load data from your backend API

        // Example pseudo-code:
        // ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        // Call<TempleResponse> call = apiService.getTempleDetails(templeId);
        // call.enqueue(new Callback<TempleResponse>() {
        //     @Override
        //     public void onResponse(Call<TempleResponse> call, Response<TempleResponse> response) {
        //         if (response.isSuccessful()) {
        //             updateUI(response.body());
        //         }
        //     }
        //
        //     @Override
        //     public void onFailure(Call<TempleResponse> call, Throwable t) {
        //         showError("Failed to load data");
        //     }
        // });
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Optional: Add animation methods
    private void animateImageLoad() {
        templeImage.setAlpha(0f);
        templeImage.animate()
                .alpha(1f)
                .setDuration(500)
                .start();
    }
}
