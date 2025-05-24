package com.example.findtrip;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.LinearLayout;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private ImageView btnTogglePassword, btnBack;
    private Button btnLogin;
    private TextView tvForgotPassword;
    private ConstraintLayout btnApple, btnFacebook, btnGoogle;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnTogglePassword = findViewById(R.id.btnTogglePassword);
        btnBack = findViewById(R.id.btnBack);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        btnApple = findViewById(R.id.btnApple);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);

        btnTogglePassword.setOnClickListener(v -> {
            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnTogglePassword.setImageResource(R.drawable.icon_visibility);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnTogglePassword.setImageResource(R.drawable.icon_visibility_off);
            }
            etPassword.setSelection(etPassword.getText().length());
            isPasswordVisible = !isPasswordVisible;
        });

        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        tvForgotPassword.setOnClickListener(v -> {
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        btnApple.setOnClickListener(v -> {
            Toast.makeText(this, "Login with Apple", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        btnFacebook.setOnClickListener(v -> {
            Toast.makeText(this, "Login with Facebook", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        btnGoogle.setOnClickListener(v -> {
            Toast.makeText(this, "Login with Google", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
            startActivity(intent);
        });
    }
}
