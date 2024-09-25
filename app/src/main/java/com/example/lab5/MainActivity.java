package com.example.lab5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CharacterFragment fragment = new CharacterFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.place_fragment, fragment)
                .commit();
    }
}