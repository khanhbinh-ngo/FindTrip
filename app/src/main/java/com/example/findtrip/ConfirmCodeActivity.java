package com.example.findtrip;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmCodeActivity extends AppCompatActivity {

    private EditText etPin1, etPin2, etPin3, etPin4;
    private Button btnConfirm, btnCancel;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmationcode); // Đảm bảo file XML là activity_main

        // Ánh xạ các view
        etPin1 = findViewById(R.id.etPin1);
        etPin2 = findViewById(R.id.etPin2);
        etPin3 = findViewById(R.id.etPin3);
        etPin4 = findViewById(R.id.etPin4);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);
        btnBack = findViewById(R.id.btnBack);

        // Xử lý nút back
        btnBack.setOnClickListener(v -> {
            finish(); // Quay lại activity trước đó
        });

        // Xử lý nút xác nhận
        btnConfirm.setOnClickListener(v -> {
            String pin = etPin1.getText().toString().trim() +
                    etPin2.getText().toString().trim() +
                    etPin3.getText().toString().trim() +
                    etPin4.getText().toString().trim();

            if (pin.length() == 4 && !pin.contains("-")) {
                // Gửi mã PIN hoặc xử lý tiếp
                Toast.makeText(this, "PIN Entered: " + pin, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ResetPasswordActivity.class);
                startActivity(intent);

                // Ví dụ: mở Activity khác
                // startActivity(new Intent(MainActivity.this, NextActivity.class));
            } else {
                Toast.makeText(this, "Please enter a valid 4-digit PIN.", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút hủy
        btnCancel.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}

