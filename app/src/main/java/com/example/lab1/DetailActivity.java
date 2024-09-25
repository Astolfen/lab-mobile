package com.example.lab1;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("DetailActivity", "MainActivity onCreate() called");
        EdgeToEdge.enable(this);
        setContentView(R.layout.detail_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("DetailActivity", "MainActivity onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("DetailActivity", "MainActivity onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("DetailActivity", "MainActivity onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("DetailActivity", "MainActivity onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w("DetailActivity", "MainActivity onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("DetailActivity", "MainActivity onDestroy() called");
    }
}
