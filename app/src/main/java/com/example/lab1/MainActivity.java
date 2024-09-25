package com.example.lab1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("MainActivity", "MainActivity onCreate() called");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button buttonGoToActivity = findViewById(R.id.buttonGoToActivity);
        buttonGoToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        Button buttonOpenGoogle = findViewById(R.id.buttonOpenGoogle);
        buttonOpenGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.google.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("MainActivity", "MainActivity onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "MainActivity onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity", "MainActivity onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("MainActivity", "MainActivity onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w("MainActivity", "MainActivity onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("MainActivity", "MainActivity onDestroy() called");
    }
}