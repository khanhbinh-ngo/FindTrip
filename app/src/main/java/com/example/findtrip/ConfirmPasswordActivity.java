package com.example.findtrip;

import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class ConfirmPasswordActivity extends AppCompatActivity {

    private Button confirm;

    private ImageView success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmpassword);

        confirm = findViewById(R.id.confirm);
        success = findViewById(R.id.success);

        confirm.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }


}
