package com.example.findtrip;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentAcceptedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accepted_payment);

        // Automatically close the activity after 3 seconds
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            finish(); // Close the activity
        }, 3000);
    }
}