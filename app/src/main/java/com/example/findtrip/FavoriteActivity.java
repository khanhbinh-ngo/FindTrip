package com.example.findtrip;

import com.example.findtrip.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FavoriteActivity extends AppCompatActivity {

    private ImageView btnBack, notificationIcon;
    private ImageView homeIcon, favoritesIcon, cartIcon, profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        // Khởi tạo các view
        btnBack = findViewById(R.id.btnBack);
        notificationIcon = findViewById(R.id.notificationIcon);

        homeIcon = findViewById(R.id.homeIcon);
        favoritesIcon = findViewById(R.id.favoritesIcon);
        cartIcon = findViewById(R.id.cartIcon);
        profileIcon = findViewById(R.id.profileIcon);

        // Xử lý sự kiện click nút back
        btnBack.setOnClickListener(view -> {
            finish(); // Trở về màn hình trước đó
        });

        // Xử lý sự kiện click biểu tượng thông báo
        notificationIcon.setOnClickListener(view -> {
            Toast.makeText(this, "Bạn có thông báo mới", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Notification.class);
            startActivity(intent);
        });

        // Xử lý sự kiện click ở thanh điều hướng dưới
        homeIcon.setOnClickListener(view -> {
            Toast.makeText(this, "Trang chủ", Toast.LENGTH_SHORT).show();
            // startActivity(new Intent(this, HomeActivity.class)); nếu có
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

        // Nếu bạn muốn xử lý nút "See more" thì cần đặt `android:id` cho TextView đó để xử lý tương tự
    }
}
