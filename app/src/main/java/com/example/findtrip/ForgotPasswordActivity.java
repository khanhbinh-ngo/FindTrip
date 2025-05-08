package com.example.findtrip;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private Button cancelButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        // Ánh xạ View
        emailEditText = findViewById(R.id.Email);
        resetPasswordButton = findViewById(R.id.loginButton);
        cancelButton = findViewById(R.id.signup_button);
        backButton = findViewById(R.id.btnBack);

        // Xử lý nút "Back"
        backButton.setOnClickListener(v -> finish());

        // Xử lý nút "Reset Password"
        resetPasswordButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(ForgotPasswordActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Thực hiện xử lý gửi email reset password tại đây
                Toast.makeText(ForgotPasswordActivity.this, "Reset link sent to: " + email, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForgotPasswordActivity.this, ConfirmCodeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Xử lý nút "Cancel"
        cancelButton.setOnClickListener(v -> {
            // Ví dụ: quay về LoginActivity
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

