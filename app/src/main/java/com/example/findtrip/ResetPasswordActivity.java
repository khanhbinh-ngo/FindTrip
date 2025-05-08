package com.example.findtrip;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText etNewPassword, etConfirmPassword;
    private Button btnResetPassword, btnCancel;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpassword); // Đảm bảo tên file XML là activity_reset_password.xml

        // Ánh xạ view
        etNewPassword = findViewById(R.id.NewPassword);
        etConfirmPassword = findViewById(R.id.ConfirmPassword);
        btnResetPassword = findViewById(R.id.setPassword);
        btnCancel = findViewById(R.id.signup_button);
        btnBack = findViewById(R.id.btnBack);

        // Sự kiện nút Back
        btnBack.setOnClickListener(v -> finish());

        // Sự kiện nút Cancel
        btnCancel.setOnClickListener(v -> {
            // Quay về màn hình đăng nhập hoặc đóng màn hình hiện tại
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Sự kiện nút Reset Password
        btnResetPassword.setOnClickListener(v -> {
            String newPassword = etNewPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Gọi API hoặc lưu mật khẩu mới vào database
            Toast.makeText(this, "Password reset successfully.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConfirmPasswordActivity.class);
            startActivity(intent);
        });
    }
}

