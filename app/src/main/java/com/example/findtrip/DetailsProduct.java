package com.example.findtrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsProduct extends AppCompatActivity {

    private ImageView btnBack, notificationIcon, addToWishlist;
    private Button addToCart;
    private ImageView homeIcon, favoritesIcon, cartIcon, profileIcon;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_product);

        // Initialize views
        btnBack = findViewById(R.id.btnBack);
        notificationIcon = findViewById(R.id.notificationIcon);
        addToCart = findViewById(R.id.addToCart);
        addToWishlist = findViewById(R.id.addToWishlist);
        homeIcon = findViewById(R.id.homeIcon);
        cartIcon = findViewById(R.id.cartIcon);
        profileIcon = findViewById(R.id.profileIcon);

        // Handle back button click
        btnBack.setOnClickListener(view -> {
            finish(); // Return to the previous screen
        });

        // Handle notification icon click
        notificationIcon.setOnClickListener(view -> {
            Toast.makeText(this, "Bạn có thông báo mới", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Notification.class);
            startActivity(intent);
        });

        // Handle Add to Cart button click
        addToCart.setOnClickListener(view -> {
            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });

        // Handle Add to Wishlist button click
        addToWishlist.setOnClickListener(view -> {
            Toast.makeText(this, "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
        });

        // Handle bottom navigation clicks
        homeIcon.setOnClickListener(view -> {
            Toast.makeText(this, "Trang chủ", Toast.LENGTH_SHORT).show();
            // startActivity(new Intent(this, HomeActivity.class)); if exists
        });

        favoritesIcon.setOnClickListener(view -> {
            Toast.makeText(this, "Mục yêu thích", Toast.LENGTH_SHORT).show();
        });

        cartIcon.setOnClickListener(view -> {
            Toast.makeText(this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
        });

        profileIcon.setOnClickListener(view -> {
            Toast.makeText(this, "Hồ sơ cá nhân", Toast.LENGTH_SHORT).show();
        });
    }
}