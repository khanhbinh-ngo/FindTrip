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
    private ImageButton btnTogglePassword, btnBack;
    private Button btnLogin;
    private TextView tvForgotPassword;
    private ConstraintLayout btnApple, btnFacebook, btnGoogle;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Đảm bảo tên file XML đúng

        // Khởi tạo các view
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnTogglePassword = findViewById(R.id.btnTogglePassword);
        btnBack = findViewById(R.id.btnBack);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        btnApple = findViewById(R.id.btnApple);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);

        // Xử lý nút hiển thị/ẩn mật khẩu
        btnTogglePassword.setOnClickListener(v -> {
            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnTogglePassword.setImageResource(R.drawable.icon_visibility); // Icon ẩn mật khẩu
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnTogglePassword.setImageResource(R.drawable.icon_visibility_off); // Icon hiện mật khẩu
            }
            etPassword.setSelection(etPassword.getText().length()); // Đặt con trỏ về cuối
            isPasswordVisible = !isPasswordVisible;
        });

        // Xử lý nút quay lại
        btnBack.setOnClickListener(v -> {
            finish(); // Đóng activity hiện tại
        });

        // Xử lý đăng nhập
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Thêm logic đăng nhập thực tế ở đây
                Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý forgot password
        tvForgotPassword.setOnClickListener(v -> {
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
            // Chuyển tới trang reset mật khẩu nếu có
        });

        // Xử lý login qua Apple
        btnApple.setOnClickListener(v -> {
            Toast.makeText(this, "Login with Apple", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            // Thêm xử lý Apple login
        });

        // Xử lý login qua Facebook
        btnFacebook.setOnClickListener(v -> {
            Toast.makeText(this, "Login with Facebook", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            // Thêm xử lý Facebook login
        });

        // Xử lý login qua Google
        btnGoogle.setOnClickListener(v -> {
            Toast.makeText(this, "Login with Google", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            // Thêm xử lý Google login
        });
    }
}
