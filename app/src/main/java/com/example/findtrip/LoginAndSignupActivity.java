package com.example.findtrip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginAndSignupActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button loginButton;
    private Button signupButton;
    private Button skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginandsignup);

        // Ánh xạ các view từ layout
        imageView = findViewById(R.id.imageView);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signup_button);
        skipButton = findViewById(R.id.skipButton);

        // Nút Đăng nhập
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginAndSignupActivity.this, "Login Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginAndSignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Nút Đăng ký
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginAndSignupActivity.this, "Sign Up Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginAndSignupActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Nút Bỏ qua
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginAndSignupActivity.this, "Skip Clicked", Toast.LENGTH_SHORT).show();
                finish(); // Đóng màn LoginAndSignupActivity
            }
        });
    }
}
