package com.example.findtrip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TripDetailActivity extends AppCompatActivity {

    // UI Components
    private ImageView btnBack, btnNotification, btnLogo;
    private ImageView anhdiadanh;
    private TextView priceText;
    private LinearLayout wishlistLayout, cartLayout;
    private LinearLayout checkinLayout, checkoutLayout;
    private TextView checkinDate, checkoutDate;
    private TextView descriptionText;

    // Bottom Navigation
    private ImageView homeIcon, favoritesIcon, cartIcon, profileIcon;
    private LinearLayout bottomNavigationLayout;

    // Data variables
    private boolean isWishlisted = false;
    private boolean isInCart = false;
    private String currentPrice = "$59.99";
    private String checkinDateValue = "April 15";
    private String checkoutDateValue = "April 20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tripdetail);

        initViews();
        setupClickListeners();
        loadTripData();
    }

    private void initViews() {
        // Header components
        btnBack = findViewById(R.id.btn_back);
        btnNotification = findViewById(R.id.btn_notification);
        btnLogo = findViewById(R.id.btn_logo);
        anhdiadanh = findViewById(R.id.anhdiadanh);

        // Content components
        priceText = findViewById(R.id.price_tag);
        wishlistLayout = findViewById(R.id.wishlist);
        cartLayout = findViewById(R.id.cart);
        checkinLayout = findViewById(R.id.checkin_layout);
        checkoutLayout = findViewById(R.id.checkout_layout);
        descriptionText = findViewById(R.id.description_text);

        // Bottom navigation
        homeIcon = findViewById(R.id.homeIcon);
        favoritesIcon = findViewById(R.id.favoritesIcon);
        cartIcon = findViewById(R.id.cartIcon);
        profileIcon = findViewById(R.id.profileIcon);
        bottomNavigationLayout = findViewById(R.id.bottomNavigationLayout);
    }

    private void setupClickListeners() {
        // Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Notification button
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNotifications();
            }
        });

        // Logo click
        btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });

        // Wishlist click
        wishlistLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWishlist();
            }
        });

        // Add to cart click
        cartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

        // Check-in date click
        checkinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCheckinDate();
            }
        });

        // Check-out date click
        checkoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCheckoutDate();
            }
        });

        // Bottom navigation clicks
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });

        favoritesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFavorites();
            }
        });

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });
    }

    private void loadTripData() {
        // Load trip data from intent or database
        Intent intent = getIntent();
        if (intent != null) {
            String price = intent.getStringExtra("price");
            String checkin = intent.getStringExtra("checkin_date");
            String checkout = intent.getStringExtra("checkout_date");
            String description = intent.getStringExtra("description");
            int imageRes = intent.getIntExtra("image_resource", R.drawable.anhdiadanh);

            if (price != null) {
                currentPrice = price;
                if (priceText != null) {
                    priceText.setText(currentPrice);
                }
            }

            if (checkin != null) {
                checkinDateValue = checkin;
                if (checkinDate != null) {
                    checkinDate.setText(checkinDateValue);
                }
            }

            if (checkout != null) {
                checkoutDateValue = checkout;
                if (checkoutDate != null) {
                    checkoutDate.setText(checkoutDateValue);
                }
            }

            if (description != null && descriptionText != null) {
                descriptionText.setText(description);
            }

            anhdiadanh.setImageResource(imageRes);
        }
    }

    private void toggleWishlist() {
        isWishlisted = !isWishlisted;

        if (isWishlisted) {
            Toast.makeText(this, "Added to Wishlist", Toast.LENGTH_SHORT).show();
            // Update wishlist icon state if needed
            // wishlistIcon.setImageResource(R.drawable.ic_favorite_filled);
        } else {
            Toast.makeText(this, "Removed from Wishlist", Toast.LENGTH_SHORT).show();
            // wishlistIcon.setImageResource(R.drawable.ic_favorite_color);
        }

        // Save to preferences or database
        saveWishlistState();
    }

    private void addToCart() {
        if (!isInCart) {
            isInCart = true;
            Toast.makeText(this, "Added to Cart - " + currentPrice, Toast.LENGTH_SHORT).show();

            // Update cart count in preferences
            updateCartCount(1);

            // Optionally update cart icon with badge
            updateCartIcon();
        } else {
            Toast.makeText(this, "Already in Cart", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectCheckinDate() {
        // Open date picker for check-in
        // You can use DatePickerDialog here
        Toast.makeText(this, "Select Check-in Date", Toast.LENGTH_SHORT).show();

        // Example: Open date picker
        // DatePickerDialog datePickerDialog = new DatePickerDialog(this,
        //     new DatePickerDialog.OnDateSetListener() {
        //         @Override
        //         public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //             checkinDateValue = String.format("%s %d", getMonthName(month), dayOfMonth);
        //             checkinDate.setText(checkinDateValue);
        //         }
        //     }, year, month, day);
        // datePickerDialog.show();
    }

    private void selectCheckoutDate() {
        // Open date picker for check-out
        Toast.makeText(this, "Select Check-out Date", Toast.LENGTH_SHORT).show();

        // Similar to check-in date picker
    }

    private void openNotifications() {
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }

    private void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void goToFavorites() {
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }

    private void goToCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    private void goToProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void saveWishlistState() {
        // Save wishlist state to SharedPreferences
        getSharedPreferences("trip_prefs", MODE_PRIVATE)
                .edit()
                .putBoolean("is_wishlisted_" + getTripId(), isWishlisted)
                .apply();
    }

    private void updateCartCount(int increment) {
        // Update cart count in SharedPreferences
        int currentCount = getSharedPreferences("cart_prefs", MODE_PRIVATE)
                .getInt("cart_count", 0);

        getSharedPreferences("cart_prefs", MODE_PRIVATE)
                .edit()
                .putInt("cart_count", currentCount + increment)
                .apply();
    }

    private void updateCartIcon() {
        // Update cart icon with badge or different state
        // This could involve showing a badge with item count
    }

    private String getTripId() {
        // Return unique trip ID - could come from intent or generate based on content
        return getIntent().getStringExtra("trip_id") != null ?
                getIntent().getStringExtra("trip_id") : "default_trip";
    }

    private void loadWishlistState() {
        // Load wishlist state from SharedPreferences
        isWishlisted = getSharedPreferences("trip_prefs", MODE_PRIVATE)
                .getBoolean("is_wishlisted_" + getTripId(), false);

        // Update UI based on state
        if (isWishlisted) {
            // Update wishlist icon to filled state
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWishlistState();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Add any cleanup or animation here
    }
}