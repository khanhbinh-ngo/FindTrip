package com.example.findtrip;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private ImageButton btnBack, btnTogglePassword;
    private Button btnLogin, btnApple, btnFacebook, btnGoogle;
    private TextView tvForgotPassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Initialize views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnBack = findViewById(R.id.btnBack);
        btnTogglePassword = findViewById(R.id.btnTogglePassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnApple = findViewById(R.id.btnApple);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // Set click listeners
        btnBack.setOnClickListener(v -> onBackPressed());

        btnTogglePassword.setOnClickListener(v -> togglePasswordVisibility());

        tvForgotPassword.setOnClickListener(v -> {
            // Navigate to forgot password screen
            Toast.makeText(LoginActivity.this, "Forgot Password clicked", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            // startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            // Validate inputs
            if (validateInputs()) {
                // Perform login
                performLogin();
            }
        });

        btnApple.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "Continue with Apple clicked", Toast.LENGTH_SHORT).show();
            // Implement Apple sign-in logic
        });

        btnFacebook.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "Continue with Facebook clicked", Toast.LENGTH_SHORT).show();
            // Implement Facebook sign-in logic
        });

        btnGoogle.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "Continue with Google clicked", Toast.LENGTH_SHORT).show();
            // Implement Google sign-in logic
        });
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnTogglePassword.setImageResource(R.drawable.icon_visibility);
        } else {
            // Show password
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnTogglePassword.setImageResource(R.drawable.icon_visibility_off);
        }
        isPasswordVisible = !isPasswordVisible;

        // Move cursor to end
        etPassword.setSelection(etPassword.getText().length());
    }

    private boolean validateInputs() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    private void performLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Here you would typically use your authentication logic
        // For example, using Firebase Authentication:
        // FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        //     .addOnCompleteListener(this, task -> { ... });

        // For this example, we'll just show a toast
        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

        // Navigate to main screen
        // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        // startActivity(intent);
        // finish();
    }
}
