package com.example.findtrip;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CartpageActivity extends AppCompatActivity {

    private ImageView btnBack, notificationIcon;
    private ImageView homeIcon, favoritesIcon, cartIcon, profileIcon;
    private TextView removeItem1, removeItem2, removeItem3, removeItem4;
    private Button checkOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartpage);

        // Initialize views
        btnBack = findViewById(R.id.btnBack);
        notificationIcon = findViewById(R.id.notificationIcon);
        homeIcon = findViewById(R.id.homeIcon);
        favoritesIcon = findViewById(R.id.favoritesIcon);
        cartIcon = findViewById(R.id.cartIcon);
        profileIcon = findViewById(R.id.profileIcon);
        removeItem1 = findViewById(R.id.removeItem1);
        removeItem2 = findViewById(R.id.removeItem2);
        removeItem3 = findViewById(R.id.removeItem3);
        removeItem4 = findViewById(R.id.removeItem4);
        checkOutButton = findViewById(R.id.checkOutButton);

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

        // Handle remove from cart clicks
        removeItem1.setOnClickListener(view -> {
            Toast.makeText(this, "Đã xóa item 1 khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
            // Add logic to remove item 1 from cart (e.g., hide the LinearLayout)
        });

        removeItem2.setOnClickListener(view -> {
            Toast.makeText(this, "Đã xóa item 2 khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
            // Add logic to remove item 2 from cart
        });

        removeItem3.setOnClickListener(view -> {
            Toast.makeText(this, "Đã xóa item 3 khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
            // Add logic to remove item 3 from cart
        });

        removeItem4.setOnClickListener(view -> {
            Toast.makeText(this, "Đã xóa item 4 khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
            // Add logic to remove item 4 from cart
        });

        // Handle Check Out button click
        checkOutButton.setOnClickListener(view -> {
            Toast.makeText(this, "Chuyển đến thanh toán", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PaymentActivity.class);
            startActivity(intent);
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