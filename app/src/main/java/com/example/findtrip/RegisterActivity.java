package com.example.findtrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private static final int RC_GOOGLE_SIGN_IN = 9001;
    private static final int RC_FACEBOOK_SIGN_IN = 9002;

    // UI components
    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private ImageButton btnBack, btnTogglePassword;
    private Button btnRegister;
    private ConstraintLayout btnApple, btnFacebook, btnGoogle;

    // Local storage
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_USERS = "users";

    // State
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Initialize UI components
        initializeUI();

        // Set up click listeners
        setupClickListeners();
    }

    private void initializeUI() {
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
    }

    private void setupClickListeners() {
        // Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Toggle password visibility
        btnTogglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        // Register button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                if (true){
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Apple login
        btnApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simulate Apple login
                simulateAppleLogin();
            }
        });

        // Facebook login
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simulate Facebook login
                simulateFacebookLogin();
            }
        });

        // Google login
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simulate Google login
                simulateGoogleLogin();
            }
        });
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnTogglePassword.setImageResource(R.drawable.icon_visibility);
        } else {
            // Show password
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnTogglePassword.setImageResource(R.drawable.icon_visibility_off);
        }
        isPasswordVisible = !isPasswordVisible;

        // Maintain cursor position
        etPassword.setSelection(etPassword.getText().length());
        etConfirmPassword.setSelection(etConfirmPassword.getText().length());
    }

    private void registerUser() {
        // Get input values
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        // Validate inputs
        if (!validateInputs(name, email, password, confirmPassword)) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate network delay
                    Thread.sleep(1500);

                    // Save user data
                    final boolean success = saveUserData(name, email, password);

                    // Update UI on main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (success) {
                                Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                redirectToMainActivity(email);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Email already registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private boolean validateInputs(String name, String email, String password, String confirmPassword) {
        // Validate name
        if (TextUtils.isEmpty(name)) {
            etName.setError("Name is required");
            etName.requestFocus();
            return false;
        }

        // Validate email
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return false;
        }

        // Validate password
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            etPassword.requestFocus();
            return false;
        }

        // Validate password confirmation
        if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.setError("Confirm your password");
            etConfirmPassword.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return false;
        }

        return true;
    }

    private boolean saveUserData(String name, String email, String password) {
        try {
            // Get existing users or create new JSON object
            String usersJson = sharedPreferences.getString(KEY_USERS, "{}");
            JSONObject usersObject = new JSONObject(usersJson);

            // Check if email already exists
            if (usersObject.has(email)) {
                return false;
            }

            // Create user JSON object
            JSONObject userObject = new JSONObject();
            userObject.put("name", name);
            userObject.put("email", email);
            userObject.put("password", password);  // Note: In a real app, password should be hashed
            userObject.put("createdAt", System.currentTimeMillis());

            // Add user to users object
            usersObject.put(email, userObject);

            // Save updated users object
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_USERS, usersObject.toString());
            editor.apply();

            Log.d(TAG, "User registered: " + email);
            return true;
        } catch (JSONException e) {
            Log.e(TAG, "Error saving user data", e);
            return false;
        }
    }

    private void simulateAppleLogin() {
        Toast.makeText(this, "Processing Apple login...", Toast.LENGTH_SHORT).show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate network delay
                    Thread.sleep(1500);

                    // Create a random Apple user
                    final String email = "apple_user_" + System.currentTimeMillis() + "@example.com";
                    final String name = "Apple User";
                    saveUserData(name, email, "apple_oauth_token");

                    // Update UI on main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "Apple login successful!", Toast.LENGTH_SHORT).show();
                            redirectToMainActivity(email);
                            Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
                            startActivity(intent);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void simulateFacebookLogin() {
        Toast.makeText(this, "Processing Facebook login...", Toast.LENGTH_SHORT).show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate network delay
                    Thread.sleep(1500);

                    // Create a random Facebook user
                    final String email = "fb_user_" + System.currentTimeMillis() + "@example.com";
                    final String name = "Facebook User";
                    saveUserData(name, email, "fb_oauth_token");

                    // Update UI on main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "Facebook login successful!", Toast.LENGTH_SHORT).show();
                            redirectToMainActivity(email);
                            Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
                            startActivity(intent);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void simulateGoogleLogin() {
        Toast.makeText(this, "Processing Google login...", Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate network delay
                    Thread.sleep(1500);

                    // Create a random Google user
                    final String email = "google_user_" + System.currentTimeMillis() + "@gmail.com";
                    final String name = "Google User";
                    saveUserData(name, email, "google_oauth_token");

                    // Update UI on main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "Google login successful!", Toast.LENGTH_SHORT).show();
                            redirectToMainActivity(email);
                            Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
                            startActivity(intent);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void redirectToMainActivity(String email) {
        // Save current user session
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("current_user", email);
        editor.apply();

        // Start MainActivity
        Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}