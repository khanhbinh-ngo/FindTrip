
package com.example.findtrip;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void backToMainActivity(View view) {
        finish(); // Quay về MainActivity
    }

    public void exitApp(View view) {
        finishAffinity(); // exit things
    }
}
