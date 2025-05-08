package com.example.findtrip; // Đổi thành tên package thật của bạn

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyAccountActivity extends AppCompatActivity {

    ImageView iconLeft, iconRight;
    LinearLayout editProfileLayout, settingsLayout, vacationsLayout, helpLayout, logoutLayout;
    ImageView homeIcon, favoritesIcon, cartIcon, profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount); // Đảm bảo file XML có tên này

        // Header
        iconLeft = findViewById(R.id.icon_left);
        iconRight = findViewById(R.id.icon_right);

        // Account options
        editProfileLayout = findViewById(R.id.account_options);
        settingsLayout     = (LinearLayout)((LinearLayout) findViewById(R.id.account_options)).getChildAt(1);
        vacationsLayout    = (LinearLayout)((LinearLayout) findViewById(R.id.account_options)).getChildAt(2);
        helpLayout         = (LinearLayout)((LinearLayout) findViewById(R.id.account_options)).getChildAt(3);
        logoutLayout       = (LinearLayout)((LinearLayout) findViewById(R.id.account_options)).getChildAt(4);

        // Bottom navigation
        homeIcon = findViewById(R.id.homeIcon);
        favoritesIcon = findViewById(R.id.favoritesIcon);
        cartIcon = findViewById(R.id.cartIcon);
        profileIcon = findViewById(R.id.profileIcon);

        setupClickListeners();
    }

    private void setupClickListeners() {
        // Header icons
        iconLeft.setOnClickListener(v -> finish()); // Back
        iconRight.setOnClickListener(v ->
                Toast.makeText(this, "Notifications clicked", Toast.LENGTH_SHORT).show());

        // Account options
        if (editProfileLayout != null) {
            editProfileLayout.setOnClickListener(v ->
                    Toast.makeText(this, "Edit Profile clicked", Toast.LENGTH_SHORT).show());
        }

        settingsLayout.setOnClickListener(v ->
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show());

        vacationsLayout.setOnClickListener(v ->
                Toast.makeText(this, "My Vacations clicked", Toast.LENGTH_SHORT).show());

        helpLayout.setOnClickListener(v ->
                Toast.makeText(this, "Help Support clicked", Toast.LENGTH_SHORT).show());

        logoutLayout.setOnClickListener(v -> {
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();
            Intent Intent = new Intent(MyAccountActivity.this, LoginActivity.class);
            startActivity(Intent);
            // TODO: Add logout logic here
        });

        homeIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MyAccountActivity.this, HomePageActivity.class);
            startActivity(intent);
            // TODO: Chuyển sang trang Home hoặc thực hiện logic
        });

        favoritesIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Favorites clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MyAccountActivity.this, FavoriteActivity.class);
            startActivity(intent);
            // TODO: Chuyển sang mục yêu thích
        });

        cartIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show();
            // TODO: Chuyển sang giỏ hàng
        });

        profileIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
            // TODO: Chuyển sang hồ sơ người dùng
        });
    }
}
