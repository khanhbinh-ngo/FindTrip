package com.example.findtrip;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private ImageButton btnBack;
    private Button btnRegister, btnApple, btnFacebook, btnGoogle;
    private ImageButton btnTogglePassword;
    private TextView tvLogin;
    private boolean isPasswordVisible = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Initialize views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnBack = findViewById(R.id.btnBack);
        btnTogglePassword = findViewById(R.id.btnTogglePassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnApple = findViewById(R.id.btnApple);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);

        // Set click listeners
        btnBack.setOnClickListener(v -> onBackPressed());

        btnTogglePassword.setOnClickListener(v -> togglePasswordVisibility());


        btnApple.setOnClickListener(v -> {
            Toast.makeText(RegisterActivity.this, "Continue with Apple clicked", Toast.LENGTH_SHORT).show();
            // Implement Apple sign-in logic
        });

        btnFacebook.setOnClickListener(v -> {
            Toast.makeText(RegisterActivity.this, "Continue with Facebook clicked", Toast.LENGTH_SHORT).show();
            // Implement Facebook sign-in logic
        });

        btnGoogle.setOnClickListener(v -> {
            Toast.makeText(RegisterActivity.this, "Continue with Google clicked", Toast.LENGTH_SHORT).show();
            // Implement Google sign-in logic
        });
    }

    private void togglePasswordVisibility() {

    }
}
